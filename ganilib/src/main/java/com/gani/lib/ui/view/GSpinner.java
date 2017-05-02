package com.gani.lib.ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.widget.SpinnerAdapter;

public class GSpinner extends AppCompatSpinner {
//  private static Spec defaultSpec = new Spec();
//
//  public static void setDefaultSpec(Spec defaultSpec) {
//    GSpinner.defaultSpec = defaultSpec;
//  }

  private ViewHelper helper;

  public GSpinner(Context context) {
    super(context);
    init();
  }

  public GSpinner(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    this.helper = new ViewHelper(this);
//    defaultSpec.init(this);
  }

  public GSpinner size(Integer width, Integer height) {
//    ViewGroup.LayoutParams params = getLayoutParams();
//    if (width != null) {
//      params.width = Length.dpToPx(width);
//    }
//    if (height != null) {
//      params.height = Length.dpToPx(height);
//    }
//    setLayoutParams(params);

    helper.size(width, height);
    return this;
  }

  public GSpinner adapter(SpinnerAdapter adapter) {
    setAdapter(adapter);
    return this;
  }

  public GSpinner bgResource(int resId) {
    setBackgroundResource(resId);
    return this;
  }

  public GSpinner bgTintList(ColorStateList colorStateList) {
    ViewCompat.setBackgroundTintList(this, colorStateList);
    return this;
  }

  public GSpinner enabled(boolean enabled) {
    setEnabled(enabled);
    return this;
  }

//  public GSpinner spec(Spec spec) {
//    spec.init(this);
//    return this;
//  }
//
//  public GSpinner bgColor(String code) {
//    bgColor(Ui.color(code));
//    return this;
//  }
//
//  public GSpinner bgColor(int color) {
//    // Alternative implementation: http://stackoverflow.com/questions/1521640/standard-android-button-with-a-different-color
//     getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
////    ViewCompat.setBackgroundTintList(this, ContextCompat.getColorStateList(getContext(), colorResId));
//    return this;
//  }
//
//  public GSpinner color(String code) {
//    color(Ui.color(code));
//    return this;
//  }
//
//  public GSpinner color(int color) {
//    setTextColor(color);
//    return this;
//  }
//
//  public GSpinner text(String text) {
//    setText(text);
//    return this;
//  }
//
//  public GSpinner textSize(float textSize) {
//    setTextSize(textSize);
//    return this;
//  }
//
//  public GSpinner padding(int left, int top, int right, int bottom) {
//    setPadding(left, top, right, bottom);
//    return this;
//  }
//
  public GSpinner margin(Integer left, Integer top, Integer right, Integer bottom) {
    helper.margin(left, top, right, bottom);
    return this;
  }

  public GSpinner onItemSelected(OnItemSelectedListener listener) {
    setOnItemSelectedListener(listener);
    return this;
  }

//
//  public GSpinner onClick(OnClickListener listener) {
//    helper.onClick(listener);
//    return this;
//  }
//
//
//
//  public static class Spec {
//    public void init(GSpinner button) {
////      button.bgColor(Ui.color(R.color.colorAccent));
//
//      Integer backgroundColor = backgroundColor();
//      if (backgroundColor != null) {
//        button.bgColor(backgroundColor);
//      }
//
//      Integer color = color();
//      if (color != null) {
//        button.color(color);
//      }
//
//      Integer textSize = textSize();
//      if (textSize != null) {
//        button.textSize(textSize);
//      }
//    }
//
//    public Integer backgroundColor() {
//      return null;
//    }
//
//    public Integer color() {
//      return null;
//    }
//
//    public Integer textSize() {
//      return null;
//    }
//  }
}
