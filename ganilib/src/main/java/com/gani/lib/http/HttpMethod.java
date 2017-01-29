package com.gani.lib.http;

public enum HttpMethod {
  POST, PATCH, DELETE, GET;

  public static HttpMethod from(String method) {
    switch (method) {
      case "patch":
        return PATCH;
      case "put":
        return PATCH;
      case "delete":
        return DELETE;
      default:
        return POST;
    }
  }
}
