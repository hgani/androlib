package com.gani.lib.http;

import android.content.Context;
import android.os.AsyncTask;

import com.gani.lib.R;
import com.gani.lib.dialog.GDialogProgress;
import com.gani.lib.screen.GFragment;
import com.gani.lib.ui.ProgressIndicator;

import org.json.JSONException;


public interface GHttpCallback<HR extends GHttpResponse, HE extends GHttpError> {
  public void onHttpSuccess(HR response);
  public void onHttpFailure(HE error);



  public static abstract class Rest<HR extends GHttpResponse, RR extends GRestResponse, HE extends GHttpError> implements GHttpCallback<HR, HE> {
    private Context context;
    private ProgressIndicator indicator;

    public Rest(Context context, ProgressIndicator indicator) {
      this.context = context;
      this.indicator = indicator;
    }

    // To be used by child.
    protected final Context getContext() {
      return context;
    }

    public Rest(GFragment fragment) {
      this(fragment.getContext(), fragment);
    }

    public Rest(GDialogProgress dialog) {
      // No progress indicator needed since this dialog itself is an indicator
//      this(dialog, ProgressIndicator.NULL);
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
              }
              catch (JSONException e) {
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
        }
        else {
          onError();
          doFinally();
//          HttpUtils.alertCommonError(container, response);
//          HttpUtils.alertCommonError(context, response);
          GHttp.instance().alertHelper().alertCommonError(context, response);
        }
      } catch (JSONException e) {
        onError();
        doFinally();
//        HttpUtils.alertJsonError(container, r, e);
//        HttpUtils.alertJsonError(context, r, e);
        GHttp.instance().alertHelper().alertJsonError(context, r, e);
      }
    }

    @Override
    public void onHttpFailure(HE error) {
      onError();
      doFinally();
//      error.handleDefault(container.getRichActivity());
      error.handleDefault(context);
    }
    
    protected final void onBeforeHttp() {
      indicator.showProgress();
    }

    // NOTE: Execute on background thread. Use UI-safe classes such as ToastUtils.
//    protected abstract void onRestSuccess(GRestResponse r) throws JSONException;
    protected abstract void onRestSuccess(RR r) throws JSONException;

    protected void doFinally() {
      indicator.hideProgress();
//      RichActivity activity = container.getRichActivity();
//      if (activity != null) {  // May be null if the screen has been closed.
//        activity.hideProgress();
//      }
    }

    protected void onError() {
      // To be overridden.
    }
  }



  // TODO: Need retry? Especially for cognito.
  abstract class SyncSilentRest<RR extends GRestResponse> implements GHttpCallback {
    public final void onHttpSuccess(GHttpResponse response) {
      final RR r = (RR) response.asRestResponse();
      try {
        if (r.isAllOk()) {
          onRestSuccess(r);
        }
        else {
          onError();
          GHttp.instance().alertHelper().reportCodeError(response);
        }
      } catch (JSONException e) {
        onError();
//        HttpUtils.reportJsonError(r, e);
        GHttp.instance().alertHelper().reportJsonError(r, e);
      }
    }

    public final void onHttpFailure(GHttpError error) {
      onError();
    }

    // NOTE: Execute on background thread. Use UI-safe classes such as ToastUtils.
    protected abstract void onRestSuccess(RR r) throws JSONException;

    protected void onError() {
      // To be overridden.
    }
  }
}
