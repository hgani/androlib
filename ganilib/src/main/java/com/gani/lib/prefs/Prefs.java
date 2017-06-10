package com.gani.lib.prefs;

import android.content.SharedPreferences;

public class Prefs {
  private SharedPreferences backend;

  public Prefs(SharedPreferences backend) {
    this.backend = backend;
  }

  public String getNullableString(String key) {
    return backend.getString(key, null);
  }

  public void setString(String key, String value) {
    SharedPreferences.Editor editor = backend.edit();
    editor.putString(key, value);
    editor.apply();
  }
}
