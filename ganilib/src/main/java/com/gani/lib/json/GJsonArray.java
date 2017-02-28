package com.gani.lib.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;

public abstract class GJsonArray<JO extends GJsonObject> implements Iterable<JO> {
  private JO[] elements;

//  public GJsonArray(JO element) throws JSONException {
//    this(new JO[] { element });
//  }

  public GJsonArray(JO[] elements) throws JSONException {
    this.elements = elements;
  }

  public GJsonArray(JSONArray backend) throws JSONException {
//    this.elements = new GJsonObject[backend.length()];
    this.elements = createArray(backend.length());
    for (int i = 0; i < elements.length; ++i) {
//      elements[i] = new GJsonObject(backend.getJSONObject(i));
      elements[i] = createObject(backend.getJSONObject(i));
    }
  }

  protected abstract JO[] createArray(int length);
  protected abstract JO createObject(JSONObject object);

  public int size() {
    return elements.length;
  }

  public boolean isEmpty() {
    return elements.length == 0;
  }

  @Override
  public String toString() {
    return Arrays.toString(elements);
  }

  public Iterator<JO> iterator() {
    return new Iterator<JO>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < elements.length;
      }

      @Override
      public JO next() {
        return elements[index++];
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

//  public JArray ungroup() throws JSONException {
//    List<JObject> objects = new LinkedList<>();
//    for (JObject group : this) {
//      JArray arr = group.getArray(ParamKeys.LIST);
//
//        for (JObject obj : arr) {
//          objects.add(obj);
//        }
//    }
//
//    return new JArray(objects.toArray(new JObject[objects.size()]));
////    return insertRows(URIS, rows);
//  }


  static class Default extends GJsonArray<GJsonObject.Default> {
    Default(JSONArray array) throws JSONException {
      super(array);
    }

    @Override
    protected GJsonObject.Default[] createArray(int length) {
      return new GJsonObject.Default[length];
    }

    @Override
    protected GJsonObject.Default createObject(JSONObject object) {
      return new GJsonObject.Default(object);
    }
  }
}
