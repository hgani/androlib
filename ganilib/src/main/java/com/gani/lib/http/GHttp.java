package com.gani.lib.http;

import android.webkit.WebView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class GHttp {
  private static GHttp instance;

  public static void register(GHttp i) {
    instance = i;
  }

  public static GHttp instance() {
    return instance;
  }

  protected abstract String networkErrorMessage();

//  protected abstract void signOut();
//  protected abstract void reauthenticate(GHttpResponse origResponse);

  public HttpURLConnection openConnection(String url, GParams params, HttpMethod method) throws MalformedURLException, IOException {
    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    prepareConnection(connection, params, method);
    return connection;
//    ConnectionPreparator.configureCharsetAndTimeouts(connection);
//    connection.setDoOutput(true);
  }


  protected abstract void prepareConnection(HttpURLConnection connection, GParams params, HttpMethod method);
//  protected abstract void prefetchAfterLogin();
//  protected abstract HttpAsyncPost signinRequest(GParams params);
//  protected abstract String authUrl();
//  protected abstract void onJsonSuccess(GRestResponse r) throws JSONException;
  protected abstract GHttpResponse createHttpResponse(String url);

  public abstract GHttpAlert alertHelper();

  public void prepareWebView(WebView webView) {
    // To be overridden.
  }

//  public TurbolinksSessionWrapper prepareTlSession(TurbolinksSessionWrapper session) {
//    return session;
//  }



//  public void handleTlVisit() {
//
//  }

//  public void handleTlError(int errorCode) {
//
//  }

//  protected void signout() {

//    // Nothing to do by default
//  }
}
