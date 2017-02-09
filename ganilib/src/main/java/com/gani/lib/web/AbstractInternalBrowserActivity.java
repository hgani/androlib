//package com.gani.lib.web;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.gani.lib.http.GImmutableParams;
//import com.gani.lib.screen.GActivity;
//import com.gani.lib.ui.Ui;
//import com.teamapp.teamapp.R;
//import com.teamapp.teamapp.app.App;
//
//abstract class AbstractInternalBrowserActivity extends GActivity {
//  private static final String EXTRA_PATH_SPEC = "pathSpec";
//  private static final String EXTRA_PARAMS = "params";
//
//  protected static Intent intent(Class cls, PathSpec pathSpec, GImmutableParams params) {
//    Intent intent = new Intent(Ui.context(), cls);
//    intent.putExtra(EXTRA_PATH_SPEC, pathSpec);
//    intent.putExtra(EXTRA_PARAMS, params);
//    return intent;
//  }
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    // Can't use args() before super.onCreateForScreen()
//    PathSpec pathSpec = (PathSpec) getIntent().getSerializableExtra(EXTRA_PATH_SPEC);
//    String title = pathSpec.getTitle();
//    if (title != null) {
//      // For some reason, for dialog activity, this has to be before super.onCreateForScreen()
//      setTitle(title);
//    }
//
//    super.onCreate(savedInstanceState);
//
//    initViewContent(R.layout.screen_internal_browser);
//    // TODO: refactor
////    setContentWithToolbar(R.layout.screen_internal_browser, false);
//
//    com.thecrowdvoice.view.common.InternalWebView webView = ((com.thecrowdvoice.view.common.InternalWebView) findViewById(R.id.view_web));
//    webView.loadPath(pathSpec.getPath(), args().getParams(EXTRA_PARAMS));
//  }
//
//  protected abstract void initViewContent(int resId);
//
//}
