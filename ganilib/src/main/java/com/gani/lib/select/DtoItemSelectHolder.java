package com.gani.lib.select;

import android.view.View;
import android.widget.CheckBox;

import com.gani.lib.R;

public abstract class DtoItemSelectHolder<I extends SelectableItem> extends DtoBindingHolder<I> {
  private ItemSelectScreenHelper<I, ?> helper;
//  private boolean multiselect;
  private CheckBox selectButton;

  protected DtoItemSelectHolder(View view, ItemSelectScreenHelper<I, ?> activity, boolean multiselect) {
    super(view, false);

    this.helper = activity;
//    this.multiselect = multiselect;
    this.selectButton = (CheckBox) getLayout().findViewById(R.id.toggle_select);
  }

  @Override
  public void update(I item) {
    selectButton.setChecked(helper.getMutableSelectedItems().contains(item));
    selectButton.setOnCheckedChangeListener(helper.new ActivityNotifier(item));
  }

//  @Override
//  public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
//    if (view.getId() == R.id.toggle_select) {
//      CheckBox selectButton = (CheckBox) view;
//      selectButton.setOnCheckedChangeListener(null); // Null out listener in case we're reusing a view that possesses a previously set listener
//
//      I item = createSelectableItem(cursor);
//
//      selectButton.setChecked(helper.getMutableSelectedItems().contains(item));
//      selectButton.setOnCheckedChangeListener(new ActivityNotifier(item));
//
//      return true;
//    }
//
//    return false;
//  }



}
