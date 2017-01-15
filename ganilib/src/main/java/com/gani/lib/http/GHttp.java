package com.gani.lib.http;

public abstract class GHttp {
  private static GHttp instance;

  public static void register(GHttp i) {
    instance = i;
  }

  public static GHttp instance() {
    return instance;
  }

  protected abstract String networkErrorMessage();

  protected abstract void signOut();
//  protected abstract void reauthenticate(GHttpResponse origResponse);
  protected abstract void prepareForPost(GParams params);
  protected abstract void prefetchAfterLogin();
  protected abstract HttpAsyncPost signinRequest(GParams params);
  protected abstract String authUrl();
//  protected abstract void onJsonSuccess(GRestResponse r) throws JSONException;
  protected abstract GHttpResponse createHttpResponse(String url);

  public abstract GHttpAlert alertHelper();

//  protected void signout() {

//    // Nothing to do by default
//  }
}
