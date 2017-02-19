package com.gani.web.htmlform;

import android.net.Uri;
import android.util.Log;
import android.webkit.CookieManager;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class HTMLFormOnSubmit implements HTMLFormOnSubmitListener {
    private final String TAG = this.getClass().getSimpleName();

    protected void sendJSONRequest(final HTMLForm form, int method, final String endpoint, JSONObject params, Response.Listener listener) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                method, endpoint, params, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                try {
                    Uri uri = Uri.parse(endpoint);
                    CookieManager cookieManager = CookieManager.getInstance();
                    String cookieStr = cookieManager.getCookie(uri.getHost());

                    map.put("Cookie", cookieStr);
                    map.put("X-CSRF-Token", form.getDocument().select("meta[name='csrf-token']").attr("content"));
                    map.put("Accept", "application/json");
                } catch (UnsupportedOperationException e) {
                    e.printStackTrace();
                }

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(form.getContext().getApplicationContext());
        requestQueue.add(objectRequest);
    }

    protected JSONObject getParams(HTMLForm form){
        LinearLayout layout = form.getLayout();
        JSONObject params = new JSONObject();

        try {
            for(int i = 0; i < form.getLayout().getChildCount(); i++) {
                String tag = (String) layout.getChildAt(i).getTag();
                String value = "";

                if (layout.getChildAt(i) instanceof HTMLEditText) {
                    value = ((HTMLEditText) layout.getChildAt(i)).getText().toString();
                }

                if (tag instanceof String) {
                    if (tag.contains("[")) {
                        String[] keys = tag.replace("]", "").split("\\[");

                        if (params.isNull(keys[0])) {
                            params.put(keys[0], new JSONObject());
                        }

                        params.getJSONObject(keys[0]).put(keys[1], value);
                    }
                    else {
                        params.put(tag, value);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }
}
