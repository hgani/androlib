package com.gani.lib.ui.view.pager;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.gani.lib.ui.Ui;
import com.gani.lib.ui.view.GImageView;

import java.util.ArrayList;
import java.util.List;

public class DrawablePagerAdapter extends PagerAdapter {
    private List<Drawable> drawables;

    public DrawablePagerAdapter(int... resources) {
      drawables = new ArrayList<>(resources.length);
      for (int res : resources) {
        drawables.add(Ui.drawable(res));
      }
    }

    @Override
    public int getCount() {
      return drawables.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      View view = new GImageView(container.getContext()).size(null, 20).adjustViewBounds().drawable(drawables.get(position));
      container.addView(view);
      return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }
  }