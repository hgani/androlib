package com.gani.web.htmlform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.gani.lib.http.GHttp;
import com.gani.lib.model.GBundle;
import com.gani.lib.screen.GActivity;
import com.gani.lib.ui.Ui;
import com.gani.web.PathSpec;
import com.gani.web.R;

public class HTMLFormScreenHelper {
  public static final String FORM_PATH = "FORM_PATH";
  public static final String SUBMIT_LISTENER = "SUBMIT_LISTENER";
//
//  public static Intent intent(Class cls, String url, String listenerClassName) {
//    Intent intent = new Intent(Ui.context(), cls);
//    intent.putExtra(FORM_PATH, url);
////    intent.putExtra(SUBMIT_LISTENER, App.LISTENERS_PACKAGE + ".SignUpOnSubmitListener");
//    intent.putExtra(SUBMIT_LISTENER, listenerClassName);
//    return intent;
//  }
//
//  public static Intent intent(Class<? extends GActivity> cls, String url, HTMLFormOnSubmitListener listener) {
//    Intent intent = new Intent(Ui.context(), cls);
//    intent.putExtra(FORM_PATH, url);
//    intent.putExtra(SUBMIT_LISTENER, listener);
//    return intent;
//  }

//  public static Intent intent(Class cls, String url) {
//    Intent intent = new Intent(Ui.context(), cls);
//    intent.putExtra(FORM_PATH, url);
//    return intent;
//  }

  public static Intent intent(Class<? extends GActivity> cls, PathSpec spec) {
    Intent intent = new Intent(Ui.context(), cls);
    intent.putExtra(FORM_PATH, spec.getPath());
    intent.putExtra(SUBMIT_LISTENER, spec.getFormListener());
    return intent;
  }


  private GActivity activity;
  private HTMLForm form;

  public HTMLFormScreenHelper(GActivity activity, Bundle savedInstanceState) {
    this.activity = activity;

    onCreate(savedInstanceState);
  }

  private void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sign_up_screen);
    activity.setContentWithToolbar(R.layout.screen_html_form, true);

//
//    Intent intent = getIntent();
//    String path = intent.getStringExtra(FORM_PATH);
//    String className = intent.getStringExtra(SUBMIT_LISTENER);

    GBundle bundle = activity.args();
    String path = bundle.getString(FORM_PATH);
//    String className = bundle.getString(SUBMIT_LISTENER);

    LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.layout);
//    HTMLForm htmlForm = new HTMLForm(this, linearLayout, App.ENDPOINT_URL + path);
    this.form = new HTMLForm(activity, linearLayout, GHttp.instance().baseUrl() + path);

//    form.setOnSubmitListener(new HTMLFormOnSubmitListener() {
//      @Override
//      public void onSubmit(HTMLForm form) {
//        HTMLFormScreenHelper.this.onSubmit();
//      }
//    });

    form.setOnSubmitListener(((HTMLFormOnSubmitListener) bundle.getSerializable(SUBMIT_LISTENER)));

    form.buildFields();
//
//    try {
//      form.setOnSubmitListener((HTMLFormOnSubmitListener) bundle.getClass(SUBMIT_LISTENER).newInstance());
//    } catch (InstantiationException e) {
//      GLog.e(getClass(), "Listener not found", e);
//    } catch (IllegalAccessException e) {
//      GLog.e(getClass(), "Listener not found", e);
////    } catch (ClassNotFoundException e) {
////      GLog.e(getClass(), "Listener not found", e);
//    }

//
//    try {
//      htmlForm.setOnSubmitListener((HTMLFormOnSubmitListener) Class.forName(className).newInstance());
//    } catch (InstantiationException e) {
//      GLog.e(getClass(), "Listener not found", e);
//    } catch (IllegalAccessException e) {
//      GLog.e(getClass(), "Listener not found", e);
//    } catch (ClassNotFoundException e) {
//      GLog.e(getClass(), "Listener not found", e);
//    }
  }

  public HTMLForm getForm() {
    return form;
  }

//  protected abstract void onSubmit();
//
//  public JSONObject extractParams(){
//    LinearLayout layout = form.getLayout();
//    JSONObject params = new JSONObject();
//
//    try {
//      for(int i = 0; i < form.getLayout().getChildCount(); i++) {
//        String tag = (String) layout.getChildAt(i).getTag();
//        String value = "";
//
//        if (layout.getChildAt(i) instanceof HTMLEditText) {
//          value = ((HTMLEditText) layout.getChildAt(i)).getText().toString();
//        }
//
//        if (tag instanceof String) {
//          if (tag.contains("[")) {
//            String[] keys = tag.replace("]", "").split("\\[");
//
//            if (params.isNull(keys[0])) {
//              params.put(keys[0], new JSONObject());
//            }
//
//            params.getJSONObject(keys[0]).put(keys[1], value);
//          }
//          else {
//            params.put(tag, value);
//          }
//        }
//      }
//    } catch (JSONException e) {
//      e.printStackTrace();
//    }
//
//    return params;
//  }
//
//  public void sendJSONRequest(final HTMLForm form, int method, final String endpoint, JSONObject params, Response.Listener listener) {
//    JsonObjectRequest objectRequest = new JsonObjectRequest(
//        method, endpoint, params, listener, new Response.ErrorListener() {
//      @Override
//      public void onErrorResponse(VolleyError error) {
//        GLog.e(getClass(), error.toString());
////        Log.d(TAG, error.toString());
//      }
//    }) {
//      @Override
//      public Map<String, String> getHeaders() throws AuthFailureError {
//        HashMap<String, String> map = new HashMap<>();
//        try {
//          Uri uri = Uri.parse(endpoint);
//          CookieManager cookieManager = CookieManager.getInstance();
//          String cookieStr = cookieManager.getCookie(uri.getHost());
//
//          map.put("Cookie", cookieStr);
//          map.put("X-CSRF-Token", form.getDocument().select("meta[name='csrf-token']").attr("content"));
//          map.put("Accept", "application/json");
//        } catch (UnsupportedOperationException e) {
//          e.printStackTrace();
//        }
//
//        return map;
//      }
//    };
//
//    RequestQueue requestQueue = Volley.newRequestQueue(form.getContext().getApplicationContext());
//    requestQueue.add(objectRequest);
//  }
}
