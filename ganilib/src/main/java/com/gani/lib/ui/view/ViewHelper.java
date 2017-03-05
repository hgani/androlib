package com.gani.lib.ui.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.logging.GLog;

public class ViewHelper {
  private View view;

  ViewHelper(View view) {
    this.view = view;

    // Ensure layout params can't be null.
    view.setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
  }

  void margin(int left, int top, int right, int bottom) {
//    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    ViewGroup.LayoutParams params = view.getLayoutParams();
    if (params instanceof ViewGroup.MarginLayoutParams) {
      ((ViewGroup.MarginLayoutParams) params).setMargins(left, top, right, bottom);
    }
    else {
      GLog.w(getClass(), "Unable to set margin for: " + view);
    }
  }
}
