package com.gani.lib.screen;

import android.database.ContentObserver;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gani.lib.R;


public class GScreenView extends FrameLayout {
  private ViewGroup layout;
  private ViewGroup body;
  private DrawerLayout drawer;

  public GScreenView(GActivity activity) {
    super(activity);

    this.layout = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.view_screen, this);
    this.body = (ViewGroup) layout.findViewById(R.id.screen_body);
    this.drawer = (DrawerLayout) findViewById(R.id.screen_drawer);
  }

  public void openDrawer() {
    drawer.openDrawer(GravityCompat.START);
  }

  public Toolbar getToolbar() {
    return (Toolbar) layout.findViewById(R.id.screen_toolbar);
  }

  public void setBody(int resId) {
    LayoutInflater.from(getContext()).inflate(resId, body);
  }

  protected final DrawerLayout getDrawer() {
    return drawer;
  }

  ///// Navigation /////

  protected void initNavigation(boolean topNavigation, ActionBar actionBar) {
    // To be overridden
  }

  /////
}