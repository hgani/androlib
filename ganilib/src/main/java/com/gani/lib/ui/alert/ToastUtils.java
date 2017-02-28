package com.gani.lib.ui.alert;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.gani.lib.ui.Ui;

public class ToastUtils {
//  // TODO: Remove. If you can pass a context, might as well use a snack bar
//  public static void showToastInCenter(Context context, int resId) {
//    Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
//    toast.setGravity(Gravity.TOP, 0, context.getResources().getDisplayMetrics().heightPixels / 2);
//    toast.show();
//  }
//
//  // TODO: Remove. If you can pass a context, might as well use a snack bar
//  public static void showNormal(RichContainer container, int resId) {
//    showNormal(container, App.str(resId));
//  }
//
//  // TODO: Remove. If you can pass a context, might as well use a snack bar
//  public static void showNormal(RichContainer container, final String str) {
//    final RichActivity activity = container.getGActivity();
//    if (activity != null) {
//      activity.runOnUiThread(new Runnable() {
//        @Override
//        public void run() {
//          Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
//        }
//      });
//    }
//  }
//
//  // TODO: Remove. If you can pass a context, might as well use a snack bar
//  public static void showGenericSuccess(RichContainer container) {
//    showNormal(container, R.string.dialog_success);
//  }

  // Can be run from a background thread, e.g. HttpCallback.Rest.onRestSuccess()
  public static void showNormal(final String str) {
    Handler mainHandler = new Handler(Ui.context().getMainLooper());
    Runnable myRunnable = new Runnable() {
      @Override
      public void run() {
        Toast.makeText(Ui.context(), str, Toast.LENGTH_SHORT).show();
      }
    };
    mainHandler.post(myRunnable);
  }

  public static void showNormal(int strId) {
    showNormal(Ui.str(strId));
  }

//  public static void showGenericSuccess() {
//    showNormal(R.string.dialog_success);
//  }

  public static void showToastInCenter(int resId) {
    Toast toast = Toast.makeText(Ui.context(), resId, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.TOP, 0, Ui.resources().getDisplayMetrics().heightPixels / 2);
    toast.show();
  }
}
