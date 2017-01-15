package com.gani.lib.http;

import com.gani.lib.json.GJsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class GRestResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  private String jsonString;
  private Integer jsonCode;
  private transient JSONObject jsonResult;
  private GHttpResponse httpResponse;

  protected GRestResponse(String jsonString, GHttpResponse httpResponse) {
    this.jsonString = jsonString;
    this.httpResponse = httpResponse;
  }

  public String getUrl() {
    return httpResponse.getUrl();
  }
  
  public JSONObject getJsonResult() throws JSONException {
    if (this.jsonResult == null) {
      this.jsonResult = new JSONObject(jsonString);
      //.getJSONObject(ParamKeys.RESULT);
    }
    return this.jsonResult;
  }
//
//  public GJsonObject getJResult() throws JSONException {
//    return new GJsonObject(getJsonResult());
//  }

//  public JSONArray getCodes() throws JSONException {
//    return getJsonResult().getJSONArray(ParamKeys.CODE_LIST);
//  }
//
//  public JSONArray getList() throws JSONException {
//    return getJsonResult().getJSONArray(ParamKeys.LIST);
//  }
//
//  public JSONObject getObject() throws JSONException {
//    return getJsonResult().getJSONObject(ParamKeys.OBJECT);
//  }
//
//  public JObject getJObject() throws JSONException {
//    return getJResult().getObject(ParamKeys.OBJECT);
//  }
//
//  public JArray getJList() throws JSONException {
//    return getJResult().getArray(ParamKeys.LIST);
//  }
//
//  public long getId() throws JSONException {
//    return getJsonResult().getLong(ParamKeys.ID);
//  }
//
//  public boolean isObjectNull() throws JSONException {
//    return getJsonResult().isNull(ParamKeys.OBJECT);
//  }
//
//  public int getFirstJsonCode() throws JSONException  {
//    if (this.jsonCode == null) {
//      try {
//        this.jsonCode = Integer.valueOf(getCodes().getString(0));
//      }
//      catch (NumberFormatException e) {
//        throw new JSONException("Invalid number for JSON return code: " + e.getMessage());
//      }
//    }
//    return this.jsonCode.intValue();
//  }
//
//  boolean isAuthenticationRequired() throws JSONException {
//    return getFirstJsonCode() == ResponseCodes.SIGNIN_REQUIRED;
//  }
//
//  boolean isAuthenticationRejected() throws JSONException {
//    return getFirstJsonCode() == ResponseCodes.SIGNIN_FAILURE;
//  }
//
  public boolean isAllOk() throws JSONException {
//    return getFirstJsonCode() == ResponseCodes.SUCCESS;
    return true;
  }
//
//  public boolean isErrorWithMessage() throws JSONException {
//    return getFirstJsonCode() == ResponseCodes.ERROR_WITH_MESSAGE;
//  }
  
  public String getJsonString() {
    return jsonString;
  }

  @Override
  public String toString() {
    return jsonString;
  }
}