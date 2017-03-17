package com.gani.lib.ui.layout;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.logging.GLog;
import com.gani.lib.ui.style.Length;
import com.gani.lib.ui.view.GTextView;
import com.gani.lib.ui.view.ViewHelper;

public class AbstractLinearLayout<T extends AbstractLinearLayout> extends LinearLayout {
  private ViewHelper helper;

  AbstractLinearLayout(Context context) {
    super(context);

    init();

//    // Ensure layout params can't be null.
//    setLayoutParams(new LinearLayout.LayoutParams(
//        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
  }

  private void init() {
    this.helper = new ViewHelper(this);
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

  public T margin(Integer left, Integer top, Integer right, Integer bottom) {
    helper.margin(left, top, right, bottom);
    return (T) this;
  }

  public T gravity(int gravity) {
//    helper.margin(left, top, right, bottom);
    setGravity(gravity);
    return (T) this;
  }

  public T background(int color) {
    setBackgroundColor(color);
    return (T) this;
  }

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
