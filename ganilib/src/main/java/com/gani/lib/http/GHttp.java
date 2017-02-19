package com.gani.lib.http;

import android.webkit.WebView;

import com.gani.lib.io.PersistentCookieStore;
import com.gani.lib.io.WebkitCookieManagerProxy;
import com.gani.lib.ui.Ui;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
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

  protected GHttp() {
    initPermanentCookieHandler();
  }

  private static void initPermanentCookieHandler() {
    android.webkit.CookieSyncManager.createInstance(Ui.context());

    // Use ACCEPT_ALL instead of ACCEPT_ORIGINAL_SERVER so that it is cross-subdomain.
    CookieManager defaultManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
    PersistentCookieStore cookieStore = new PersistentCookieStore(defaultManager.getCookieStore());

    // See http://stackoverflow.com/questions/18057624/two-way-sync-for-cookies-between-httpurlconnection-java-net-cookiemanager-and
    CookieHandler.setDefault(new WebkitCookieManagerProxy(cookieStore, java.net.CookiePolicy.ACCEPT_ALL));
  }

  protected abstract String networkErrorMessage();

  public HttpURLConnection openConnection(String url, GParams params, HttpMethod method) throws MalformedURLException, IOException {
    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    prepareConnection(connection, params, method);
    return connection;
  }

  protected abstract void prepareConnection(HttpURLConnection connection, GParams params, HttpMethod method);
  protected abstract GHttpResponse createHttpResponse(String url);

  public abstract GHttpAlert alertHelper();
  public abstract String baseUrl();

  public void prepareWebView(WebView webView) {
    // To be overridden.
  }
}
