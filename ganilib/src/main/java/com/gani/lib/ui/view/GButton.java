package com.gani.lib.ui.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.gani.lib.ui.Ui;

public class GButton<T extends GButton> extends AppCompatButton {
  private static Spec defaultSpec = new Spec();

  public static void setDefaultSpec(Spec defaultSpec) {
    GButton.defaultSpec = defaultSpec;
  }

  private ViewHelper helper;
  private MediaPlayer customClickSound;

  public GButton(Context context) {
    super(context);
    init();
  }

  public GButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public T relative() {
    helper.relative();
//    setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    return self();
  }

  private void init() {
    this.helper = new ViewHelper(this);
    defaultSpec.init(this);
  }

  public T size(Integer width, Integer height) {
    helper.size(width, height);
    return self();
  }

  public T spec(Spec spec) {
    spec.init(this);
    return self();
  }

  public T background(String code) {
    background(Ui.color(code));
    return self();
  }

  public T background(int color) {
    // Alternative implementation: http://stackoverflow.com/questions/1521640/standard-android-button-with-a-different-color
     getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
//    ViewCompat.setBackgroundTintList(this, ContextCompat.getColorStateList(getContext(), colorResId));
    return self();
  }

  public T color(String code) {
    color(Ui.color(code));
    return self();
  }

  public T color(int color) {
    setTextColor(color);
    return self();
  }

  public T text(String text) {
    setText(text);
    return self();
  }

  public T textSize(float textSize) {
    setTextSize(textSize);
    return self();
  }

  public T bold() {
    return typeface(Typeface.DEFAULT_BOLD);
  }

  public T typeface(Typeface typeface) {
    setTypeface(typeface);
    return self();
  }

  public T padding(Integer left, Integer top, Integer right, Integer bottom) {
    helper.padding(left, top, right, bottom);
    return self();
  }

  public T margin(Integer left, Integer top, Integer right, Integer bottom) {
    helper.margin(left, top, right, bottom);
    return self();
  }

  public T click(final View.OnClickListener listener) {
    if (customClickSound == null) {
      helper.click(listener);
    }
    else {
      helper.click(new OnClickListener() {
        @Override
        public void onClick(View view) {
          customClickSound.start();
          listener.onClick(view);
        }
      });
    }
    return self();
  }

  public T sound(MediaPlayer customClickSound) {
    this.customClickSound = customClickSound;
//        int maxVolume = 10;
//        mp.setVolume();
//        float log1=(float)(Math.log(maxVolume?-currVolume)/Math.log(maxVolume));
//        mp.setVolume(1-log1);
    customClickSound.setVolume(1.0f, 1.0f);
    setSoundEffectsEnabled(false);
    return self();
  }

//  @Override
//  public RelativeLayout.LayoutParams getLayoutParams() {
//    return (RelativeLayout.LayoutParams) super.getLayoutParams();
//  }

  private T self() {
    return (T) this;
  }

  public T alignParentRight() {
//    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
//    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    helper.alignParentRight();
    return self();
  }



  public static class Spec {
    public void init(GButton button) {
//      button.bgColor(Ui.color(R.color.colorAccent));

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
