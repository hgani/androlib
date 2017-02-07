package com.gani.lib.htmlform;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.gani.lib.R;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class HTMLSpinner extends Spinner {
    private final String SELECTED_ATTR = "selected";

    private final Element mField;

    public HTMLSpinner(Context context, Element field, RelativeLayout.LayoutParams params) {
        super(context);

        this.mField = field;
        setLayoutParams(params);
        setDefaultListeners();
    }

    public HTMLSpinner(Context context, Element field) {
        super(context);

        this.mField = field;
        setDefaultListeners();
    }

    private void setDefaultListeners() {
        ArrayList<String> spinnerArray = new ArrayList<>();

        spinnerArray.add("");
        for(Element option : mField.children()) {
            spinnerArray.add(option.text());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray
        );

        String selectedItem = mField.getElementsByAttributeValue(SELECTED_ATTR, SELECTED_ATTR).text();
        int selectedIndex = spinnerAdapter.getPosition(selectedItem);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 25);

        setLayoutParams(params);
        setBackgroundResource(R.drawable.spinner_default);
        setAdapter(spinnerAdapter);
        setSelection(selectedIndex);

        setOnItemSelectedListener(new HTMLFieldValidation(mField, this));
    }
}
