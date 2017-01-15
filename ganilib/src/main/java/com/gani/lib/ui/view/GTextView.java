package com.gani.lib.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GTextView extends TextView {
  public GTextView(Context context) {
    super(context);
  }

  public GTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

//  public static GTextView create(Context context, String text) {
//    GTextView view = new GTextView(context);
//    view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//    view.setText(text);
//    return view;
//
////    return new TextViewHelper(view);
//  }

  public GTextView boldify() {
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
}
