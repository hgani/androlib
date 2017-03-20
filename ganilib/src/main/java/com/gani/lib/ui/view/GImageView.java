package com.gani.lib.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gani.lib.ui.Ui;
import com.gani.lib.ui.style.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GImageView extends AppCompatImageView {
  private ViewHelper helper;

  public GImageView(Context context) {
    super(context);
    init();
  }

  public GImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    this.helper = new ViewHelper(this);
  }

  // TODO: Remove once we make sure none of our projects is using this anymore
  public void setImageUrl(String url) {
    if (url != null) {
      Glide.with(getContext())
          .load(url)
          .into(this);
    }
  }

  public GImageView imageUrl(String url) {
    if (url != null) {
      Glide.with(getContext())
          .load(url)
          .into(this);
    }
    return this;
  }

  public GImageView size(Integer width, Integer height) {
    helper.size(width, height);
    return this;
  }

  public GImageView background(String code) {
    background(Ui.color(code));
    return this;
  }

  public GImageView background(int color) {
    setBackgroundColor(color);
    return this;
  }

  public GImageView drawable(Drawable drawable) {
    setImageDrawable(drawable);
    return this;
  }

  public GImageView drawable(int resId) {
    setImageDrawable(Ui.drawable(resId));
    return this;
  }

  public GImageView margin(Integer left, Integer top, Integer right, Integer bottom) {
    helper.margin(left, top, right, bottom);
    return this;
  }
}
