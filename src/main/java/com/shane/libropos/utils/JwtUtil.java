package com.shane.libropos.utils;

import javax.management.relation.Role;

import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

/**
 * JwtUtil
 */
public class JwtUtil {
  private final RsaJsonWebKey rsaJsonWebKey;

  public JwtUtil(int keySize) throws JoseException {
    // rsa pair
    this.rsaJsonWebKey = RsaJwkGenerator.generateJwk(keySize);
    // TODO:
    this.rsaJsonWebKey.setKeyId("k1");
  }
  /**
   * jwt
   *
   * @param subject 
   * @param email
   * @param expirationMinitues
   * @return jwt claims string
   */
  public String createJWT(Integer id, String username, Role role, Boolean status, int expirationMinitues) throws JoseException {
    JwtClaims claims = new JwtClaims();
    claims.setIssuer("libropos");
    claims.setAudience(username);
    claims.setSubject(String.valueOf(id));
    claims.setExpirationTimeMinutesInTheFuture(expirationMinitues);
    claims.setGeneratedJwtId();
    claims.setIssuedAtToNow();
    claims.setNotBeforeMinutesInThePast(2);

    claims.setClaim("role", role);
    claims.setClaim("status", status);

    // Create jws
    JsonWebSignature jws = new JsonWebSignature();
    jws.setPayload(claims.toJson());
    jws.setKey(rsaJsonWebKey.getPrivateKey());
    jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

    return jws.getCompactSerialization();
  }

  /**
   * 验证JWT令牌并返回声明内容
   * @param jwt JWT令牌字符串
   * @return JWT声明
   */
  public JwtClaims validateToken(String jwt) throws InvalidJwtException {
      JwtConsumer jwtConsumer = new JwtConsumerBuilder()
              .setRequireExpirationTime()
              .setAllowedClockSkewInSeconds(30)
              .setExpectedIssuer("Issuer")
              .setExpectedAudience("Audience") 
              .setVerificationKey(rsaJsonWebKey.getKey())
              .setJwsAlgorithmConstraints(
                      ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_USING_SHA256)
              .build();
      
      return jwtConsumer.processToClaims(jwt);
  }
    
}
