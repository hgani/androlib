package com.gani.web;

import android.net.Uri;

import com.gani.lib.ui.Ui;
import com.gani.web.htmlform.HTMLFormOnSubmitListener;

import java.io.Serializable;

public class PathSpec implements Serializable {
  private String path;
  private String title;
  private HTMLFormOnSubmitListener formListener;

  public PathSpec(String path, String title) {
    this.path = path;
    this.title = title;
  }

  public PathSpec(String path, String title, HTMLFormOnSubmitListener formListener) {
    this(path, title);

    this.formListener = formListener;
  }

  public PathSpec(String path) {
    this(path, null);
  }

  public PathSpec(String path, int titleResId) {
    this(path, Ui.str(titleResId));
  }

  public String getPath() {
    return path;
  }

  public String getTitle() {
    return title;
  }

  public HTMLFormOnSubmitListener getFormListener() {
    return formListener;
  }

  public boolean matches(Uri uri) {
    return path.equals(uri.getPath());
  }
}