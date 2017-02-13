package com.gani.lib.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
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

public class GImageView extends ImageView {
  public GImageView(Context context) {
    super(context);
    init();
  }

  public GImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    // Ensure layout params can't be null.
    setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
  }

  public void setImageUrl(String url) {
    if (url != null) {
      Glide.with(getContext())
          .load(url)
          .into(this);
    }
  }

//  public GImageView size(Integer width, Integer height) {
//    ViewGroup.LayoutParams params = getLayoutParams();
//    if (width != null) {
//      params.width = Length.dpToPx(width);
//    }
//    if (height != null) {
//      params.height = Length.dpToPx(height);
//    }
//    setLayoutParams(params);
//
//    return this;
//  }

//  public GImageView padding(int left, int top, int right, int bottom) {
//    setPadding(left, top, right, bottom);
//    return this;
//  }
}
