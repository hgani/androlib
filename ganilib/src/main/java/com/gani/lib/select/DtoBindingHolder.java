package com.gani.lib.select;

import android.view.View;

import com.gani.lib.ui.list.DbCursorRecyclerAdapter;

public abstract class DtoBindingHolder<DO> extends DbCursorRecyclerAdapter.AbstractBindingHolder {
  public DtoBindingHolder(View view, boolean selectable) {
    super(view, selectable);
  }

  public abstract void update(DO object);
}