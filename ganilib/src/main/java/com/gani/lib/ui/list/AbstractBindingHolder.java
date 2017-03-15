package com.gani.lib.ui.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gani.lib.R;
import com.gani.lib.ui.Ui;

public abstract class AbstractBindingHolder extends RecyclerView.ViewHolder {
    private View layout;

    public AbstractBindingHolder(View view, boolean selectable) {
      super(view);
      this.layout = view;

      if (selectable) {
        unhighlightSelectable();
      }
    }

    public View getLayout() {
      return layout;
    }

    public Context getContext() {
      return layout.getContext();
    }

    protected static View inflate(ViewGroup parent, int layoutId) {
      return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
//
//      return DbCursorRecyclerAdapter.inflate(parent, layoutId);
    }

    protected void unselectable() {
      layout.setBackgroundDrawable(Ui.resources().getDrawable(R.color.transparent));
    }

    protected void highlightSelectable() {
      layout.setBackgroundDrawable(Ui.resources().getDrawable(R.drawable.background_post_highlight_selector));
    }

    protected void unhighlightSelectable() {
      // See http://stackoverflow.com/questions/8732662/how-to-set-background-highlight-to-a-linearlayout/28087443#28087443
      TypedValue outValue = new TypedValue();
      Ui.context().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
      layout.setBackgroundResource(outValue.resourceId);
    }
  }
