package com.gani.lib.ui.layout;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.ui.style.Length;
import com.gani.lib.ui.view.GImageView;

public class VerticalLayout extends LinearLayout {
  public VerticalLayout(Context context) {
    super(context);

    init();
  }

  private void init() {
    setOrientation(VERTICAL);

    // Ensure layout params can't be null.
    setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
  }

  public VerticalLayout size(Integer width, Integer height) {
    ViewGroup.LayoutParams params = getLayoutParams();
    if (width != null) {
      params.width = Length.dpToPx(width);
    }
    if (height != null) {
      params.height = Length.dpToPx(height);
    }
    setLayoutParams(params);

    return this;
  }

}
