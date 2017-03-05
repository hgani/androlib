package com.gani.lib.ui.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.ui.style.Length;

public class HorizontalLayout extends AbstractLinearLayout<HorizontalLayout> {
  public HorizontalLayout(Context context) {
    super(context);
  }

  public void setWeightOf(View child, int weight) {
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) child.getLayoutParams();
    if (params == null) {
      params = new LinearLayout.LayoutParams(
          ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    params.width = 0;
    params.weight = weight;
    child.setLayoutParams(params);
  }
}
