package com.gani.lib.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;

// TODO: Rename to res because this is not UI-specific, e.g. int(), context()
public class Ui {
  private static Context appContext;
  private static Resources resources;
  private static Handler uiHandler;

  public static void init(Context c, Resources r, Handler h) {
    appContext = c;
    resources = r;
    uiHandler = h;
  }

  public static Context context() {
    return appContext;
  }

  public static Resources resources() {
    return resources;
  }

  public static Drawable drawable(int resId) {
    return ((Build.VERSION.SDK_INT >= 21) ?
        appContext.getDrawable(resId) : resources.getDrawable(resId));
  }

  public static String str(int resId, Object... formatArgs) {
    return appContext.getString(resId, formatArgs);
  }

  public static String quantityStr(int resId, int quantity, Object... formatArgs) {
    return appContext.getResources().getQuantityString(resId, quantity, formatArgs);
  }

  public static int dimen(int resId) {
    return (int) resources.getDimension(resId);
  }

  public static int color(int resId) {
    return resources.getColor(resId);
  }

  public static int integer(int resId) {
    return resources.getInteger(resId);
  }

  // Use this to:
  // Ensure that async task is created and executed on UI thread and
  // Ensure that listeners are executed on UI thread
  public static void run(Runnable command) {
    uiHandler.post(command);
  }
}
