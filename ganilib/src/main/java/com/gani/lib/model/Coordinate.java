package com.gani.lib.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

// A Serializable alternative to LatLng
public class Coordinate implements Serializable {
  public final double latitude;
  public final double longitude;

  public Coordinate(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public LatLng toLatLng() {
    return new LatLng(latitude, longitude);
  }
}
