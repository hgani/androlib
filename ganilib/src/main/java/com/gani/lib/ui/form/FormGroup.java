package com.gani.lib.ui.form;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gani.lib.ui.layout.HorizontalLayout;
import com.gani.lib.ui.layout.VerticalLayout;

public class FormGroup extends VerticalLayout {
  private Float labelFontSize;

  public FormGroup(Context context) {
    super(context);
    size(LayoutParams.MATCH_PARENT, null);
  }

  public FormGroup labelFontSize(float labelFontSize) {
    this.labelFontSize = labelFontSize;
    return this;
  }

  public void add(FormField field) {
    if (labelFontSize != null) {
      field.getLabel().setTextSize(labelFontSize);
    }
    addView(field);
  }
}
