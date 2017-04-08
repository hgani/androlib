//package com.gani.lib.ui.view.pager;
//
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.support.v4.view.PagerAdapter;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.gani.lib.logging.GLog;
//import com.gani.lib.ui.Ui;
//import com.gani.lib.ui.view.GImageView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DrawablePagerAdapter extends PagerAdapter {
////    private ArrayList<Integer> children;
//    private List<Drawable> drawables;
//
//    public DrawablePagerAdapter(int... resources) {
//      drawables = new ArrayList<>(resources.length);
//      for (int res : resources) {
//        drawables.add(Ui.drawable(res));
//      }
//
////      this.drawables = drawables;
////      this.children = new ArrayList<>();
////      for (ComponentController controller : builder.getChildren()) {
////        children.add(controller.view());
////      }
//    }
//
//    @Override
//    public int getCount() {
////      return children.size();
//      return drawables.size();
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
////      View view = children.get(position);
//      GLog.t(getClass(), "* INSTANTIE " + position);
//      View view = new GImageView(container.getContext()).size(null, 20).adjustViewBounds().drawable(drawables.get(position)).bgColor(Color.RED);
//      container.addView(view);
//      return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//      container.removeView((View) object);
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//      return view == object;
//    }
//  }