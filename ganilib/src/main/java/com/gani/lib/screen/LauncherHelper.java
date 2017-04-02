package com.gani.lib.screen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class LauncherHelper {
  private Context context;

  public LauncherHelper(Context context) {
    this.context = context;
  }

  public void map(String address) {
    String uri = "http://maps.google.com/maps?q=" + address;
    // String uri = "geo:0,0?q=" + object.getNullableString("location");
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    // intent.setPackage("com.google.android.apps.maps");
    context.startActivity(intent);
  }

  public void call(String number) {
    // TODO
  }

  public void mail(String to, String subject, String message) {
    // TODO
  }
}
