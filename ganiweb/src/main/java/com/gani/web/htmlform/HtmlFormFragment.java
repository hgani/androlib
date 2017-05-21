package com.gani.web.htmlform;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gani.lib.http.GHttp;
import com.gani.lib.model.GBundle;
import com.gani.lib.screen.GFragment;
import com.gani.lib.ui.style.Length;
import com.gani.web.R;

import static com.gani.web.htmlform.HtmlFormScreenHelper.FORM_PATH;
import static com.gani.web.htmlform.HtmlFormScreenHelper.SUBMIT_LISTENER;

public class HtmlFormFragment extends GFragment {
  private static final int PADDING = Length.dpToPx(20);
  private HtmlForm form;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragmentLayout = inflater.inflate(R.layout.common_fragment, null);

    LinearLayout containerLayout = (LinearLayout) fragmentLayout.findViewById(R.id.container);
    containerLayout.setPadding(PADDING, PADDING, PADDING, PADDING);

    GBundle bundle = args();
    String path = bundle.getString(FORM_PATH);

    this.form = new HtmlForm(this, containerLayout, GHttp.instance().baseUrl() + path);
    form.setOnSubmitListener(((HtmlFormOnSubmitListener) bundle.getSerializable(SUBMIT_LISTENER)));
    form.buildFields();

    return fragmentLayout;
  }
}
