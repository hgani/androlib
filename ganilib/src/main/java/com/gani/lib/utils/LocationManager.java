package com.gani.lib.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.gani.lib.logging.GLog;
import com.gani.lib.screen.GActivity;
import com.gani.lib.ui.Ui;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.R.attr.permission;
import static java.security.AccessController.getContext;

public class LocationManager {
  private static final String PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

  private static final LocationFetchingListener FETCH_LISTENER = new LocationFetchingListener() {
    @Override
    public void onSuccess(Location location) {
      GLog.t(getClass(), "Updated location: " + location);
      LocationManager.instance().location = location;
    }

    @Override
    public void onFailure() {
      // Nothing to do
    }
  };

  private Location location;
//  private LocationFetchingListener fetchListener;

  private static final LocationManager INSTANCE = new LocationManager();

  public static LocationManager instance() {
    return INSTANCE;
  }

//  public void updateLocationSilently(GActivity activity) {
//    activity.fetchLastLocation(Manifest.permission.ACCESS_FINE_LOCATION, new GActivity.LocationFetchingListener() {
//      @Override
//      public void onSuccess(Location location) {
////        GLog.t(getClass(), "Updated location: " + location.getLatitude() + ". " + location.getLongitude());
//        LocationManager.this.location = location;
//      }
//
//      @Override
//      public void onFailure() {
//        // Nothing to do
//      }
//    });
//
//
//  }

  public void updateLocation(GActivity activity) {
    if (!updateLocationSilently(activity)) {
      ActivityCompat.requestPermissions(activity, new String[] { PERMISSION }, GActivity.PERMISSION_LOCATION);
    }
  }

  public boolean updateLocationSilently(GActivity activity) {
    GLog.t(getClass(), "UPDATE LOC1");
    if (LocationManager.instance().isAccessPermitted()) {
      GLog.t(getClass(), "UPDATE LOC2");
      fetchFusedLocation(activity);
      return true;
    }
    return false;
  }
//
//  public static boolean updateLocationSilently(GActivity activity) {
//    return activity.fetchLastLocationSilently();
//  }

  public boolean isAccessPermitted() {
    return ContextCompat.checkSelfPermission(Ui.context(), PERMISSION) == PackageManager.PERMISSION_GRANTED;
  }

  public void fetchFusedLocation(GActivity activity) {
    GLog.t(getClass(), "fetchFusedLocation3");

    FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(activity);

    client.getLastLocation()
        .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
          @Override
          public void onSuccess(Location location) {
            GLog.t(getClass(), "fetchFusedLocation5");
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
              GLog.t(getClass(), "fetchFusedLocation6");
              FETCH_LISTENER.onSuccess(location);
            }
            else {
              GLog.t(getClass(), "fetchFusedLocation7");
              FETCH_LISTENER.onFailure();
            }
          }
        })
        .addOnFailureListener(activity, new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            GLog.t(getClass(), "fetchFusedLocation8");
            FETCH_LISTENER.onFailure();
          }
        });



//    GLog.t(getClass(), "fetchFusedLocation3");
//    final LocationFetchingListener listener = fetchListener;
//    if (listener != null) {  // Extra protection in case Android calls onRequestPermissionsResult() multiple times.
//
//      GLog.t(getClass(), "fetchFusedLocation4");
//      this.locationFetchListener = null;
//
//      FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(getContext());
//
//      client.getLastLocation()
//          .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//              GLog.t(getClass(), "fetchFusedLocation5");
//              // Got last known location. In some rare situations this can be null.
//              if (location != null) {
//                GLog.t(getClass(), "fetchFusedLocation6");
//                listener.onSuccess(location);
//              }
//              else {
//                GLog.t(getClass(), "fetchFusedLocation7");
//                listener.onFailure();
//              }
//            }
//          })
//          .addOnFailureListener(this, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//              GLog.t(getClass(), "fetchFusedLocation8");
//              listener.onFailure();
//            }
//          });
//    }
  }


  public interface LocationFetchingListener {
    void onSuccess(Location location);
    void onFailure();
  }
}
