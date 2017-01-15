package com.gani.lib.http;

import com.gani.lib.GApp;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

public class ConnectionPreparator {
  static Map<String, Object> nonNullImmutable(GParams params) {
    if (params == null) {
      return Collections.<String, Object>emptyMap();
    }
    return params.asMap();
  }

  static HttpHook nonNull(HttpHook hook) {
    if (hook == null) {
      return HttpHook.DUMMY;
    }
    return hook;
  }

  // See https://github.com/google/google-visualization-issues/issues/2209
  private static final String USER_AGENT_GOOGLE_CHART_COMPATIBILITY = "AppleWebKit/533.1";

  public static String userAgent() {
    String deviceModel = "Unknown";
    try {
      deviceModel = URLEncoder.encode(android.os.Build.MODEL, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // In theory, this should never happen
    }
    return String.format("%s/%s/%s %s/%s ad%s %s", GApp.getCodeName(), GApp.getVersionName(), GApp.getApiVersion(),
        deviceModel, android.os.Build.VERSION.RELEASE, GApp.getDeviceId(), USER_AGENT_GOOGLE_CHART_COMPATIBILITY);
  }

  // According to http://android-developers.blogspot.com.au/2011/09/androids-http-clients.html ,
  // Gingerbread adds gzip encoding when sending requests.
  static void configureCharsetAndTimeouts(HttpURLConnection connection) {
    connection.setRequestProperty("Accept-Charset", "UTF-8");
    connection.setRequestProperty("User-Agent", userAgent());
    connection.setConnectTimeout(20000);
    connection.setReadTimeout(20000);
  }
}
