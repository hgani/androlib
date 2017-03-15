package com.gani.lib.ui.form;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gani.lib.ui.layout.HorizontalLayout;
import com.gani.lib.ui.layout.VerticalLayout;

public abstract class FormField extends HorizontalLayout {
  private TextView label;
  private View editView;

  public FormField(Context context, String labelText) {
    super(context);

    setGravity(Gravity.CENTER_VERTICAL);
    size(ViewGroup.LayoutParams.MATCH_PARENT, null).padding(0, 60, 0, 0);

    this.label = createLabel(context);
    this.editView = createEditView(context);

    VerticalLayout editLayout = new VerticalLayout(context);
    editLayout.addView(editView);

    setWeightOf(label, 10);
    setWeightOf(editLayout, 4);

//    label.setBackgroundColor(Color.RED);
//    editLayout.setBackgroundColor(Color.BLUE);

    addView(label);
    addView(editLayout);

    label(labelText);
  }

  public FormField label(String text) {
    label.setText(text);
    return this;
  }

  private TextView createLabel(Context context) {
    return new TextView(context);
  }

  protected abstract View createEditView(Context context);

  TextView getLabel() {
    return label;
  }
}
