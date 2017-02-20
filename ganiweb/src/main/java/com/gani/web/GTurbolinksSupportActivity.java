package com.gani.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gani.lib.R;
import com.gani.lib.analytics.Tracker;
import com.gani.lib.analytics.TrackingSpec;
import com.gani.lib.http.GImmutableParams;
import com.gani.lib.model.GBundle;
import com.gani.lib.screen.GActivity;
import com.gani.lib.screen.GFragment;
import com.gani.lib.screen.GScreenView;
import com.gani.lib.ui.ProgressIndicator;
import com.gani.lib.ui.Ui;
import com.gani.web.GTurbolinks;
import com.gani.web.PathSpec;

import java.io.Serializable;

public abstract class GTurbolinksSupportActivity extends GActivity {
  private GTurbolinks turbolinks;



  ///// Turbolinks /////

  public void registerTurbolinksRestore(GTurbolinks turbolinks) {
    this.turbolinks = turbolinks;
  }

  @Override
  protected void onRestart() {
    super.onRestart();

    if (turbolinks != null) {
      turbolinks.restore();
    }
  }

  public abstract void startTurbolinksScreen(PathSpec pathSpec, GImmutableParams params);
//  {
//    // Override for TL support
//  }

  /////
}
