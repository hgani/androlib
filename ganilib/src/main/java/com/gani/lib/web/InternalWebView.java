//package com.gani.lib.web;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.gani.lib.http.GImmutableParams;
//import com.thecrowdvoice.R;
//import com.thecrowdvoice.app.CvActivity;
//import com.thecrowdvoice.app.CvHttp;
//import com.thecrowdvoice.app.build.Build;
//import com.thecrowdvoice.util.io.http.CvImmutableParams;
//import com.thecrowdvoice.util.system.CvLog;
//import com.thecrowdvoice.web.GTurbolinks;
//import com.thecrowdvoice.web.WebVisit;
//
//// Only suitable for displaying content from CV server. See init().
//public class InternalWebView extends LinearLayout {
//  private WebView webView;
//  private TextView progress;
//
//  public InternalWebView(Context context) {
//    super(context);
//    init();
//  }
//
//  public InternalWebView(Context context, AttributeSet attrs) {
//    super(context, attrs);
//    init();
//  }
//
//  private void init() {
//    ViewGroup layout = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.view_internal_web, this);
//    this.progress = (TextView) layout.findViewById(R.id.label_progress);
//    this.webView = ((WebView) layout.findViewById(R.id.web_content));
//    webView.setWebViewClient(new ProgressAwareWebViewClient());
//
//    // Mimic turbolinks-android's WebView as much as possible.
//    WebSettings webSettings = webView.getSettings();
//    webSettings.setUserAgentString(CvHttp.userAgent());
//    webSettings.setJavaScriptEnabled(true);
//    webSettings.setDomStorageEnabled(true);
//    webSettings.setDatabaseEnabled(true);
//
//    // To enable js alert and confirm dialog.
//    webView.setWebChromeClient(new WebChromeClient());
//
//    // TODO: Try implementing transparent html background on the server so that the content blends in.
//    webView.setBackgroundColor(getResources().getColor(R.color.background));
//  }
//
//  public void loadPath(String path, GImmutableParams params) {
//    String fullUrl = Build.INSTANCE.getWebPrefix() + path + "?" + params.asQueryString();
//    CvLog.t(getClass(), "Load browser path: " + fullUrl);
//    webView.loadUrl(fullUrl);
//  }
//
//  public void loadPath(String path) {
//    loadPath(path, CvImmutableParams.EMPTY);
//  }
//
//
//
//  private class ProgressAwareWebViewClient extends WebViewClient {
//    @Override
//    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//      super.onPageStarted(view, url, favicon);
//    }
//
//    @Override
//    public void onPageFinished(WebView view, String url) {
//      super.onPageFinished(view, url);
//
//      progress.setVisibility(View.GONE);
//      webView.setVisibility(View.VISIBLE);
//    }
//
//    // This wasn't working on Android 5.1.1 last time it was tested, but it doesn't matter now that we use GTurbolinks on newer OSes.
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView  view, String  url){
//      Uri uri = Uri.parse(url);
//      String host = uri.getHost();
////      GLog.t(getClass(), "CLICK RUL1: " + url + " -- " + host + " -- " + Build.TABLE_HELPER.getApiDomain());
//      if (host != null) {  // Can be null for embedded base64 image.
////        if (host.equals(Build.TABLE_HELPER.getApiDomain())) {
////            GLog.t(getClass(), "CLICK RUL2: " + url + " -- " + host + " -- " + Build.TABLE_HELPER.getApiDomain());
//        if (getContext() instanceof CvActivity) {
//          GTurbolinks.handleVisit((CvActivity) getContext(), uri, WebVisit.Action.ADVANCE);
//        }
//          return true;
////        }
//      }
//      return false;
//    }
//
////    @Override
////    public void onLoadResource(WebView  view, String  url) {
////      Uri uri = Uri.parse(url);
////      String host = uri.getHost();
////      GLog.t(getClass(), "CLICK RUL1: " + url + " -- " + host + " -- " + Build.TABLE_HELPER.getApiDomain());
////      if (host != null) {  // Can be null for embedded base64 image.
////        if (host.equals(Build.TABLE_HELPER.getApiDomain())) {
////          Long postId = ScreenPostPathMatcher.extractPostIdFrom(uri);
////          if (postId != null) {
////            GLog.t(getClass(), "CLICK RUL2: " + url + " -- " + host + " -- " + Build.TABLE_HELPER.getApiDomain());
////            getContext().startActivity(DialogProgressPostInterlink.intent(postId));
////          }
////        }
////      }
////
//////      if(url.equals("http://cnn.com") ) {
//////        // do whatever you want
//////      }
////    }
//  }
//}
