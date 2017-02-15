package com.gani.web.htmlform;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.gani.web.R;

import org.jsoup.nodes.Element;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HTMLEditText extends EditText {
    private final Element mField;

    private static final String TAG = HTMLEditText.class.getName();

    private static final String ATTR_PLACEHOLDER = "placeholder";
    private static final String ATTR_NAME = "name";

    private static final String DATETIME_PICKER_TYPE = "datetime_picker";
    private static final String DATE_PICKER_TYPE     = "date_picker";
    private static final String TIME_PICKER_TYPE     = "time_picker";

    public HTMLEditText(Context context, Element field, RelativeLayout.LayoutParams params) {
        super(context);

        this.mField = field;
        setLayoutParams(params);
        setDefaultListeners();
    }

    public HTMLEditText(Context context, Element field) {
        super(context);

        this.mField = field;
        setDefaultListeners();
    }

    private void setDefaultListeners() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 25);

        setLayoutParams(params);
        setPadding(20, 20, 20, 20);
        setBackgroundResource(R.drawable.edit_text_state);
        setTag(mField.attr(ATTR_NAME));
        setHint(mField.attr(ATTR_PLACEHOLDER));
        setText(mField.val());

        if (mField.classNames().contains(DATE_PICKER_TYPE)) {
            setOnTouchListener(new DatePickerOnTouchEvent());
        }
        else if (mField.classNames().contains(TIME_PICKER_TYPE)) {
            setOnTouchListener(new TimePickerOnTouchEvent());
        }
        else if (mField.classNames().contains(DATETIME_PICKER_TYPE)) {
            setOnTouchListener(new DateTimePickerOnTouchEvent());
        }

        addTextChangedListener(new HTMLFieldValidation(mField, this));
    }

    private DatePickerDialog createDatePickerDialog(
            Calendar calendar,
            DatePickerDialog.OnDateSetListener onDateSetListener) {
        return new DatePickerDialog(
                getContext(),
                onDateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
    }

    private TimePickerDialog createTimePickerDialog(
            Calendar calendar,
            TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        return new TimePickerDialog(
                getContext(),
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
    }

    private class DatePickerOnTouchEvent implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                final HTMLEditText htmlEditText = (HTMLEditText) view;
                final Calendar calendar = Calendar.getInstance();

                DatePickerDialog datePicker = createDatePickerDialog(
                        calendar,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                calendar.set(year, month, day);
                                htmlEditText.setText(format.format(calendar.getTime()));
                            }
                        }
                );

                datePicker.show();
            }

            return false;
        }
    }

    private class TimePickerOnTouchEvent implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                final HTMLEditText htmlEditText = (HTMLEditText) view;
                final Calendar calendar = Calendar.getInstance();

                TimePickerDialog timePicker = createTimePickerDialog(
                        calendar,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int minute) {
                                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                                calendar.set(0, 0, 0, hour, minute);
                                htmlEditText.setText(format.format(calendar.getTime()));
                            }
                        }
                );

                timePicker.show();
            }

            return false;
        }
    }

    private class DateTimePickerOnTouchEvent implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                final HTMLEditText htmlEditText = (HTMLEditText) view;
                final Calendar calendar = Calendar.getInstance();

                DatePickerDialog datePicker = createDatePickerDialog(
                        calendar,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                calendar.set(year, month, day);
                            }
                        }
                );

                datePicker.setOnDismissListener(new DatePickerDialog.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        TimePickerDialog timePicker = createTimePickerDialog(
                                calendar,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hour, int minute) {
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                                        calendar.set(
                                                calendar.get(Calendar.YEAR),
                                                calendar.get(Calendar.MONTH),
                                                calendar.get(Calendar.DAY_OF_MONTH),
                                                hour,
                                                minute
                                        );
                                        htmlEditText.setText(format.format(calendar.getTime()));
                                    }
                                }
                        );

                        timePicker.show();
                    }
                });

                datePicker.show();

            }

            return false;
        }
    }
}
