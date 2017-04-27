package com.gani.lib.ui.view.pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.gani.lib.ui.Ui;

import java.util.Timer;
import java.util.TimerTask;

public class GWrapHeightViewPager extends ViewPager {
  /**
   * Constructor
   *
   * @param context the context
   */
  public GWrapHeightViewPager(Context context) {
    super(context);
  }

  /**
   * Constructor
   *
   * @param context the context
   * @param attrs the attribute set
   */
  public GWrapHeightViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public GWrapHeightViewPager adapter(PagerAdapter adapter) {
    super.setAdapter(adapter);
    return this;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int height = 0;

    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);

      child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

      int h = child.getMeasuredHeight();

      if (h > height) height = h;
    }

    heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  private Timer timer;

  // See http://stackoverflow.com/questions/17610085/how-to-switch-automatically-between-viewpager-pages
  public GWrapHeightViewPager autoScroll(int millis) {
    if (timer == null) {
      timer = new Timer(); // At this line a new Thread will be created
      timer.scheduleAtFixedRate(new RemindTask(), 0, millis);
    }
    return this;
  }

  private class RemindTask extends TimerTask {
    private int page = 0;

    @Override
    public void run() {
      // As the TimerTask run on a seprate thread from UI thread we have
      // to call runOnUiThread to do work on UI thread.
      Ui.run(new Runnable() {
        public void run() {
          page++;

          if (page >= getAdapter().getCount()) {
            page = 0;
//            timer.cancel();
//            // Showing a toast for just testing purpose
//            Toast.makeText(getApplicationContext(), "Timer stoped",
//                Toast.LENGTH_LONG).show();
          }

          GWrapHeightViewPager.this.setCurrentItem(page);
        }
      });
    }
  }
}


