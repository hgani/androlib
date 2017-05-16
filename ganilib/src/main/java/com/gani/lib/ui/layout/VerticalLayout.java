package com.gani.lib.ui.layout;

import android.content.Context;
import android.util.AttributeSet;

import com.gani.lib.ui.view.ViewHelper;

public class VerticalLayout extends AbstractLinearLayout<VerticalLayout> {
  public VerticalLayout(Context context) {
    super(context);
    init();
  }

  private void init() {
    setOrientation(VERTICAL);
  }

  public VerticalLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }
}
