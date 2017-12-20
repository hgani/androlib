package com.gani.lib.http;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.gani.lib.dialog.GDialogProgress;
import com.gani.lib.logging.GLog;
import com.gani.lib.screen.GFragment;
import com.gani.lib.ui.ProgressIndicator;

import org.json.JSONException;


public abstract class GRestCallback<HR extends GHttpResponse, RR extends GRestResponse, HE extends GHttpError> implements GHttpCallback<HR, HE> {


  private Context context;
  private ProgressIndicator indicator;

  public GRestCallback(Context context, ProgressIndicator indicator) {
    this.context = context;
    this.indicator = indicator;
  }

  // To be used by child.
  protected final Context getContext() {
    return context;
  }

  public GRestCallback(GFragment fragment) {
    this(fragment.getContext(), fragment);
  }

  public GRestCallback(GDialogProgress dialog) {
    this(dialog, dialog);
  }

  protected abstract void onJsonSuccess(RR r) throws JSONException;

  @Override
  public final void onHttpSuccess(HR response) {
    final RR r = (RR) response.asRestResponse();
    try {
      if (r.isAllOk()) {
        new AsyncTask<Void, Void, Exception>() {
          @Override
          protected Exception doInBackground(Void... params) {
            try {
              onRestSuccess(r);
            } catch (JSONException e) {
              return e;
            }

            return null;
          }

          @Override
          protected void onPostExecute(Exception e) {
            if (e instanceof JSONException) {
              GHttp.instance().alertHelper().alertJsonError(context, r, (JSONException) e);
            }

            doFinally();
          }
        }.execute();

        onJsonSuccess(r);
      } else {
        GLog.t(getClass(), "ON ERROR");
        onError();
        doFinally();
        GHttp.instance().alertHelper().alertCommonError(context, response);
      }
    } catch (JSONException e) {
      onError();
      doFinally();
      GHttp.instance().alertHelper().alertJsonError(context, r, e);
    }
  }

  @Override
  public void onHttpFailure(HE error) {
    onError();
    doFinally();
    error.handle(context);
  }

  protected final void onBeforeHttp() {
    indicator.showProgress();
  }

  // NOTE: Execute on background thread. Use UI-safe classes such as ToastUtils.
//    protected abstract void onRestSuccess(GRestResponse r) throws JSONException;
  protected abstract void onRestSuccess(RR r) throws JSONException;

//    protected abstract void onJsonParsingError(RR r) throws JSONException;

  protected void doFinally() {
    indicator.hideProgress();
//      RichActivity activity = container.getGActivity();
//      if (activity != null) {  // May be null if the screen has been closed.
//        activity.hideProgress();
//      }
  }

  protected void onError() {
    // To be overridden.
  }


  public static abstract class Default extends GRestCallback<GHttpResponse, GRestResponse, GHttpError> {
    public Default(Context context, ProgressIndicator indicator) {
      super(context, indicator);
    }

    public Default(GFragment fragment) {
      super(fragment);
    }

    //    @Override
//    public void handleDefault(Context context) {
//      Toast.makeText(context, getMessage(), Toast.LENGTH_LONG).show();
//    }
  }
}
