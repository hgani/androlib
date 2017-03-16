package com.gani.lib.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gani.lib.R;
import com.gani.lib.ui.Ui;
import com.gani.lib.ui.menu.GMenu;
import com.gani.lib.ui.style.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.button;
import static android.R.attr.text;

public class GButton extends AppCompatButton {
  private static Spec defaultSpec = new Spec();

  public static void setDefaultSpec(Spec defaultSpec) {
    GButton.defaultSpec = defaultSpec;
  }

  private ViewHelper helper;

  public GButton(Context context) {
    super(context);
    init();
  }

  public GButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    this.helper = new ViewHelper(this);
    defaultSpec.init(this);
  }

  public GButton size(Integer width, Integer height) {
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

  public GButton spec(Spec spec) {
    spec.init(this);
    return this;
  }

  public GButton background(String code) {
    background(Ui.color(code));
    return this;
  }

  public GButton background(int color) {
    // Alternative implementation: http://stackoverflow.com/questions/1521640/standard-android-button-with-a-different-color
     getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
//    ViewCompat.setBackgroundTintList(this, ContextCompat.getColorStateList(getContext(), colorResId));
    return this;
  }

  public GButton color(String code) {
    color(Ui.color(code));
    return this;
  }

  public GButton color(int color) {
    setTextColor(color);
    return this;
  }

  public GButton text(String text) {
    setText(text);
    return this;
  }

  public GButton textSize(float textSize) {
    setTextSize(textSize);
    return this;
  }

  public GButton bold() {
    return typeface(Typeface.DEFAULT_BOLD);
  }

  public GButton typeface(Typeface typeface) {
    setTypeface(typeface);
    return this;
  }

  public GButton padding(int left, int top, int right, int bottom) {
    setPadding(left, top, right, bottom);
    return this;
  }

  public GButton margin(int left, int top, int right, int bottom) {
    helper.margin(left, top, right, bottom);
    return this;
  }

  public GButton click(View.OnClickListener listener) {
    helper.click(listener);
    return this;
  }



  public static class Spec {
    public void init(GButton button) {
//      button.background(Ui.color(R.color.colorAccent));

      Integer backgroundColor = backgroundColor();
      if (backgroundColor != null) {
        button.background(backgroundColor);
      }

      Integer color = color();
      if (color != null) {
        button.color(color);
      }

      Integer textSize = textSize();
      if (textSize != null) {
        button.textSize(textSize);
      }

      Typeface typeface = typeface();
      if (typeface != null) {
        button.typeface(typeface);
      }

      button.size(width(), height());
    }

    public Integer backgroundColor() {
      return null;
    }

    public Integer color() {
      return null;
    }

    public Integer textSize() {
      return null;
    }

    public Typeface typeface() { return null; }

    public Integer width() {
      return null;
    }

    public Integer height() {
      return null;
    }
  }
}
