package com.cogent.insurance.shared;

import com.cogent.insurance.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@Component
public class Utils {

  private static final Random RANDOM = new Random();
  private static final String ALPHABET =
      "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
  private static final int JWT_MAX_TIME = 86400000;

  public static boolean hasTokenExpired(String token) {
    Claims claims =
        Jwts.parser()
            .setSigningKey(SecurityConstants.JWT_SECRET)
            .parseClaimsJws(token)
            .getBody();

    Date tokenExpirationDate = claims.getExpiration();
    Date todayDate = new Date();

    return tokenExpirationDate.before(todayDate);
  }

  public String generateId(int length) {
    return generateRandomString(length);
  }

  private String generateRandomString(int length) {
    StringBuffer randomValue = new StringBuffer(length);

    for (int i = 0; i < length; i++) {
      randomValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
    }
    return new String(randomValue);
  }

  public String generateEmailVerificationToken(String userId) {

    return Jwts.builder()
        .setSubject(userId)
        .setExpiration(new Date(System.currentTimeMillis() + JWT_MAX_TIME))
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
        .compact();
  }
}
