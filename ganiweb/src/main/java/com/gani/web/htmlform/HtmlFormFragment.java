package com.gani.web.htmlform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.http.GHttp;
import com.gani.lib.model.GBundle;
import com.gani.lib.screen.GActivity;
import com.gani.lib.screen.GFragment;
import com.gani.lib.ui.Ui;
import com.gani.lib.ui.style.Length;
import com.gani.web.PathSpec;
import com.gani.web.R;

import static com.gani.web.htmlform.HTMLFormScreenHelper.FORM_PATH;
import static com.gani.web.htmlform.HTMLFormScreenHelper.SUBMIT_LISTENER;

public class HtmlFormFragment extends GFragment {
//  private ComponentDetailController controller;

  private static final int PADDING = Length.dpToPx(20);
  private HTMLForm form;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragmentLayout = inflater.inflate(R.layout.common_fragment, null);

    LinearLayout containerLayout = (LinearLayout) fragmentLayout.findViewById(R.id.container);
    containerLayout.setPadding(PADDING, PADDING, PADDING, PADDING);
//    controller = new ComponentDetailController(this, containerLayout);

    GBundle bundle = args();
    String path = bundle.getString(FORM_PATH);
//    String className = bundle.getString(SUBMIT_LISTENER);

//    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
//    HTMLForm htmlForm = new HTMLForm(this, linearLayout, App.ENDPOINT_URL + path);


    this.form = new HTMLForm(this, containerLayout, GHttp.instance().baseUrl() + path);

    form.setOnSubmitListener(((HTMLFormOnSubmitListener) bundle.getSerializable(SUBMIT_LISTENER)));

    form.buildFields();

    return fragmentLayout;
  }
}
