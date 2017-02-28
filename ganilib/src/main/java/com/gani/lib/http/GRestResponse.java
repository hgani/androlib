package com.gani.lib.http;

import com.gani.lib.json.GJsonObject;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class GRestResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  private String jsonString;
  private transient JSONObject jsonResult;
  private GHttpResponse httpResponse;

  protected GRestResponse(String jsonString, GHttpResponse httpResponse) {
    this.jsonString = jsonString;
    this.httpResponse = httpResponse;
  }

  public String getUrl() {
    return httpResponse.getUrl();
  }
  
  private JSONObject getJsonResult() throws JSONException {
    if (this.jsonResult == null) {
      this.jsonResult = new JSONObject(jsonString);
    }
    return this.jsonResult;
  }

  public GJsonObject getResult() throws JSONException {
    return new GJsonObject.Default(getJsonResult());
  }

  public boolean isAllOk() throws JSONException {
    return true;
  }
  
  public String getJsonString() {
    return jsonString;
  }

  @Override
  public String toString() {
    return jsonString;
  }
}