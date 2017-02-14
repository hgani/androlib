package com.gani.web;

import android.content.Intent;

import com.gani.lib.R;
import com.gani.lib.http.GImmutableParams;

public abstract class GScreenTurbolinks extends AbstractTurbolinksActivity {
//  public static Intent intent(PathSpec pathSpec, GImmutableParams params) {
//    return intent(GScreenTurbolinks.class, pathSpec, params);
//  }

//  public static Intent intent(PathSpec pathSpec, GImmutableParams params) {
//    if (GTurbolinks.isSupported()) {
//      return intent(GScreenTurbolinks.class, pathSpec, params);
//    }
//    return ScreenInternalBrowser.intent(pathSpec, params);
//  }
//
//  public static Intent intent(PathSpec pathSpec) {
//    return intent(pathSpec, GImmutableParams.EMPTY);
//  }

  @Override
  protected final void initViewContent() {
    setContentWithToolbar(R.layout.screen_turbolinks, false);
  }
}
