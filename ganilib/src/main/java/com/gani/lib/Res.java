package com.gani.lib;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.provider.Settings;

import com.gani.lib.logging.GLog;
import com.gani.lib.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Res {
  public static Context context() {
    return Ui.context();
  }

  public static String deviceId() {
    return Settings.Secure.getString(context().getContentResolver(), Settings.Secure.ANDROID_ID);
  }

  private static AssetManager assets() {
    return context().getAssets();
  }

  public static String assetText(String fileName) throws IOException {
//    AssetManager am =
    InputStream is = assets().open(fileName);

    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder out = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      out.append(line);
    }
    reader.close();

    return out.toString();
  }

  public static Drawable assetDrawable(String fileName) throws IOException {
    InputStream ims = assets().open(fileName);
    return Drawable.createFromStream(ims, null);
//    try {
//      // set image to ImageView
//      mImage.setImageDrawable(d);
//    }
//    catch(IOException ex) {
//      return;
//  }
  }
}
