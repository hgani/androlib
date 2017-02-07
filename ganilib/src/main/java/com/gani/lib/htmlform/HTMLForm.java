package com.gani.lib.htmlform;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gani.lib.http.GHttpCallback;
import com.gani.lib.http.GHttpError;
import com.gani.lib.http.GHttpResponse;
import com.gani.lib.http.GImmutableParams;
import com.gani.lib.http.GRestResponse;
import com.gani.lib.http.HttpAsyncGet;
import com.gani.lib.http.HttpHook;
import com.gani.lib.logging.GLog;
import com.gani.lib.ui.ProgressIndicator;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HTMLForm {
//  private final String TAG = HTMLForm.class.getName();
  private final String INPUT_TAG = "input";
  private final String TEXTAREA_TAG = "textarea";
  private final String SELECT_TAG = "select";

  private final String TEXT_TYPE = "text";
  private final String URL_TYPE = "url";
  private final String HIDDEN_TYPE = "hidden";
  private final String SUBMIT_TYPE = "submit";
  private final String RADIO_TYPE = "radio";
  private final String CHECKBOX_TYPE = "checkbox";

  private Context mContext;
  private LinearLayout mLayout;
  private String mURL;
//  private ArrayList<Integer> mViewIds = new ArrayList<>();
//  private Element mForm;
//  private Elements mFields;

  public HTMLForm(Context context, LinearLayout layout, String url) {
    this.mContext = context;
    this.mLayout = layout;
    this.mURL = url;
  }

  public void buildFields() {
    GHttpCallback callback = new GHttpCallback<GHttpResponse, GHttpError>() {
      @Override
      public void onHttpSuccess(GHttpResponse response) {
        if (!response.hasError()) {
          Document document = Jsoup.parse(response.asString());
          parse(document);
        }
      }

      @Override
      public void onHttpFailure(GHttpError error) {
        GLog.e(getClass(), "Failed retrieving form");
      }
    };

    new HttpAsyncGet(mURL, null, HttpHook.DUMMY, callback).execute();
  }

  private void parse(Document document) {
    Element mForm = document.select("form").first();
    Elements mFields = mForm.select("input, textarea, select");

    for (int index = 0; index < mFields.size(); index++) {
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
              HTMLRadioButton htmlRadioButton = new HTMLRadioButton(mContext, field);
              mLayout.addView(htmlRadioButton);
              break;
            case SUBMIT_TYPE:
              Button button = new Button(mContext);
              button.setText(field.val());
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


//    private class ParseForm extends AsyncTask<String, Void, Document> {
//        @Override
//        protected Document doInBackground(String... args) {
//            Document doc = null;
//            try {
//                doc = Jsoup.connect(args[0]).get();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return doc;
//        }
//
//        @Override
//        protected void onPostExecute(Document document) {
//          super.onPostExecute(document);
//
//        }
//    }

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
