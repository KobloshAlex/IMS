package com.cogent.insurance.security;

public class SecurityConstants {

  public static final long EXPIRATION_TIME = 864000000; // 10 DAYS
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SING_UP_URL = "/api/customers";
  public static final String TOKEN_SECRET = "abcd1234";
}
