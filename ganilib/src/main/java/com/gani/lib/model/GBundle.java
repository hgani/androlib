package com.gani.lib.model;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public class GBundle {
  private Bundle bundle;

  public GBundle(Bundle bundle) {
    this.bundle = bundle;
  }

  public final Serializable getSerializable(String key) {
    return bundle.getSerializable(key);
  }

  public final Intent getIntent(String key) {
    return (Intent) bundle.getParcelable(key);
  }

  public final String getString(String key) {
    return bundle.getString(key);
  }

  public final long getLong(String key) {
    return bundle.getLong(key);
  }

  public final boolean getBoolean(String key) {
    if (!containsKey(key)) {
      throw new IllegalArgumentException("Bundle key not specified: " + key);
    }
    return bundle.getBoolean(key);
  }

  public final boolean getBoolean(String key, boolean defaultValue) {
    return bundle.getBoolean(key, defaultValue);
  }

  public final Long getNullableLong(String key) {
    if (containsKey(key)) {
      return getLong(key);
    }
    return null;
  }

  private final boolean containsKey(String key) {
    return bundle.containsKey(key);
  }

  public final <T> Class<T> getClass(String key) {
    return (Class<T>) getSerializable(key);
  }
//  public GImmutableParams getParams(String key) {
//    return (GImmutableParams) bundle.getSerializable(key);
//  }
}
