package com.gani.lib.dialog;

import android.content.Intent;
import android.os.Bundle;

import com.gani.lib.http.GImmutableParams;
import com.gani.lib.http.GRestCallback;
import com.gani.lib.http.GRestResponse;
import com.gani.lib.http.HttpAsync;
import com.gani.lib.http.HttpMethod;

import org.json.JSONException;

import java.io.Serializable;

public class GRestDialogProgress extends GDialogProgress {
  private static final String ARG_METHOD = "method";
  private static final String ARG_URL = "url";
  private static final String ARG_PARAMS = "params";
  private static final String ARG_CALLBACK = "callback";

//  private static final String ARG_HTTP_REQUEST = "httpRequest";
//  private HttpAsyncGet httpRequest;
  private HttpAsync httpRequest;

  public static Intent intent(HttpMethod method, String url, GImmutableParams params, Callback callback) {
//  public static Intent intent(HttpAsync httpRequest) {
    return intentBuilder(GRestDialogProgress.class)
//        .withArg(ARG_HTTP_REQUEST, httpRequest)
        .withArg(ARG_METHOD, method)
        .withArg(ARG_URL, url)
        .withArg(ARG_PARAMS, params)
        .withArg(ARG_CALLBACK, callback)
        .getIntent();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    httpRequest = (HttpAsync) args().getSerializable(ARG_HTTP_REQUEST);
//    httpRequest.execute();

    HttpMethod method = (HttpMethod) args().getSerializable(ARG_METHOD);
    String url = args().getString(ARG_URL);
    GImmutableParams params = args().getParams(ARG_PARAMS);
    final Callback callback = (Callback) args().getSerializable(ARG_CALLBACK);
//
    httpRequest = method.async(url, params, new GRestCallback(this) {
      @Override
      protected void onJsonSuccess(GRestResponse r) throws JSONException {
        callback.onJsonSuccess(GRestDialogProgress.this, r);
      }

      @Override
      protected void onRestSuccess(GRestResponse r) throws JSONException {

      }
    }).execute();

//    final SearchCriteria criteria = (SearchCriteria) args().getSerializable(ARG_CRITERIA);
//    GParams params = GParams.create().put("address", criteria.getAddress()).put("radius", criteria.getRadius());
//    httpRequest = new HttpAsyncGet(args().getString(ARG_URL), params.toImmutable(), HttpHook.DUMMY, new GRestCallback(this) {
//      //        HttpMethod.from(args().getString(ARG_METHOD)), new GRestCallback(this) {
//      @Override
//      protected void onJsonSuccess(GRestResponse r) throws JSONException {
//        List<Gym> gyms = new LinkedList();
//        GJsonObject.Default result = (GJsonObject.Default) r.getResult();
//        for (GJsonObject event : result.getArray("events")) {
//          Gym gym = new Gym(event.getString("name"),
//              new Coordinate(event.getDouble("latitude"), event.getDouble("longitude")),
//              event.getString("address"),
//              event.getNullableString("phone"),
//              event.getNullableDouble("entry_fee"));
//          gyms.add(gym);
////
////          Marker marker = mGoogleMap.addMarker(new MarkerOptions()
////              .position(gym.getCoordinate())
////              .title(gym.getName()));
////          marker.setTag(gym);
//
//          GLog.t(getClass(), "Found event: " + gym);
//        }
//
//        startActivity(ResultActivity.intent(gyms, criteria));
//      }
//
//      @Override
//      protected void onRestSuccess(GRestResponse r) throws JSONException {
////        final TaJsonObject resultJson = r.getResult();
////
////        TaLog.t(getClass(), "RESULT: " + resultJson);
//
//      }
//    }).execute();
  }

  @Override
  protected void onCancel() {
    httpRequest.cancel();
  }



  public interface Callback extends Serializable {
    void onJsonSuccess(GRestDialogProgress dialogProgress, GRestResponse r) throws JSONException;
  }
}
