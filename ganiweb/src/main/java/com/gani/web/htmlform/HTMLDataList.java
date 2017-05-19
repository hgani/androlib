package com.gani.web.htmlform;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.gani.web.R;

import org.jsoup.nodes.Element;

public class HTMLDataList extends AppCompatAutoCompleteTextView {
  private final Element mField;

  private boolean showAlways;

  public HTMLDataList(Context context, Element field) {
    super(context);

    this.mField = field;
    setDefaultListeners();
  }

  public void setShowAlways(boolean showAlways) {
    this.showAlways = showAlways;
  }

  @Override
  protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
    super.onFocusChanged(focused, direction, previouslyFocusedRect);
    showDropDownIfFocused();
  }

  private void showDropDownIfFocused() {
    if (enoughToFilter() && isFocused() && getWindowVisibility() == View.VISIBLE) {
      showDropDown();
    }
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    showDropDownIfFocused();
  }

  @Override
  public boolean enoughToFilter() {
    return showAlways || super.enoughToFilter();
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
    setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_down_float, 0);
    setSingleLine(true);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_dropdown_item_1line);
    Element datalist = mField.parent().getElementsByTag("datalist").first();

    for(Element option : datalist.getElementsByTag("option")) {
      arrayAdapter.add(option.text());
    }

    setTag(mField.attr("name"));
    setShowAlways(true);
    setAdapter(arrayAdapter);
  }
}
