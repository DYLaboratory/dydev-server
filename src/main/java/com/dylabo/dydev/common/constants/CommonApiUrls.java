package com.dylabo.dydev.common.constants;

public class CommonApiUrls {

    private CommonApiUrls() {
        throw new IllegalArgumentException("Constants Class");
    }

    public static final String API_PREFIX = "/api";

    /* api root */
    public static final String API_PACKAGE_PREFIX_SYSTEM = "/system";
    public static final String API_PACKAGE_PREFIX_ADMIN = "/admin";
    public static final String API_PACKAGE_PREFIX_COMMON = "/common";

    /* package */
    public static final String API_PACKAGE_SETTINGS = "/settings";
    public static final String API_PACKAGE_FILE = "/file";
    public static final String API_PACKAGE_SESSION  = "/session";
    public static final String API_PACKAGE_USER  = "/user";
    public static final String API_PACKAGE_EXTERNAL = "/external";
    public static final String API_PACKAGE_BLOG  = "/blog";
    public static final String API_PACKAGE_FEED   = "/feed";
    public static final String API_PACKAGE_NOTICE   = "/notice";
    public static final String API_PACKAGE_WEB_SITE = "/web-site";
    public static final String API_PACKAGE_LOGIN_HISTORY = "/login-history";
    public static final String API_PACKAGE_ACCESS_HISTORY = "/access-history";

}
