//package com.gani.lib.ui.view.pager;
//
//import android.content.Context;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.util.AttributeSet;
//import android.view.View;
//
//import com.gani.lib.logging.GLog;
//import com.gani.lib.ui.view.GButton;
//import com.gani.lib.ui.view.ViewHelper;
//
//public class GViewPager extends ViewPager {
//  private ViewHelper helper;
//
//  public GViewPager(Context context) {
//    super(context);
//    init();
//  }
//
//  public GViewPager(Context context, AttributeSet attrs) {
//    super(context, attrs);
//    init();
//  }
//
//  public GViewPager adapter(PagerAdapter adapter) {
//    super.setAdapter(adapter);
//    return this;
//  }
//
//  private void init() {
//    this.helper = new ViewHelper(this);
//  }
//
////  public GViewPager size(Integer width, Integer height) {
////    helper.size(width, height);
////    return this;
////  }
//
//
//  @Override
//  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//    // find the first child view
//    View view = getChildAt(0);
//    if (view != null) {
//      // measure the first child view with the specified measure spec
//      view.measure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    setMeasuredDimension(getMeasuredWidth(), measureHeight(heightMeasureSpec, view));
//  }
//
//  /**
//   * Determines the height of this view
//   *
//   * @param measureSpec A measureSpec packed into an int
//   * @param view the base view with already measured height
//   *
//   * @return The height of the view, honoring constraints from measureSpec
//   */
//  private int measureHeight(int measureSpec, View view) {
//    int result = 0;
//    int specMode = MeasureSpec.getMode(measureSpec);
//    int specSize = MeasureSpec.getSize(measureSpec);
//
//    GLog.t(getClass(), "HEIGHT 1: ");
//    if (specMode == MeasureSpec.EXACTLY) {
//      GLog.t(getClass(), "HEIGHT 2: ");
//      result = specSize;
//    } else {
//      // set the height from the base view if available
//      if (view != null) {
//        result = view.getMeasuredHeight();
//        GLog.t(getClass(), "HEIGHT 3: " + view.getClass() + " -- " + result);
//      }
//      if (specMode == MeasureSpec.AT_MOST) {
//        GLog.t(getClass(), "HEIGHT 4: ");
//        result = Math.min(result, specSize);
//      }
//    }
//    return result;
//  }
//}
//
//
