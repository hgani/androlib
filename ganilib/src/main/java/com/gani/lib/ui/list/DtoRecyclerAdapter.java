package com.gani.lib.ui.list;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

import com.gani.lib.R;
import com.gani.lib.logging.GLog;
import com.gani.lib.select.DtoBindingHolder;
import com.gani.lib.ui.Ui;

import java.util.List;

import static com.gani.lib.ui.list.AbstractBindingHolder.inflate;

public abstract class DtoRecyclerAdapter<DO, VH
    extends DtoBindingHolder<DO>> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private List<DO> objects;

  protected DtoRecyclerAdapter(List<DO> objects) {
    this.objects = objects;
  }

  public void initForList(RecyclerView recyclerView) {
    initForList(recyclerView, true);
  }

  public RecyclerListHelper initForList(RecyclerView recyclerView, boolean withSeparator) {
    Context context = recyclerView.getContext();
    if (withSeparator) {
      recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
    }
    recyclerView.setAdapter(this);
    return new RecyclerListHelper(recyclerView);
  }

  private boolean isPositionHeader(int position) {
    return position == 0;
  }

  private boolean isPositionFooter(int position) {
    return position == getItemCount() - 1;
  }

  @Override
  public final int getItemViewType(int position) {
    if (isPositionHeader(position)) {
      return R.id.listitem_header;
    } else if (isPositionFooter(position)) {
      return R.id.listitem_footer;
    }
    return determineViewType(getItem(position - 1));
  }

  // Should return 1 or higher
  protected int determineViewType(DO item) {
    return R.id.listitem_normal;
  }

  @Override
  public final void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
    if (isPositionHeader(i)) {
      ((GenericBindingHolder) holder).update();
    }
    else if (isPositionFooter(i)) {
      ((GenericBindingHolder) holder).update();
    }
    else {
      ((VH) holder).update(getItem(i - 1));
    }
  }

  @Override
  public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // Need to use `if` instead of `switch`. See http://stackoverflow.com/questions/8476912/menu-item-ids-in-an-android-library-project
    if (viewType == R.id.listitem_header) {
      return onCreateHeaderHolder(parent);
    }
    else if (viewType == R.id.listitem_footer) {
      return onCreateFooterHolder(parent);
    }
    else {
      return onCreateItemHolder(parent, viewType);
    }
  }

  protected abstract VH onCreateItemHolder(ViewGroup parent, int viewType);

  protected RecyclerView.ViewHolder onCreateHeaderHolder(ViewGroup parent) {
    return new BlankGenericItemHolder(parent);
  }

  protected RecyclerView.ViewHolder onCreateFooterHolder(ViewGroup parent) {
    return  new BlankGenericItemHolder(parent);
  }

  @Override
  public int getItemCount() {
    return objects.size() + 2;  // Header and footer
  }

//  /**
//   * @see android.widget.ListAdapter#getItemId(int)
//   */
//  @Override
//  public long getItemId(int position) {
////    if (mDataValid && mCursor != null) {
////      if (mCursor.moveToPosition(position)) {
////        return mCursor.getLong(mRowIDColumn);
////      } else {
////        return 0;
////      }
////    } else {
////      return 0;
////    }
//    return objects.get(position);
//  }

  public DO getItem(int position) {
    return objects.get(position);
  }



//  public static abstract class GenericBindingHolder extends AbstractBindingHolder {
//    public GenericBindingHolder(View view, boolean selectable) {
//      super(view, selectable);
//    }
//
//    protected abstract void update(DbCursorRecyclerAdapter.State state);
//  }

  public static abstract class GenericBindingHolder extends AbstractBindingHolder {
    public GenericBindingHolder(View view, boolean selectable) {
      super(view, selectable);
    }

    protected abstract void update();
  }

  public static class BlankGenericItemHolder extends GenericBindingHolder {
    public BlankGenericItemHolder(ViewGroup parent) {
      super(inflate(parent, R.layout.blank), false);
    }

    @Override
    protected void update() {

    }
  }
}