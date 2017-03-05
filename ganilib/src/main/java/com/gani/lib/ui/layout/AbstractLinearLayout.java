package com.gani.lib.ui.layout;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.logging.GLog;
import com.gani.lib.ui.style.Length;
import com.gani.lib.ui.view.GTextView;

public class AbstractLinearLayout<T extends AbstractLinearLayout> extends LinearLayout {
  AbstractLinearLayout(Context context) {
    super(context);

    // Ensure layout params can't be null.
    setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

//    setLayoutParams(new LinearLayout.LayoutParams(
//        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
  }

  public T size(Integer width, Integer height) {
    ViewGroup.LayoutParams params = getLayoutParams();
    if (width != null) {
      GLog.t(getClass(), "WIDTH: " + width);
      if (width > 0) {
        params.width = Length.dpToPx(width);
      }
      else {  // Special values such as MATCH_PARENT
        params.width = width;
      }
    }
    if (height != null) {
      if (height > 0) {
        params.height = Length.dpToPx(height);
      }
      else {  // Special values such as MATCH_PARENT
        params.height = height;
      }
    }
    setLayoutParams(params);

    return (T) this;
  }

  public T padding(int left, int top, int right, int bottom) {
    setPadding(left, top, right, bottom);
    return (T) this;
  }

//
//  public T layout(Integer width, Integer height) {
//    ViewGroup.LayoutParams params = getLayoutParams();
//    if (width != null) {
//      params.
//    }
//    if (height != null) {
//      params.height = Length.dpToPx(height);
//    }
//    setLayoutParams(params);
//
//    return (T) this;
//  }
}
