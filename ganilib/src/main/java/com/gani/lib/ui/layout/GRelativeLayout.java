package com.gani.lib.ui.layout;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gani.lib.R;
import com.gani.lib.ui.view.ViewHelper;

import static android.R.attr.button;

public class GRelativeLayout<T extends GRelativeLayout> extends RelativeLayout {
  private ViewHelper helper;

  public GRelativeLayout(Context context) {
    super(context);

    init();
  }

  private void init() {
//    RelativeLayout.LayoutParams params = ;
//    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//    params.addRule(RelativeLayout.LEFT_OF, R.id.id_to_be_left_of);
//
//    button.setLayoutParams(params); //causes layout update
//
//    this.helper = new ViewHelper(this, new RelativeLayout.LayoutParams(
//        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    this.helper = new ViewHelper(this);
  }

  private T self() {
    return (T) this;
  }

  public T size(Integer width, Integer height) {
    helper.size(width, height);
    return (T) this;
  }

  public T padding(Integer left, Integer top, Integer right, Integer bottom) {
//    setPadding(left, top, right, bottom);
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

  public T background(int color) {
    setBackgroundColor(color);
    return (T) this;
  }

//  @Override
//  public RelativeLayout.LayoutParams getLayoutParams() {
//    return (RelativeLayout.LayoutParams) super.getLayoutParams();
//  }
//
//  public T alignParentRight() {
//    RelativeLayout.LayoutParams params = getLayoutParams();
//    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//    return self();
//  }

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
