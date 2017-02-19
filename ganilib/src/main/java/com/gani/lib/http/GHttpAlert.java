package com.gani.lib.http;

import android.content.Context;

import org.json.JSONException;

public abstract class GHttpAlert<HR extends GHttpResponse, RR extends GRestResponse> {
//  public enum HttpMethod {
//    POST, PATCH, DELETE
//  }

//  // Only report an error only when the error does not occur during error
//  // reporting, which would cause an endless recursion of reporting.
//  public static boolean shouldReportErrorFor(String url) {
////    GLog.d(HttpUtils.class, "Detected error when connecting to URL: " + url);
////    if (!url.startsWith(AsyncUnauthedRequests.ERROR)) {
////      GLog.d(HttpUtils.class, "Reporting error for: " + url);
////      return true;
////    }
//    return false;
//  }

//  public static String messageForJsonError(String url, JSONException ex) {
////    if (shouldReportErrorFor(url)) {
////      ErrorReporter.getInstance().reportSilently("JSON error: " + url, ex);
////    }
////    return ErrorReporter.userMessageFrom(R.string.failure_incompatible_json);
//    return "";
//  }
//
//  public static String generateMessageForCodeError(GHttpResponse r, int jsonCode) {
////    if (shouldReportErrorFor(r.getUrl())) {
////      ErrorReporter.getInstance().reportSilently("Unexpected code (" + jsonCode + "): " +
////          r.getUrl() + " returns: " + r.asString());
////    }
////    return ErrorReporter.userMessageFrom(App.str(
////        R.string.failure_unexpected_code) + ": " + jsonCode);
//    return "";
//  }

//  public static void reportCodeError(GHttpResponse r) throws JSONException {
////    generateMessageForCodeError(r, r.asRestResponse().getFirstJsonCode());
//  }
//
//  public static String generateMessageForSingleCodeError(GHttpResponse r) throws JSONException {
////    return generateMessageForCodeError(r, r.asRestResponse().getFirstJsonCode());
//    return "";
//  }

//  public static void alertMessagefulError(Context c, GHttpResponse r) throws JSONException {
////    Alert.display(c, r.asRestResponse().getJsonResult().getString(ParamKeys.MESSAGE));
//  }
//
//  public static void alertSingleCodeError(Context c, GHttpResponse r) throws JSONException {
////    Alert.display(c, generateMessageForSingleCodeError(r));
//  }
//
//  private static void alertJsonError(Context c, String url, JSONException e) {
//    GLog.e(HttpUtils.class, "JSON Error", e);
//    Toast.makeText(c, messageForJsonError(url, e), Toast.LENGTH_LONG).show();
//  }
//
//  public static void alertJsonError(Context c, GHttpResponse r, JSONException e) {
//    alertJsonError(c, r.getUrl(), e);
//  }
//
//  public static void alertJsonError(Context c, GRestResponse r, JSONException e) {
//    alertJsonError(c, r.getUrl(), e);
//  }
//
//  public static void reportJsonError(GRestResponse r, JSONException e) {
//    messageForJsonError(r.getUrl(), e);
//  }

//  public static void toast(RichContainer s, GRestResponse r) throws JSONException {
////    ToastUtils.showNormal(s, r.getJsonResult().getString(ParamKeys.MESSAGE));
//  }

  public abstract void reportCodeError(HR r) throws JSONException;
  public abstract void reportJsonError(RR r, JSONException e);
  public abstract String messageForJsonError(String url, JSONException ex);
  public abstract void alertJsonError(Context c, RR r, JSONException e);
  public abstract void alertCommonError(Context context, HR r) throws JSONException;

  public void alertFormError(Context context, HR response) {
    // Do nothing by default
  }
}
