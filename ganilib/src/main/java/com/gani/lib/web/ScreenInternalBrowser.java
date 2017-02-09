//package com.gani.lib.web;
//
//import android.content.Intent;
//
//import com.gani.lib.http.GImmutableParams;
//import com.thecrowdvoice.R;
//import com.thecrowdvoice.app.constant.PathSpec;
//import com.thecrowdvoice.util.io.http.CvImmutableParams;
//
//public class ScreenInternalBrowser extends AbstractInternalBrowserActivity {
//  public static Intent intent(PathSpec pathSpec, GImmutableParams params) {
//    return intent(ScreenInternalBrowser.class, pathSpec, params);
//  }
//
//  public static Intent intent(PathSpec pathSpec) {
//    return intent(pathSpec, CvImmutableParams.EMPTY);
//  }
//
//  public static Intent intent(String path) {
//    return intent(new PathSpec(path));
//  }
//
//  @Override
//  protected void initViewContent(int resId) {
//    setContentWithToolbar(R.layout.screen_internal_browser, false);
//  }
//}
