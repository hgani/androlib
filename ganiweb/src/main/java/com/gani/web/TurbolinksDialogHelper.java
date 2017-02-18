package com.gani.web;

import android.os.Bundle;

import com.gani.lib.R;
import com.gani.lib.screen.GActivity;

public abstract class TurbolinksDialogHelper extends TurbolinksActivityHelper {
  public TurbolinksDialogHelper(GTurbolinksSupportActivity activity, Bundle savedInstanceState) {
    super(activity, savedInstanceState);
  }

  @Override
  protected final void initViewContent() {
    getActivity().setContentForDialog(R.layout.screen_inline_turbolinks);
  }
}
