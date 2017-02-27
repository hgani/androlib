package com.gani.lib.http;

import android.os.AsyncTask;


public class AsyncHttpTask extends AsyncTask<Void, Void, GHttpResponse> {
  private GHttpCallback callback;
  private HttpDelegate delegate;
  
  AsyncHttpTask(GHttpCallback callback, HttpDelegate delegate) {
    this.callback = callback;
    this.delegate = delegate;
  }
  
  public String getUrl() {
    return delegate.getUrl();
  }

  @Override
  protected GHttpResponse doInBackground(Void... unused) {
    return delegate.launch();
  }

  @Override
  protected void onPostExecute(GHttpResponse result) {
    result.handle(callback);
  }

  public void notifyFailure(GHttpError error) {
    callback.onHttpFailure(error);
  }

  // NOTE: calling this to ensure that no future call to executeIfNotCanceled() will take effect
  synchronized void safeCancel() {  // Sync with executeIfNotCanceled()
  	cancel(true);
  }

  public synchronized void firstPhaseExecute() {
    // TODO: refactor so we don't need to use instanceof
    if (callback instanceof GRestCallback) {
      ((GRestCallback) callback).onBeforeHttp();
    }
  }

  public synchronized void secondPhaseExecute() {
    if (!isCancelled()) {
      execute();
    }
  }

  public synchronized void executeIfNotCanceled() {
//    // TODO: refactor so we don't need to use instanceof
//    if (callback instanceof GHttpCallback.Rest) {
//      ((GHttpCallback.Rest) callback).onBeforeHttp();
//    }
//
//  	if (!isCancelled()) {
//  		execute();
//  	}

    firstPhaseExecute();
    secondPhaseExecute();
  }
}