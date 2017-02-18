//package com.gani.web;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.basecamp.turbolinks.TurbolinksView;
//import com.gani.lib.R;
//import com.gani.lib.http.GImmutableParams;
//import com.gani.lib.screen.GActivity;
//import com.gani.lib.ui.Ui;
//
//abstract class AbstractTurbolinksActivity extends GActivity {
//  public static final String EXTRA_PATH_SPEC = "specPath";
//  public static final String EXTRA_PARAMS = "params";
//
//  private GTurbolinks turbolinks;
//  private FileUploadSupport fileUploadSupport;
////
////  protected static Intent intent(Class cls, PathSpec pathSpec, GImmutableParams params) {
////    Intent intent = new Intent(Ui.context(), cls);
////    intent.putExtra(EXTRA_PATH_SPEC, pathSpec);
////    intent.putExtra(EXTRA_PARAMS, params);
////    return intent;
////  }
//
//  @Override
//  public final void onCreate(Bundle savedInstanceState) {
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
//    initViewContent();
//
////    this.turbolinks = new GTurbolinks(this, ((TurbolinksView) findViewById(R.id.turbolinks_view)),
////        pathSpec.getPath() + "?" + args().getParams(EXTRA_PARAMS).asQueryString());
//
////    MutableContextWrapper contextWrapper = (MutableContextWrapper) turbolinks.getWebView().getContext();
////    contextWrapper.setBaseContext(this);
//
//    this.fileUploadSupport = new FileUploadSupport(this, turbolinks.getWebView());
//
////    fileUploadSupport.onActivityCreate(turbolinks.getWebView());
//
//    fileUploadSupport.register();
//    turbolinks.visit();
//  }
//
//  protected abstract GTurbolinks createTurbolinks(GTurbolinksSupportActivity activity, TurbolinksView view, String path);
//
//  @Override
//  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//    super.onActivityResult(requestCode, resultCode, intent);
//
//    fileUploadSupport.onActivityResult(requestCode, resultCode, intent);
//  }
//
//  protected abstract void initViewContent();
//
//  @Override
//  protected final void onRestart() {
//    super.onRestart();
//
//    fileUploadSupport.register();  // Reattach webview to this activity.
//    turbolinks.restore();
//  }
//}
