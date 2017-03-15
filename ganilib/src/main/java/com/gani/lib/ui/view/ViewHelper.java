package com.gani.lib.ui.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.logging.GLog;
import com.gani.lib.ui.style.Length;

public class ViewHelper {
  private View view;

  ViewHelper(View view) {
    this.view = view;

    // Ensure layout params can't be null.
    view.setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
  }

  public void size(Integer width, Integer height) {
    ViewGroup.LayoutParams params = view.getLayoutParams();
    if (width != null) {
      if (width >= 0) {
        params.width = Length.dpToPx(width);
      }
      else {
        params.width = width;
      }
    }
    if (height != null) {
//      params.height = Length.dpToPx(height);

      if (width >= 0) {
        params.height = Length.dpToPx(height);
      }
      else {
        params.height = height;
      }
    }
    view.setLayoutParams(params);
  }

  void margin(int left, int top, int right, int bottom) {
//    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    ViewGroup.LayoutParams params = view.getLayoutParams();
    if (params instanceof ViewGroup.MarginLayoutParams) {
      ((ViewGroup.MarginLayoutParams) params).setMargins(
          Length.dpToPx(left),
          Length.dpToPx(top),
          Length.dpToPx(right),
          Length.dpToPx(bottom));
    }
    else {
      GLog.w(getClass(), "Unable to set margin for: " + view);
    }
  }

  void click(View.OnClickListener listener) {
    view.setOnClickListener(listener);
  }
}
