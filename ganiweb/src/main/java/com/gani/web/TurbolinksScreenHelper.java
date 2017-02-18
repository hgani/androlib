package com.gani.web;

import android.os.Bundle;

import com.gani.lib.R;
import com.gani.lib.screen.GActivity;

public abstract class TurbolinksScreenHelper extends TurbolinksActivityHelper {
  public TurbolinksScreenHelper(GTurbolinksSupportActivity activity, Bundle savedInstanceState) {
    super(activity, savedInstanceState);
  }

  @Override
  protected final void initViewContent() {
    getActivity().setContentWithToolbar(R.layout.screen_turbolinks, false);
  }
}
