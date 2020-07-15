package com.cogent.insurance.shared;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Utils {

  private static final Random RANDOM = new Random();
  private static final String ALPHABET =
      "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

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
}
