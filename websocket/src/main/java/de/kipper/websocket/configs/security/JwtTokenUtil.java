package de.kipper.websocket.configs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class JwtTokenUtil {

  private RSAPrivateKey privateKey;
  private RSAPublicKey publicKey;
  private final Algorithm algorithm;
  private final RsaProperties rsaProperties;

  JwtTokenUtil(RsaProperties rsaProperties) {
    this.rsaProperties = rsaProperties;
    loadKeys();
    algorithm = Algorithm.RSA256(publicKey, privateKey);
  }

  public String createRefreshToken(final Authentication authentication) {
    return JWT.create()
        .withSubject(authentication.getName())
        .withIssuer("slack-backend")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(new Date().getTime() + 1800000000))
        .sign(algorithm);
  }

  public String createAccessToken(final Authentication authentication) {

    List<String> roles =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .map(role -> role.replace("ROLE_", ""))
            .toList();

    return JWT.create()
        .withSubject(authentication.getName())
        .withIssuer("slack-backend")
        .withClaim("roles", roles)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(new Date().getTime() + 300000000))
        .sign(algorithm);
  }

  public Boolean validate(final String tokenAsString) {
    try {
      final Verification verification = JWT.require(algorithm);
      final JWTVerifier verifier = verification.build();
      verifier.verify(tokenAsString);
      return true;
    } catch (final Exception e) {
      return false;
    }
  }

  public String getUsername(final String token) {
    final Verification verification = JWT.require(algorithm);
    final JWTVerifier verifier = verification.build();
    final DecodedJWT decodedJWT = verifier.verify(token);
    return decodedJWT.getSubject();
  }

  public Instant getExpiresAt(final String token) {
    final Verification verification = JWT.require(algorithm);
    final JWTVerifier verifier = verification.build();
    final DecodedJWT decodedJWT = verifier.verify(token);
    return decodedJWT.getExpiresAtAsInstant();
  }

  private void loadKeys() {

    try {
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PKCS8EncodedKeySpec keySpec =
          new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaProperties.getPrivateKey()));
      privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

      X509EncodedKeySpec publicKeySpec =
          new X509EncodedKeySpec(Base64.getDecoder().decode(rsaProperties.getPublicKey()));
      publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }
}
