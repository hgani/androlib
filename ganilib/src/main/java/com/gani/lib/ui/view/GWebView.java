package com.gani.lib.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gani.lib.http.GImmutableParams;
import com.gani.lib.logging.GLog;
import com.gani.lib.screen.GFragment;

//public class GWebView extends LinearLayout {
public class GWebView extends WebView {
  private ViewHelper helper;
  private GFragment fragment;

//  private ProgressBar progress;
//  private TextView progress;

  public GWebView(Context context, GFragment fragment) {
    super(context);
    init();
    this.fragment = fragment;
  }

  public GWebView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }
  
  private void init() {
    this.helper = new ViewHelper(this);



//    size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    setWebViewClient(new ProgressAwareWebViewClient());

    // Mimic turbolinks-android's WebView as much as possible.
    WebSettings webSettings = getSettings();
//    webSettings.setUserAgentString(ConnectionPreparator.userAgent());
    webSettings.setJavaScriptEnabled(true);
    webSettings.setDomStorageEnabled(true);
    webSettings.setDatabaseEnabled(true);

    // http://stackoverflow.com/questions/9055347/fitting-webpage-contents-inside-a-webview-android
    webSettings.setLoadWithOverviewMode(true);
    webSettings.setUseWideViewPort(true);

    // To enable js alert and confirm dialog.
    setWebChromeClient(new WebChromeClient());
  }

  public GWebView size(Integer width, Integer height) {
    helper.size(width, height);
    return this;
  }

  public GWebView load(String url, GImmutableParams params) {
    String fullUrl = url + ((params == null) ? "" : "?" + params.asQueryString());
    loadUrl(fullUrl);
    return this;
  }

  public GWebView load(String url) {
    return load(url, null);
  }



  private class ProgressAwareWebViewClient extends WebViewClient {
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      GLog.i(getClass(), "onPageStarted: " + url);
      super.onPageStarted(view, url, favicon);

      fragment.showProgress();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
      GLog.i(getClass(), "onPageFinished: " + url);
      super.onPageFinished(view, url);

      fragment.hideProgress();
    }

//    // This wasn't working on Android 5.1.1 last time it was tested, but it doesn't matter now that we use Turbolinks on newer OSes.
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView  view, String  url){
//      Uri uri = Uri.parse(url);
//      String host = uri.getHost();
//      if (host != null) {  // Can be null for embedded base64 image.
//        if (getContext() instanceof RichActivity) {
//          Turbolinks.handleVisit((RichActivity) getContext(), uri, WebVisit.Action.ADVANCE);
//        }
//          return true;
//      }
//      return false;
//    }
  }
}
