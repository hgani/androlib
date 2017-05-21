package com.gani.web.htmlform;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLFieldValidation
    implements TextWatcher, Spinner.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
  private static final String ATTR_VALIDATE = "data-validate";

  private static final String JSON_MESSAGES = "messages";
  private static final String JSON_OPTIONS = "options";

  private static final String VALIDATE_KIND = "kind";
  private static final String VALIDATE_PRESENCE = "presence";
  private static final String VALIDATE_LENGTH = "length";
  private static final String VALIDATE_BLACKLIST = "blacklist";

  private static final String OPTIONS_MAXIMUM = "maximum";
  private static final String OPTIONS_FORMATS = "formats";

  private static final String MESSAGES_BLANK = "blank";
  private static final String MESSAGES_TOO_LONG = "too_long";

  private static final String DATETIME_PICKER_TYPE = "datetime_picker";
  private static final String DATE_PICKER_TYPE = "date_picker";
  private static final String TIME_PICKER_TYPE = "time_picker";

  private static final String DATE_FORMAT = "\\d{4}-\\d{2}-\\d{2}";
  private static final String TIME_FORMAT = "\\d{2}:\\d{2}";
  private static final String DATETIME_FORMAT = DATE_FORMAT + "\\s" + TIME_FORMAT;

  private Element mField;
  private HTMLEditText mHtmlEditText;
  private HTMLSpinner mHtmlSpinner;
  private HTMLCheckBox mHtmlCheckBox;
  private HTMLRadioButton mHtmlRadioButton;
  private ArrayList<String> mErrorMessages = new ArrayList<>();

  public HTMLFieldValidation(Element field, HTMLSpinner htmlSpinner) {
    this.mField = field;
    this.mHtmlSpinner = htmlSpinner;
  }

  public HTMLFieldValidation(Element field, HTMLEditText htmlEditText) {
    this.mField = field;
    this.mHtmlEditText = htmlEditText;
  }

  public HTMLFieldValidation(Element field, HTMLCheckBox htmlCheckBox) {
    this.mField = field;
    this.mHtmlCheckBox = htmlCheckBox;
  }

  public HTMLFieldValidation(Element field, HTMLRadioButton htmlRadioButton) {
    this.mField = field;
    this.mHtmlRadioButton = htmlRadioButton;
  }

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        ignored
  }

  @Override
  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    validate(mHtmlEditText);

    if (mErrorMessages.size() > 0) {
      mHtmlEditText.setError(readableErrorMessages());
    } else {
      mHtmlEditText.setError(null);
    }

  }

  @Override
  public void afterTextChanged(Editable editable) {
//        ignored
  }

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    validate(mHtmlSpinner);

    TextView selectedTextView = (TextView) view;
    if (mErrorMessages.size() > 0) {
      selectedTextView.setError(readableErrorMessages());
    } else {
      selectedTextView.setError(null);
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> adapterView) {
//        ignored
  }

  @Override
  public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//        TODO: no example usage validation for checkbox and radio button
//        compoundButton.setError("ASD");
  }

  private String readableErrorMessages() {
    return TextUtils.join("\n", mErrorMessages);
  }

  private void validate(HTMLSpinner view) {
    mErrorMessages.clear();
    String value = view.getSelectedItem().toString();
    validateField(value);
  }

  private void validate(HTMLEditText view) {
    mErrorMessages.clear();
    String value = view.getText().toString();
    validateField(value);

    if (mField.classNames().contains(DATE_PICKER_TYPE)) {
      validateDateTimeFormat(value, DATE_FORMAT);
    } else if (mField.classNames().contains(TIME_PICKER_TYPE)) {
      validateDateTimeFormat(value, TIME_FORMAT);
    } else if (mField.classNames().contains(DATETIME_PICKER_TYPE)) {
      validateDateTimeFormat(value, DATETIME_FORMAT);
    }
  }

  private void validateDateTimeFormat(String value, String dateFormat) {
    Pattern r = Pattern.compile("^" + dateFormat + "$");
    Matcher m = r.matcher(value);

    if (!m.find()) {
      mErrorMessages.add("Invalid");
    }
  }

  private void validateField(String value) {
    if (!mField.attr(ATTR_VALIDATE).equals("")) {
      try {
        JSONArray validateOptions = new JSONArray(mField.attr(ATTR_VALIDATE));

        for (int index = 0; index < validateOptions.length(); index++) {
          JSONObject validation = validateOptions.getJSONObject(index);
          JSONObject messages = validation.getJSONObject(JSON_MESSAGES);
          JSONObject options = validation.getJSONObject(JSON_OPTIONS);

          if (Objects.equals(validation.getString(VALIDATE_KIND), VALIDATE_PRESENCE)) {
            if (value.isEmpty()) {
              mErrorMessages.add(messages.getString(MESSAGES_BLANK));
            }
          }

          if (Objects.equals(validation.getString(VALIDATE_KIND), VALIDATE_LENGTH)) {
            if (value.length() > options.getInt(OPTIONS_MAXIMUM)) {
              mErrorMessages.add(messages.getString(MESSAGES_TOO_LONG));
            }
          }

//                TODO: uncomment for validate blacklist, currently no error messages on attributes
//                if (Objects.equals(validation.getString(VALIDATE_KIND), VALIDATE_BLACKLIST)) {
//                    JSONArray formats = options.getJSONArray(OPTIONS_FORMATS);
//                    for(int i = 0; i < formats.length(); i++) {
//                        Pattern r = Pattern.compile(formats.getString(i));
//                        Matcher m = r.matcher(value);
//
//                        if (!m.find()) {
//                            mErrorMessages.add(messages);
//                        }
//                    }
//                }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }
}
