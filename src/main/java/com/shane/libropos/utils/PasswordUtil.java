package com.shane.libropos.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * PasswordUtil
 */
public class PasswordUtil {
  private static final BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();

  public static String encode(String rawPassword) {
      return encoder.encode(rawPassword);
  }

  public static boolean matches(String rawPassword, String encodedPassword) {
      return encoder.matches(rawPassword, encodedPassword);
  }
}
