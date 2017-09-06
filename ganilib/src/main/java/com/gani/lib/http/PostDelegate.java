package com.gani.lib.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

final class PostDelegate extends HttpDelegate {
  private static final long serialVersionUID = 1L;

  private HttpMethod method;
  private GParams params;

  PostDelegate(String nakedUrl, GImmutableParams params, HttpHook hook, HttpMethod method) {
    super(nakedUrl, hook);
    this.method = method;
    this.params = GParams.fromNullable(params);
  }
  
  @Override
  protected String getMethod() {
    return method.name();
  }
  
  @Override
  protected HttpURLConnection makeConnection() throws MalformedURLException, IOException {
    params.put("_method", getMethod());

//    params.put("authenticity_token", Credential.getInstance().getAuthenticityToken());
//
//    HttpURLConnection connection = (HttpURLConnection) new URL(getFullUrl()).openConnection();
//    ConnectionPreparator.configureCharsetAndTimeouts(connection);
//    connection.setDoOutput(true);

    HttpURLConnection connection = GHttp.instance().openConnection(getFullUrl(), params, method);
    connection.setDoOutput(true);

    // NOTE: 
    // - Apparently, not setting content length will give us a 411 when routing traffic through CloudFlare,
    //   which could be fixed by using setFixedLengthStreamingMode()
    // - However, setting a fixed length via setFixedLengthStreamingMode() is VERY dangerous, because this will cause
    //   subsequent 302 redirection to return HTTP code 400 in ICS
    // - Setting a non-fixed length via setChunkedStreamingMode(0) cause HTTP code 400 (same as above) in GingerBread
    // Therefore, we should use any of these methods
//    byte[] data = UrlUtils.paramMapToString(params).getBytes("UTF-8");
    byte[] data = params.toImmutable().asQueryString().getBytes("UTF-8");
    connection.getOutputStream().write(data);
    return connection;
  }

  @Override
  protected String getFullUrl() {
    return getUrl();
  }
}
