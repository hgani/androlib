package com.gani.lib.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
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

public class GButton extends Button {
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
    ViewGroup.LayoutParams params = getLayoutParams();
    if (width != null) {
      params.width = Length.dpToPx(width);
    }
    if (height != null) {
      params.height = Length.dpToPx(height);
    }
    setLayoutParams(params);

    return this;
  }

//  public GButton bold() {
//    List<String> matchers = new ArrayList<>();
//    String text = getText().toString();
//    SpannableStringBuilder builder = new SpannableStringBuilder(text);
//    Pattern boldPattern = Pattern.compile("\\*([A-z0-9 ]+)\\*");
//    Matcher matcher = boldPattern.matcher(builder);
//
//    while(matcher.find()) {
//      matchers.add(matcher.group());
//    }
//
//    for (int i = matchers.size() - 1; i >= 0; i--) {
//      String m = matchers.get(i);
//      int startIndex = text.indexOf(m);
//      int endIndex = startIndex + m.length();
//
//      SpannableString str = new SpannableString(m.substring(1, m.length() - 1));
//      str.setSpan(new StyleSpan(Typeface.BOLD), 0, str.length(), 0);
//      builder.replace(startIndex, endIndex, str);
//    }
//
//    setText(builder);
//
//    return this;
//  }

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
    setTextColor(Ui.color(code));
    return this;
  }

  public GButton text(String text) {
    setText(text);
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
    // TODO
//    helper.click(listener);
    return this;
  }



  public static class Spec {
    public void init(GButton button) {
      button.background(Ui.color(R.color.colorAccent));
    }
  }
}
