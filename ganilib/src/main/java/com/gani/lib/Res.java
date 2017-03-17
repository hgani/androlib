package com.gani.lib;

import android.content.res.AssetManager;

import com.gani.lib.logging.GLog;
import com.gani.lib.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Res {
  public static String assetText(String fileName) throws IOException {
    AssetManager am = Ui.context().getAssets();
    InputStream is = am.open(fileName);

    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder out = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      out.append(line);
    }
    reader.close();

    return out.toString();

//      GLog.t(Questions.class, "Loading JSON file3: " + out.toString());
  }
}
