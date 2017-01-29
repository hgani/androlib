package com.gani.lib.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


class GetDelegate extends HttpDelegate {
  private static final long serialVersionUID = 1L;

//  private Map<String, Object> params;
  private GParams params;
  private HttpMethod method;

  GetDelegate(String nakedUrl, GImmutableParams params, HttpHook hook) {
    super(nakedUrl, hook);

//    this.params = ConnectionPreparator.nonNullImmutable(params);
    this.params = GParams.fromNullable(params);
    this.method = HttpMethod.GET;
  }
  
//  Map<String, Object> getParams() {
//    return params;
//  }
//  GParams getParams() {
//    return params;
//  }
  Object getParam(String key) {
    return params.get(key);
  }

  @Override
  protected String getMethod() {
    return method.name();
  }
  
  @Override
  protected HttpURLConnection makeConnection() throws MalformedURLException, IOException {
//    return makeConnection(getFullUrl());
    return GHttp.instance().openConnection(getFullUrl(), params, method);
  }

  @Override
  protected String getFullUrl() {
    if (params.size() <= 0) {
      return getUrl();
    }
//    return getUrl() + "?" + UrlUtils.paramMapToString(params);
    return getUrl() + "?" + params.toImmutable().asQueryString();
  }
  
//  static HttpURLConnection makeConnection(String url) throws MalformedURLException, IOException {
//    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//    ConnectionPreparator.configureCharsetAndTimeouts(connection);
//    return connection;
//  }
}
