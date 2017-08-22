package com.gani.lib.http;

import android.content.Context;
import android.widget.Toast;

import com.gani.lib.logging.GLog;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;

public class GHttpError<HR extends GHttpResponse> implements Serializable {
  private static final long serialVersionUID = 1L;

  public enum ErrorType {
    NETWORK,
    CODE,
    JSON,
    AUTH
  }

  private HR response;
  private String message;
  private ErrorType type;
  private Integer code;

  public GHttpError(HR response) {
    this.type = null;
    this.response = response;
  }

  String getUrl() {
    return response.getUrl();
  }

  public HR getResponse() {
    return response;
  }

  public String getMessage() {
    return message;
  }
  
  public boolean hasError() {
    return type != null;
  }

  private GHttpError setError(ErrorType type, String reportMessage, String logMessage) {
    GLog.e(getClass(), logMessage);
    this.type = type;
    this.message = reportMessage;
    return this;
  }
  
  private GHttpError setError(ErrorType type, String message) {
    return setError(type, message, message);
  }

  public ErrorType getType() {
    return type;
  }

  public Integer getCode() {
    return code;
  }

  public GHttpError markForJson(JSONException e) {
    return setError(ErrorType.JSON, GHttp.instance().alertHelper().messageForJsonError(getUrl(), e));
  }
  
  GHttpError markForCode(int code) {
    this.code = code;
    return setError(ErrorType.CODE, GHttp.instance().networkErrorMessage() + " Code: " + code, "HTTP error code: " + code);
  }

  GHttpError markForNetwork(IOException e) {
    return setError(ErrorType.NETWORK, GHttp.instance().networkErrorMessage(), "HTTP exception: " + e);
  }

  // The logout part relies on handleDefault() which means that this only applies for foreground network operation (when activity is involved).
  public GHttpError markForAuth(String logMessagePrefix) {
    return setError(ErrorType.AUTH, ErrorType.AUTH.name(), logMessagePrefix + " -- logging out (" + getUrl() + ") ...");
  }

  // TODO: Review. This seems to handle multiple types of error, the distinction is unclear.
  public void handleDefault(Context context) {
    // To be overidden
  }



  public static class Default extends GHttpError<GHttpResponse> {
    public Default(GHttpResponse response) {
      super(response);
    }

    @Override
    public void handleDefault(Context context) {
      Toast.makeText(context, getMessage(), Toast.LENGTH_LONG).show();
    }
  }
}
