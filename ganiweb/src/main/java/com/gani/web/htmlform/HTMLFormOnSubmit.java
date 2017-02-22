package com.gani.web.htmlform;

import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gani.lib.logging.GLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class HTMLFormOnSubmit implements HTMLFormOnSubmitListener {
    private final String TAG = this.getClass().getSimpleName();

    private JSONObject mParams;

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

    private void setParams(String tag, String value) {
        try {
            if (tag instanceof String && value != null) {
                if (tag.contains("[")) {
                    String[] keys = tag.replace("]", "").split("\\[");

                    if (mParams.isNull(keys[0])) {
                        mParams.put(keys[0], new JSONObject());
                    }

                    GLog.t(getClass(), "PARAM1: " + keys[1] + " => " + value);

                    mParams.getJSONObject(keys[0]).put(keys[1], value);
                } else {
                    mParams.put(tag, value);

                    GLog.t(getClass(), "PARAM2: " + tag + " => " + value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setParams(String tag, ArrayList<String> value) {
        try {
            if (tag instanceof String && value != null) {
                if (tag.contains("[")) {
                    String[] keys = tag.replace("]", "").split("\\[");

                    if (mParams.isNull(keys[0])) {
                        mParams.put(keys[0], new JSONObject());
                    }

                    GLog.t(getClass(), "PARAM1: " + keys[1] + " => " + value);

                    mParams.getJSONObject(keys[0]).put(keys[1], new JSONArray(value));
                } else {
                    mParams.put(tag, new JSONArray(value));

                    GLog.t(getClass(), "PARAM2: " + tag + " => " + value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected JSONObject getParams(HTMLForm form) {
        LinearLayout layout = form.getLayout();
        mParams = new JSONObject();

        for (int i = 0; i < form.getLayout().getChildCount(); i++) {
            String tag = (String) layout.getChildAt(i).getTag();

            if (layout.getChildAt(i) instanceof HTMLEditText) {
                String value = ((HTMLEditText) layout.getChildAt(i)).getText().toString();
                setParams(tag, value);
            } else if (layout.getChildAt(i) instanceof HTMLSpinner) {
                String value = ((HTMLSpinner) layout.getChildAt(i)).getSelectedItem().toString();
                setParams(tag, value);
            } else if (layout.getChildAt(i) instanceof RadioGroup) {
                String value;
                int radioButtonId = ((RadioGroup) layout.getChildAt(i)).getCheckedRadioButtonId();

                if (radioButtonId > -1) {
                    HTMLRadioButton radioButton = (HTMLRadioButton) layout.findViewById(radioButtonId);
                    value = radioButton.getValue();
                } else {
                    value = null;
                }

                setParams(tag, value);
            } else if (layout.getChildAt(i) instanceof HTMLCheckBox) {
                Elements checkBoxes = form.getFormElement().getElementsByAttributeValue(HTMLForm.NAME_ATTR, tag);
                checkBoxes = checkBoxes.select(HTMLForm.CHECKBOX_TYPE_QUERY);

                if (checkBoxes.size() > 1) {
                    ArrayList<String> values = new ArrayList<>();

                    for(int j = 0; j < checkBoxes.size(); j++) {
                        HTMLCheckBox htmlCheckBox = (HTMLCheckBox) layout.getChildAt(i + j);

                        if (htmlCheckBox.isChecked()) {
                            String value = htmlCheckBox.getValue();
                            values.add(value);
                        }
                    }

                    i = i + (checkBoxes.size() - 1);
                    setParams(tag, values);
                }
                else {
                    HTMLCheckBox htmlCheckBox = (HTMLCheckBox) layout.getChildAt(i);

                    if (htmlCheckBox.isChecked()) {
                        String value = htmlCheckBox.getValue();
                        setParams(tag, value);
                    }
                }
            }
        }

        return mParams;
    }

//    protected Map extractParams(HTMLForm form){
//        LinearLayout layout = form.getLayout();
////        JSONObject params = new JSONObject();
//        Map params = new HashMap();
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
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return params;
//    }
}
