package com.gani.lib.ui.layout;

import android.content.Context;

public class VerticalLayout extends AbstractLinearLayout<VerticalLayout> {
  public VerticalLayout(Context context) {
    super(context);

    setOrientation(VERTICAL);
  }

//  private void init() {
//    setOrientation(VERTICAL);
//
//    // Ensure layout params can't be null.
//    setLayoutParams(new LinearLayout.LayoutParams(
//        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//  }
//
//  public VerticalLayout size(Integer width, Integer height) {
//    ViewGroup.LayoutParams params = getLayoutParams();
//    if (width != null) {
//      params.width = Length.dpToPx(width);
//    }
//    if (height != null) {
//      params.height = Length.dpToPx(height);
//    }
//    setLayoutParams(params);
//
//    return this;
//  }
//
//  public void addField(FormField field, String labelText) {
//    field.setLabelText(labelText);
//    addView(field);
//  }
}
