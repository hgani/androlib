package com.gani.web.htmlform;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.InputType;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RadioGroup;

import com.gani.lib.http.GHttp;
import com.gani.lib.http.GHttpCallback;
import com.gani.lib.http.GHttpError;
import com.gani.lib.http.GHttpResponse;
import com.gani.lib.http.HttpAsyncGet;
import com.gani.lib.http.HttpHook;
import com.gani.lib.logging.GLog;
import com.gani.lib.screen.GActivity;
import com.gani.lib.screen.GFragment;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HTMLForm {
  private final String TAG          = HTMLForm.class.getName();
  private final String INPUT_TAG    = "input";
  private final String TEXTAREA_TAG = "textarea";
  private final String SELECT_TAG   = "select";

  private final String TEXT_TYPE     = "text";
  private final String URL_TYPE      = "url";
  private final String HIDDEN_TYPE   = "hidden";
  private final String SUBMIT_TYPE   = "submit";
  private final String RADIO_TYPE    = "radio";
  private final String CHECKBOX_TYPE = "checkbox";
  private final String EMAIL_TYPE    = "email";
  private final String PASSWORD_TYPE = "password";

  public static final String NAME_ATTR = "name";

  public static final String RADIO_TYPE_QUERY = "input[type=radio]";
  public static final String CHECKBOX_TYPE_QUERY = "input[type=checkbox]";

  private GFragment fragment;
  private Context mContext;
  private LinearLayout mLayout;
  private String mURL;
  private ArrayList<Integer> mViewIds = new ArrayList<>();
  private Document mDocument;
  private Element mForm;
  private Elements mFields;
  private HTMLFormOnSubmitListener mListener;

  public HTMLForm(GFragment fragment, LinearLayout layout, String url) {
    this.fragment = fragment;
    this.mContext = fragment.getContext();
    this.mLayout = layout;
    this.mURL = url;
  }

  public Element getFormElement() {
    return mForm;
  }

  public GFragment getFragment() {
    return fragment;
  }

  public Document getDocument() {
    return mDocument;
  }

  public LinearLayout getLayout() {
    return mLayout;
  }

  public void buildFields() {
//        new ParseForm().execute(mURL);
    GLog.t(getClass(), "BUILD FIELDS");

    GHttpCallback callback = new GHttpCallback<GHttpResponse, GHttpError>() {
      @Override
      public void onHttpSuccess(GHttpResponse response) {
        GLog.t(getClass(), "FORM RESPONSE: " + response.hasError());
        if (!response.hasError()) {
          Document document = Jsoup.parse(response.asString());
//                    parse(document);
          new ParseForm().onPostExecute(document);
        } else {
          GHttp.instance().alertHelper().alertFormParsingError(mContext, response);
        }
      }

      @Override
      public void onHttpFailure(GHttpError error) {
        GHttp.instance().alertHelper().alertFormParsingError(mContext, error.getResponse());
//                GLog.e(getClass(), "Failed retrieving form");
      }
    };

    new HttpAsyncGet(mURL, null, HttpHook.DUMMY, callback).execute();
  }

  public void setOnSubmitListener(HTMLFormOnSubmitListener listener) {
    this.mListener = listener;
  }

//    private HTMLEditText createInputField(int index, Element field, LayoutParams params) {
//        HTMLEditText editText = new HTMLEditText(mContext, field, params);
//        editText.setId(mViewIds.get(index));
//
//        return editText;
//    }

  private HTMLForm getCurrentForm() {
    return this;
  }

  private class ParseForm extends AsyncTask<String, Void, Document> {
    @Override
    protected Document doInBackground(String... args) {
      Document doc = null;
      try {
        Uri uri = Uri.parse(args[0]);
        Connection conn = Jsoup.connect(args[0]);

        CookieManager cookieManager = CookieManager.getInstance();
        String cookieStr = cookieManager.getCookie(uri.getHost());
        Map<String, String> cookies = new HashMap<>();

        if (cookieStr != null) {
          for (String cookie : cookieStr.split(";")) {
            String[] temp = cookie.split("=");
            cookies.put(temp[0], temp[1]);
          }

          if (!cookies.isEmpty()) {
            conn.cookies(cookies);
          }
        }

        doc = conn.get();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return doc;
    }

//    @Override
//    protected void onPostExecute(Document document) {
//      super.onPostExecute(document);
//
//      mDocument = document;
//      mForm = document.select("form").first();
//      mFields = mForm.select("input, textarea, select");
//
//      for (int index = 0; index < mFields.size(); index++) {
//        HTMLEditText htmlEditText;
//        Element field = mFields.get(index);
//
//        switch (field.tagName()) {
//          case INPUT_TAG:
//            switch (field.attr("type")) {
//              case TEXT_TYPE:
//                addLabel(field);
//                htmlEditText = new HTMLEditText(mContext, field);
//                htmlEditText.setSingleLine(true);
//                mLayout.addView(htmlEditText);
//                break;
//              case EMAIL_TYPE:
//                addLabel(field);
//                htmlEditText = new HTMLEditText(mContext, field);
//                htmlEditText.setSingleLine(true);
//                htmlEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//                mLayout.addView(htmlEditText);
//                break;
//              case PASSWORD_TYPE:
//                addLabel(field);
//                htmlEditText = new HTMLEditText(mContext, field);
//                htmlEditText.setSingleLine(true);
//                htmlEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                mLayout.addView(htmlEditText);
//                break;
//              case URL_TYPE:
//                addLabel(field);
//                htmlEditText = new HTMLEditText(mContext, field);
//                htmlEditText.setSingleLine(true);
//                htmlEditText.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
//                mLayout.addView(htmlEditText);
//                break;
//              case HIDDEN_TYPE:
//                htmlEditText = new HTMLEditText(mContext, field);
//                htmlEditText.setVisibility(View.GONE);
//                mLayout.addView(htmlEditText);
//                break;
//              case CHECKBOX_TYPE:
//                HTMLCheckBox htmlCheckBox = new HTMLCheckBox(mContext, field);
//                mLayout.addView(htmlCheckBox);
//                break;
//              case RADIO_TYPE:
//                HTMLRadioButton htmlRadioButton = new HTMLRadioButton(mContext, field);
//                mLayout.addView(htmlRadioButton);
//                break;
//              case SUBMIT_TYPE:
//                Button button = new Button(mContext);
//                button.setText(field.val());
//                button.setOnClickListener(new View.OnClickListener() {
//                  @Override
//                  public void onClick(View view) {
//                    mListener.onSubmit(getCurrentForm());
//                  }
//                });
//                mLayout.addView(button);
//                break;
//            }
//            break;
//          case TEXTAREA_TAG:
//            addLabel(field);
//            htmlEditText = new HTMLEditText(mContext, field);
//            htmlEditText.setLines(3);
//            mLayout.addView(htmlEditText);
//            break;
//          case SELECT_TAG:
//            addLabel(field);
//            HTMLSpinner htmlSpinner = new HTMLSpinner(mContext, field);
//            mLayout.addView(htmlSpinner);
//            break;
//        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);

            mDocument = document;
            mForm = document.select("form").first();
            mFields = mForm.select("input, textarea, select");

            for(int index = 0; index < mFields.size(); index++) {
                HTMLEditText htmlEditText;
                Element field = mFields.get(index);

                switch (field.tagName()) {
                    case INPUT_TAG:
                        switch (field.attr("type")) {
                            case TEXT_TYPE:
                                addLabel(field);
                                htmlEditText = new HTMLEditText(mContext, field);
                                htmlEditText.setSingleLine(true);
                                mLayout.addView(htmlEditText);
                                break;
                            case EMAIL_TYPE:
                                addLabel(field);
                                htmlEditText = new HTMLEditText(mContext, field);
                                htmlEditText.setSingleLine(true);
                                htmlEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                                mLayout.addView(htmlEditText);
                                break;
                            case PASSWORD_TYPE:
                                addLabel(field);
                                htmlEditText = new HTMLEditText(mContext, field);
                                htmlEditText.setSingleLine(true);
                                htmlEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                mLayout.addView(htmlEditText);
                                break;
                            case URL_TYPE:
                                addLabel(field);
                                htmlEditText = new HTMLEditText(mContext, field);
                                htmlEditText.setSingleLine(true);
                                htmlEditText.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
                                mLayout.addView(htmlEditText);
                                break;
                            case HIDDEN_TYPE:
                                htmlEditText = new HTMLEditText(mContext, field);
                                htmlEditText.setVisibility(View.GONE);
                                mLayout.addView(htmlEditText);
                                break;
                            case CHECKBOX_TYPE:
                                HTMLCheckBox htmlCheckBox = new HTMLCheckBox(mContext, field);
                                mLayout.addView(htmlCheckBox);
                                break;
                            case RADIO_TYPE:
//                                HTMLRadioButton htmlRadioButton = new HTMLRadioButton(mContext, field);
//                                mLayout.addView(htmlRadioButton);
                                RadioGroup radioGroup = new RadioGroup(mContext);
                                radioGroup.setTag(field.attr(NAME_ATTR));
                                mLayout.addView(radioGroup);

                                Elements radioButtons = mForm.getElementsByAttributeValue(NAME_ATTR, field.attr(NAME_ATTR));
                                radioButtons = radioButtons.select(RADIO_TYPE_QUERY);

                                for(int i = 0; i < radioButtons.size(); i++) {
                                    Element radio = mFields.get(index + i);
                                    HTMLRadioButton htmlRadioButton = new HTMLRadioButton(mContext, radio);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                        htmlRadioButton.setId(View.generateViewId());
                                    }
                                    radioGroup.addView(htmlRadioButton);
                                }

                                index = index + (radioButtons.size() - 1);
                                break;
                            case SUBMIT_TYPE:
                                Button button = new Button(mContext);
                                button.setText(field.val());
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mListener.onSubmit(getCurrentForm());
                                    }
                                });
                                mLayout.addView(button);
                                break;
                        }
                        break;
                    case TEXTAREA_TAG:
                        addLabel(field);
                        htmlEditText = new HTMLEditText(mContext, field);
                        htmlEditText.setLines(3);
                        mLayout.addView(htmlEditText);
                        break;
                    case SELECT_TAG:
                        addLabel(field);
                        HTMLSpinner htmlSpinner = new HTMLSpinner(mContext, field);
                        mLayout.addView(htmlSpinner);
                        break;
                }

      }
    }
  }

  private void addLabel(Element field) {
    if (field.parent().getElementsByTag("label").size() > 0) {
      TextView textView = new TextView(mContext);
      textView.setTextColor(Color.BLACK);
      textView.setTypeface(null, Typeface.BOLD);
      textView.setText(field.parent().getElementsByTag("label").text());
      mLayout.addView(textView);
    }
  }
}
