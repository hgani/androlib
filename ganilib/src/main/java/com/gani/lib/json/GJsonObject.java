package com.gani.lib.json;

import android.graphics.Color;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gani.lib.logging.GLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class GJsonObject<JO extends GJsonObject, JA extends GJsonArray> {
  private JSONObject backend;

  // NOTE: Might not be needed anymore in the future
  protected GJsonObject(JSONObject backend) {
    this.backend = backend;
  }

  protected GJsonObject(String jsonString) throws JSONException {
    this(new JSONObject(jsonString));
  }

  // Return GJsonArray instead of List<GJsonObject> so we can provide additional info such as
  // overriding toString().
  @NonNull
  public JA getArray(String name) throws JSONException {
    return createArray(getRawArray(name));
  }

  protected abstract JA createArray(JSONArray array) throws JSONException;

  @NonNull
  private JSONArray getRawArray(String name) throws JSONException  {
    return backend.getJSONArray(name);
  }

  @NonNull
  public String[] getStringArray(String name) throws JSONException {
    JSONArray arr = backend.getJSONArray(name);
    String[] elements = new String[arr.length()];
    for (int i = 0; i < elements.length; ++i) {
      elements[i] = arr.getString(i);
    }
    return elements;
  }

  @NonNull
  public JO getObject(String name) throws JSONException {
    return createObject(getRawObject(name));
  }

  protected abstract JO createObject(JSONObject object);

  @NonNull
  private final JSONObject getRawObject(String name) throws JSONException  {
    return backend.getJSONObject(name);
  }

  protected final boolean isNull(String name) {
    return backend.isNull(name);
  }

  @Nullable
  public JO getNullableObject(String name) throws JSONException {
    return isNull(name) ? null : getObject(name);
  }

  // TODO: Deprecate. As much as possible, use getImage()
//  public String getImageUrl(String name) throws JSONException {
//    return ImageGetRequest.toImageUrl(getString(name));
//  }

//  public Image getImage(String name) throws JSONException {
//    return Image.fromKey(getString(name));
//  }
//
//  public Image getImage(String name) throws JSONException {
//    return backend.isNull(name) ? null : Image.fromKey(getString(name));
//  }
//
//  public Images getImages(String name) throws JSONException {
//    return Images.fromJson(getStringArray(name));
//  }

  @NonNull
  public List<Long> getLongs(String name) throws JSONException {
    JSONArray array = backend.getJSONArray(name);
    List<Long> elements = new ArrayList<Long>(array.length());
    for (int i = 0; i < array.length(); ++i) {
      elements.add(array.getLong(i));
    }
    return elements;
  }

  @NonNull
  public String getString(String name) throws JSONException {
    return backend.getString(name);
  }

  @Nullable
  public String getNullableString(String name) {
    try {
      // isNull() is needed to check if the property is explicitly specified as null.
      // If the property is not specified (i.e. undefined), we'll get JSONException.
      return backend.isNull(name) ? null : getString(name);
    }
    catch (JSONException e) {
      return null;
    }
  }

  @NonNull
  public long getLong(String name) throws JSONException {
    return backend.getLong(name);
  }

  @NonNull
  public int getInt(String name) throws JSONException {
    return backend.getInt(name);
  }

  @NonNull
  public boolean getBoolean(String name) throws JSONException {
    return backend.getBoolean(name);
  }

  @NonNull
  public double getDouble(String name) throws JSONException {
    return backend.getDouble(name);
  }

  @Nullable
  public Integer getNullableInt(String name) {
    try {
      return backend.isNull(name) ? null : getInt(name);
    }
    catch (JSONException e) {
      return null;
    }
  }

  @Nullable
  public Long getNullableLong(String name) {
    try {
      return backend.isNull(name) ? null : getLong(name);
    }
    catch (JSONException e) {
      return null;
    }
  }

  @Nullable
  public Double getNullableDouble(String name) {
    try {
      return backend.isNull(name) ? null : getDouble(name);
    }
    catch (JSONException e) {
      return null;
    }
  }

  @Nullable
  public Boolean getNullableBoolean(String name) {
    try {
      return backend.isNull(name) ? null : getBoolean(name);
    }
    catch (JSONException e) {
      return null;
    }
  }

  private String expandColorIfNecessary(String code) {
    if (code.length() == 3) {
      String result = "";
      for (char c : code.toCharArray()) {
        result += ("" + c + c);
      }
      return result;
    }
    return code;
  }

  @Nullable
  public Integer getNullableColor(String name) {
    String code = getNullableString(name);
    if (code != null) {
      if (code.startsWith("#")) {
        code = "#" + expandColorIfNecessary(code.substring(1));
      }
      try {
        return Color.parseColor(code);
      }
      catch (IllegalArgumentException e) {
        // Do nothing
      }
    }
    return null;
  }

  @Nullable
  public JA getNullableArray(String name) {
    try {
      return backend.isNull(name) ? null : getArray(name);
    }
    catch (JSONException e) {
      return null;
    }
  }

  @Override
  @NonNull
  public String toString() {
    return backend.toString();
  }
}

