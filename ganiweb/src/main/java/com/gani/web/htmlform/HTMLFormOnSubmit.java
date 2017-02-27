package com.gani.web.htmlform;

import android.content.Intent;
import android.net.Uri;
import android.widget.LinearLayout;

import com.gani.lib.http.GHttp;
import com.gani.lib.http.GHttpCallback;
import com.gani.lib.http.GParams;
import com.gani.lib.http.GRestCallback;
import com.gani.lib.http.GRestResponse;
import com.gani.lib.http.HttpAsyncPost;
import com.gani.lib.http.HttpHook;
import com.gani.lib.http.HttpMethod;
import com.gani.web.PathSpec;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public abstract class HTMLFormOnSubmit implements HTMLFormOnSubmitListener {
    //    private final String TAG = this.getClass().getSimpleName();

//    protected void sendJSONRequest(final HTMLForm form, int method, final String endpoint, JSONObject params, Response.Listener listener) {
//        JsonObjectRequest objectRequest = new JsonObjectRequest(
//                method, endpoint, params, listener, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, error.toString());
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> map = new HashMap<>();
//                try {
//                    Uri uri = Uri.parse(endpoint);
//                    CookieManager cookieManager = CookieManager.getInstance();
//                    String cookieStr = cookieManager.getCookie(uri.getHost());
//
//                    map.put("Cookie", cookieStr);
//                    map.put("X-CSRF-Token", form.getDocument().select("meta[name='csrf-token']").attr("content"));
//                    map.put("Accept", "application/json");
//                } catch (UnsupportedOperationException e) {
//                    e.printStackTrace();
//                }
//
//                return map;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(form.getContext().getApplicationContext());
//        requestQueue.add(objectRequest);
//    }

//    protected JSONObject getParams(HTMLForm form){
//        LinearLayout layout = form.getLayout();
//        JSONObject params = new JSONObject();
//
//        try {
//            for(int i = 0; i < form.getLayout().getChildCount(); i++) {
//                String tag = (String) layout.getChildAt(i).getTag();
//                String value = "";
//
//                if (layout.getChildAt(i) instanceof HTMLEditText) {
//                    value = ((HTMLEditText) layout.getChildAt(i)).getText().toString();
//                }
//
//                if (tag instanceof String) {
//                    GLog.t(getClass(), "PARAM0: " + tag);
//
//                    if (tag.contains("[")) {
//                        String[] keys = tag.replace("]", "").split("\\[");
//
//                        if (params.isNull(keys[0])) {
//                            params.put(keys[0], new JSONObject());
//                        }
//
//                        GLog.t(getClass(), "PARAM1: " + keys[0] + " => " + value);
//
//                        params.getJSONObject(keys[0]).put(keys[1], value);
//                    }
//                    else {
//                        params.put(tag, value);
//
//                        GLog.t(getClass(), "PARAM2: " + tag + " => " + value);
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return params;
//    }

    protected Map buildParamMap(HTMLForm form) {
        LinearLayout layout = form.getLayout();
//        JSONObject params = new JSONObject();
        Map<String, Object> params = new HashMap<String, Object>();

//        try {
            for(int i = 0; i < form.getLayout().getChildCount(); i++) {
                String tag = (String) layout.getChildAt(i).getTag();
                String value = "";

                if (layout.getChildAt(i) instanceof HTMLEditText) {
                    value = ((HTMLEditText) layout.getChildAt(i)).getText().toString();
                }

                if (tag instanceof String) {
                    params.put(tag, value);

//                    if (tag.contains("[")) {
//                        String[] keys = tag.replace("]", "").split("\\[");
//
//                        if (params.isNull(keys[0])) {
//                            params.put(keys[0], new JSONObject());
//                        }
//
//                        params.getJSONObject(keys[0]).put(keys[1], value);
//                    }
//                    else {
//                        params.put(tag, value);
//                    }
                }
            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return params;
    }

    protected abstract Intent createTurbolinksIntent(PathSpec pathSpec);

    @Override
    public void onSubmit(final HTMLForm form) {
    String endpoint = GHttp.instance().baseUrl() + form.getFormElement().attr("action") + ".json";

        GParams params = GParams.fromParamMap(buildParamMap(form));
//    MaParams params = extractParams(form);

        GHttpCallback restCallback = new GRestCallback.Default(form.getFragment()) {
            @Override
            protected void onRestSuccess(GRestResponse r) throws JSONException {
                String url = r.getJsonResult().getString("visit");

                Uri uri = Uri.parse(url);
                PathSpec path = new PathSpec(uri.getPath());
                form.getContext().startActivity(createTurbolinksIntent(path));
//                form.getContext().startActivity(ScreenTurbolinks.intent(path));
//                ((Activity) form.getContext()).finish();
                form.getFragment().getGActivity().finish();

//        new AlertDialog.Builder(form.getContext())
//            .setMessage("SUCCESS")
//            .show();


            }

            @Override
            protected final void onJsonSuccess(GRestResponse r) throws JSONException {
//    String message = r.getJResult().getNullableString(ParamKeys.MESSAGE);
//    if (message != null) {
//      ToastUtils.showNormal(message);
//    }
            }
        };

        // The HTTP method is specified as a field.
        new HttpAsyncPost(endpoint, params.toImmutable(), HttpHook.DUMMY, HttpMethod.from(params), restCallback).execute();
    }
}
