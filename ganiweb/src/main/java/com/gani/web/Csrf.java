package com.gani.web;

import com.gani.lib.database.GDbValue;
import com.gani.lib.http.GHttp;
import com.gani.lib.http.GHttpCallback;
import com.gani.lib.http.GHttpError;
import com.gani.lib.http.GHttpResponse;
import com.gani.lib.http.HttpMethod;
import com.gani.lib.logging.GLog;
import com.gani.web.htmlform.HtmlForm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Csrf {
  public static final String TOKEN_DBKEY = "__csrf_token";
  private static final Csrf INSTANCE = new Csrf();

  public static Csrf instance() {
    return INSTANCE;
  }

  private String url;
  private String token;

  public void init(String url) {
    this.url = url;

    download(null);
  }

  private void download(final Callback callback) {
    HttpMethod.GET.async(url, null, new GHttpCallback() {
      @Override
      public void onHttpSuccess(GHttpResponse response) {
        if (!response.hasError()) {
          Document document = Jsoup.parse(response.asString());

          token = HtmlForm.parseCsrfToken(document);
          if (token == null) {
            fail();
//            GLog.i(getClass(), "CSRF token not received");
          }
          else {
            GLog.i(getClass(), "CSRF token received");
            GDbValue.set(TOKEN_DBKEY, token);
            if (callback != null) {
              callback.onTokenReceived(token);
            }
          }
        } else {
          fail();
//          GLog.i(getClass(), "CSRF token not received");
          //          GHttp.instance().alertHelper().alertFormParsingError(mContext, response);
        }
//        GDbValue.set(TOKEN_DBKEY, );
      }

      @Override
      public void onHttpFailure(GHttpError error) {
//        GLog.i(getClass(), "CSRF token not received");
        fail();
      }
    }).execute();
  }

  private void fail() {
    GLog.i(getClass(), "CSRF token not received");
  }

  public void executeAsync(Callback callback) {
    if (token == null) {
      download(callback);
    }
    else {
      callback.onTokenReceived(token);
    }
//    String token = GDbValue.getString(TOKEN_DBKEY);
  }

  public String getToken() {
    return token;
  }

  public interface Callback {
    void onTokenReceived(String token);
  }
}
