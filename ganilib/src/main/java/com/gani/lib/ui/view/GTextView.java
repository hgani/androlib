package com.gani.lib.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;

import com.gani.lib.ui.Ui;
import com.gani.lib.ui.layout.GRelativeLayoutParams;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.width;

public class GTextView<T extends GTextView> extends AppCompatTextView implements GView {
  private ViewHelper helper;

  public GTextView(Context context) {
    super(context);
    init();
  }

  public GTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    this.helper = new ViewHelper(this);
  }

  private T self() {
    return (T) this;
  }

//  public T relative() {
//    helper.relative();
//    return self();
//  }

  public GRelativeLayoutParams<T> relative() {
    return helper.relative(self());
  }

  public T alignParentRight() {
    helper.alignParentRight();
    return self();
  }

  public GTextView spec(Spec spec) {
    spec.init(this);
    return this;
  }

  public GTextView size(Integer width, Integer height) {
    helper.size(width, height);
    return this;
  }

  public GTextView width(Integer width) {
    helper.width(width);
    return this;
  }

  public GTextView height(Integer height) {
    helper.height(height);
    return this;
  }

  public GTextView processBold() {
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
//    setBackgroundColor(Ui.color(code));
    bgColor(Ui.color(code));
    return this;
  }

  public GTextView bgColor(int color) {
    setBackgroundColor(color);
    return this;
  }

  public GTextView color(String code) {
    return color(Ui.color(code));
  }

  public GTextView color(int color) {
    setTextColor(color);
    return this;
  }

  @Override
  public GTextView padding(Integer left, Integer top, Integer right, Integer bottom) {
    helper.padding(left, top, right, bottom);
    return this;
  }

  @Override
  public GTextView margin(Integer left, Integer top, Integer right, Integer bottom) {
    helper.margin(left, top, right, bottom);
    return this;
  }

  public GTextView bold() {
    return typeface(Typeface.DEFAULT_BOLD);
  }

  public GTextView typeface(Typeface typeface) {
    setTypeface(typeface);
    return this;
  }

  public GTextView text(String text) {
    setText(text);
    return this;
  }

  public GTextView textSize(float textSize) {
    setTextSize(textSize);
//    setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    return this;
  }

//  public GTextView textSize(int unit, float textSize) {
//    setTextSize(unit, textSize);
//    return this;
//  }

  public GTextView gravity(int alignment) {
    setGravity(alignment);
    return this;
  }

  public GTextView onClick(OnClickListener listener) {
    helper.click(listener);
    return this;
  }

  // NOTE: Deprecated
  public GTextView click(OnClickListener listener) {
    helper.click(listener);
    return this;
  }

  // NOTE: Has to be called before setting the text.
  // See https://stackoverflow.com/questions/27927930/android-linkify-clickable-telephone-numbers
  public GTextView linkify(int mask) {
    setAutoLinkMask(mask);
    setLinksClickable(true);
    return this;
  }




//  EnumSet<FileAccess> readWrite = EnumSet.of(FileAccess.Read, FileAccess.Write);



  public static class Spec {
    public void init(GTextView textView) {
      Integer backgroundColor = backgroundColor();
      if (backgroundColor != null) {
        textView.bgColor(backgroundColor);
      }

      Integer color = color();
      if (color != null) {
        textView.color(color);
      }

      Integer textSize = textSize();
      if (textSize != null) {
        textView.textSize(textSize);
      }

      Typeface typeface = typeface();
      if (typeface != null) {
        textView.typeface(typeface);
      }
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
  }
}
