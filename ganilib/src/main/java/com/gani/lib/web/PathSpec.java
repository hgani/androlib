package com.gani.lib.web;

import com.gani.lib.ui.Ui;

import java.io.Serializable;

public class PathSpec implements Serializable {
  private String path;
  private String title;

  public PathSpec(String path, String title) {
    this.path = path;
    this.title = title;
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
}