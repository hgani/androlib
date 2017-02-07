package com.gani.lib.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gani.lib.ui.Ui;
import com.gani.lib.ui.style.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GTextView extends TextView {
  public GTextView(Context context) {
    super(context);
    init();
  }

  public GTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    // Ensure layout params can't be null.
    setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
  }

  public GTextView size(Integer width, Integer height) {
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

  public GTextView bold() {
    List<String> matchers = new ArrayList<>();
    String text = getText().toString();
    SpannableStringBuilder builder = new SpannableStringBuilder(text);
    Pattern boldPattern = Pattern.compile("\\*([A-z0-9 ]+)\\*");
    Matcher matcher = boldPattern.matcher(builder);

    while(matcher.find()) {
      matchers.add(matcher.group());
    }

    for (int i = matchers.size() - 1; i >= 0; i--) {
      String m = matchers.get(i);
      int startIndex = text.indexOf(m);
      int endIndex = startIndex + m.length();

      SpannableString str = new SpannableString(m.substring(1, m.length() - 1));
      str.setSpan(new StyleSpan(Typeface.BOLD), 0, str.length(), 0);
      builder.replace(startIndex, endIndex, str);
    }

    setText(builder);

    return this;
  }

  public GTextView background(String code) {
    setBackgroundColor(Ui.color(code));
    return this;
  }

  public GTextView color(String code) {
    setTextColor(Ui.color(code));
    return this;
  }

  public GTextView padding(int left, int top, int right, int bottom) {
    setPadding(left, top, right, bottom);
    return this;
  }
}
