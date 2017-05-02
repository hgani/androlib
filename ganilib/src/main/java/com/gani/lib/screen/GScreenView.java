package com.gani.lib.screen;

import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gani.lib.R;
import com.gani.lib.ui.icon.GIcon;


public class GScreenView extends IScreenView {
  private static int GROUP_PRIMARY = 1;
  private static int GROUP_SECONDARY = 2;

  private ViewGroup layout;
  private ViewGroup body;
  private DrawerLayout drawer;

  private GActivity activity;
  private MenuItem selectedItem;
  private NavigationMenu navMenu;

  public GScreenView(GActivity activity) {
    super(activity);

    this.layout = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.view_screen, this);
    this.body = (ViewGroup) layout.findViewById(R.id.screen_body);
    this.drawer = (DrawerLayout) findViewById(R.id.screen_drawer);

    this.activity = activity;
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
    DrawerLayout drawer = getDrawer();
    NavigationView navView = ((NavigationView) drawer.findViewById(R.id.view_navigation));
//      this.drawerHeader = navView.inflateHeaderView(R.layout.drawer_header);
//      updateDrawerHeader();

    this.navMenu = new NavigationMenu(navView.getMenu(), actionBar);

    if (topNavigation) {
      GIcon icon = menuIcon();
      if (icon != null) {
        actionBar.setHomeAsUpIndicator(icon.drawable().actionBarSize());
      }

    }
    else {
      drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
    actionBar.setDisplayHomeAsUpEnabled(true);
  }

  protected GIcon menuIcon() {
    return null;
  }

  protected void addMenu(GIcon icon, String label, Intent intent) {
    navMenu.addItem(GROUP_PRIMARY, icon, label, intent);
  }

  /////



  private class NavigationMenu {
    private Menu menu;
    private ActionBar bar;

    NavigationMenu(Menu menu, ActionBar bar) {
      this.menu = menu;
      this.bar = bar;
    }

    private MenuItem addItem(int groupId, GIcon icon, String string, Intent intent) {
      MenuItem item = menu.add(groupId, Menu.NONE, Menu.NONE, string);
      item.setIntent(intent);
      item.setCheckable(true);  // Needed for setChecked() to work
      if (icon != null) {
        item.setIcon(icon.drawable());
      }

      if (activity.getClass().getName().equals(intent.getComponent().getClassName())) {
        selectedItem = item;
        item.setChecked(true);
        bar.setTitle(string);
      }
      return item;
    }

//    private CvMenuItem addItem(int groupId, CvIcon icon, String string, Intent intent, String nudgePath) {
//      return new CvMenuItem(addItem(groupId, icon, string, intent), nudgePath);
//    }
  }
}