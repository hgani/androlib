package com.gani.lib.ui.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.ui.view.ViewHelper;

public class AbstractLinearLayout<T extends AbstractLinearLayout> extends LinearLayout {
  private ViewHelper helper;

  protected AbstractLinearLayout(Context context) {
    super(context);

    init();

//    // Ensure layout params can't be null.
//    setLayoutParams(new LinearLayout.LayoutParams(
//        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
  }

  public AbstractLinearLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    this.helper = new ViewHelper(this);
  }

  public T self() {
    return (T) this;
  }

  public T size(Integer width, Integer height) {
//    ViewGroup.LayoutParams params = getLayoutParams();
//    if (width != null) {
//      GLog.t(getClass(), "WIDTH: " + width);
//      if (width > 0) {
//        params.width = Length.dpToPx(width);
//      }
//      else {  // Special values such as MATCH_PARENT
//        params.width = width;
//      }
//    }
//    if (height != null) {
//      if (height > 0) {
//        params.height = Length.dpToPx(height);
//      }
//      else {  // Special values such as MATCH_PARENT
//        params.height = height;
//      }
//    }
//    setLayoutParams(params);

    helper.size(width, height);
    return (T) this;
  }

  public T vertical(){
    this.setOrientation(LinearLayout.VERTICAL);
    return (T) this;
  }

  public T horizontal(){
    this.setOrientation(LinearLayout.HORIZONTAL);
    return (T) this;
  }

  public T padding(Integer left, Integer top, Integer right, Integer bottom) {
    helper.padding(left, top, right, bottom);
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

  public T bgColor(int color) {
    setBackgroundColor(color);
    return (T) this;
  }

  public T bgColor(String code) {
    helper.bgColor(code);
    return self();
  }

  public T bg(Drawable drawable) {
    int sdk = android.os.Build.VERSION.SDK_INT;
    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
      setBackgroundDrawable(drawable);
    } else {
      setBackground(drawable);
    }

    // Not sure why this doesn't work
//    ViewCompat.setBackground(this, drawable);
    return self();
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

  public T rtl() {
    ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_RTL);
    return self();
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
