package com.shane.libropos.properties;

import lombok.Data;

/**
 * JwtProperties
 */
@Data
public class JwtProperties {
  private String secretKey;
  private long ttl;
  private String tokenName;
}
