package com.cogent.insurance.security;

import com.cogent.insurance.SpringApplicationContext;

public class SecurityConstants {

  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String AUTHENTICATE_URL = "/api/auth/**";
  public static final String VERIFICATION_EMAIL_URL = "/api/auth/email-verification";
  public static final String JWT_EXPIRATION_TIME = "86400000";
  public static final String JWT_SECRET = "randomSecret";

  public static String getTokenSecret() {
    final AppPropertiesReader appProperties = (AppPropertiesReader) SpringApplicationContext.getBean("appProperties");

    return appProperties.getTokenSecret();
  }

}
