package com.gani.lib.web;

import android.net.Uri;

import com.gani.lib.http.GImmutableParams;
import com.gani.lib.screen.GActivity;

public class WebVisit {
  public enum Action {
    ADVANCE {
      @Override
      public boolean openGeneric(GActivity activity, String path, GImmutableParams params) {
        activity.startTurbolinksScreen(new PathSpec(path), params);
//        activity.startActivity(GScreenTurbolinks.intent(new PathSpec(path), params));
        return true;
      }

      @Override
      public void openSpecific(GActivity activity, Runnable command) {
        command.run();
      }
    },
    REPLACE {
      @Override
      public boolean openGeneric(GActivity activity, String path, GImmutableParams params) {
        return false;
      }

      @Override
      public void openSpecific(GActivity activity, Runnable command) {
        command.run();
        activity.finish();
      }
    };

    public abstract boolean openGeneric(GActivity activity, String path, GImmutableParams params);
    public abstract void openSpecific(GActivity activity, Runnable command);
  }

//  private GActivity activity;
//  private Uri uri;
//  private Action action;
//
//  public WebVisit(GActivity activity, Uri uri, Action action) {
//    this.activity = activity;
//    this.uri = uri;
//    this.action = action;
//  }

//  private void handleAppAction(String path) {
//    switch (path.replaceFirst("^/app/", "")) {
//      case "copy":
//        CrossVersionClipboard.addText(uri.getQueryParameter("text"));
//        break;
//      case "share":
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, uri.getQueryParameter("text"));
//        sendIntent.setType("text/plain");
//        activity.startActivity(Intent.createChooser(sendIntent, uri.getQueryParameter("title")));
//        break;
//
//    }
//
//    activity.finish();
//  }
//
//  public boolean handle() {
//    String domain = uri.getHost();
//    if (uri.getPort() == 3000) {  // Development only
//      domain += ":" + uri.getPort();
//    }
//
//    if (domain.equals(com.thecrowdvoice.app.build.Build.INSTANCE.getApiDomain())) {
//      InternalPathMatcher[] matchers = new InternalPathMatcher[]{
//          new ScreenInternalPostPathMatcher(),
//          new ScreenInternalGuildPathMatcher(),
//          new ScreenInternalUserPathMatcher(),
//          new ScreenInternalConversationPathMatcher()
//      };
//
//      for (final InternalPathMatcher matcher : matchers) {
//        final Object id = matcher.extractId(uri);
//        if (id != null) {
//          Runnable command = new Runnable() {
//            @Override
//            public void run() {
//              matcher.launch(id, activity);
//            }
//          };
//          action.openSpecific(activity, command);
//          return true;
//        }
//      }
//
//      String path = uri.getPath();
//      if (path.startsWith("/app")) {
//        handleAppAction(path);
//        return true;
//      }
//
//      if (path.equals("/users/me")) {
//        activity.startActivity(ScreenMeView.intent());
//        return true;
//      }
//
//      if (path.equals("/users/me/conversations")) {
//        activity.startActivity(ScreenConversationList.intentForTurbolinksRedirection());
//        return true;
//      }
//
//      // NOTE: This might not work for URIs containing query strings. We'll tackle this when the bridge is crossed.
//      //
//      // Don't worry about screen title. It's not yet worth implementing a routing solution such as one described at https://github.com/turbolinks/turbolinks/pull/33
////      activity.startActivity(GScreenTurbolinks.intent(new PathSpec(uri.getPath())));
////      activity.startActivity(GScreenTurbolinks.intent(new PathSpec(path));
//      GLog.i(getClass(), "Opening page: " + (path + "?" + uri.getQuery()) + " -- " + uri);
//      return action.openGeneric(activity, path, GImmutableParams.fromUri(uri));
//    }
//    return true;
//  }
}
