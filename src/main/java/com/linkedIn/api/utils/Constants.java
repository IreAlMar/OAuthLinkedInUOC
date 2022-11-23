package com.linkedIn.api.utils;

/**
 * Constants for use
 */
public class Constants {
    public static final String THREE_LEGGED_TOKEN_GEN_ENDPOINT = "login";

    public static final String IRENE_MAIN_PAGE = "main";

    public static final String AUTHORIZE_URL = "https://www.linkedin.com/oauth/v2/authorization";

    public static final String REQUEST_TOKEN_URL = "https://www.linkedin.com/oauth/v2/accessToken";

    public static final String CLIENT_SECRET = "client_secret";

    public static final String CLIENT_ID = "client_id";

    public static final String REFRESH_TOKEN = "refresh_token";

    public static final String CODE = "code";

    public static final String REDIRECT_URI = "redirect_uri";

    public static final String GRANT_TYPE = "grant_type";
    public static final String TOKEN_URL_PARAMETER_NAME = "oauth2_access_token";

    public enum GrantType {
        CLIENT_CREDENTIALS("client_credentials"), AUTHORIZATION_CODE("authorization_code"), REFRESH_TOKEN("refresh_token");
        private String grantType;

        GrantType(final String grantType) {
            this.grantType = grantType;
        }

        public String getGrantType() {
            return grantType;
        }
    }

    public static final String SAMPLE_APP_BASE = "java-sample-application";
    public static final String SAMPLE_APP_VERSION = "version 1.0";
    enum AppName {
        OAuth,
        Marketing;
    }
    public static final String USER_AGENT_OAUTH_VALUE = String.format("%s (%s, %s)", SAMPLE_APP_BASE, SAMPLE_APP_VERSION, AppName.OAuth.name());
}
