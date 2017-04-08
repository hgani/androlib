package com.gani.lib.ui.layout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractHorizontalLayout<T extends AbstractLinearLayout> extends AbstractLinearLayout<T> {
  public AbstractHorizontalLayout(Context context) {
    super(context);
  }

  public void setWeightOf(View child, int weight) {
    LayoutParams params = (LayoutParams) child.getLayoutParams();
    if (params == null) {
      params = new LayoutParams(
          ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    params.width = 0;
    params.weight = weight;
    child.setLayoutParams(params);
  }

  public AbstractHorizontalLayout rtl() {
    ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_RTL);
    return this;
  }
}
