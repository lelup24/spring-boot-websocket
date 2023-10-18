package de.kipper.websocket.configs.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final JwtTokenUtil jwtTokenUtil;
  private final SessionService sessionService;
  private final UserDetailsService userDetailsService;

  public AuthenticationService(
      final JwtTokenUtil jwtTokenUtil,
      final SessionService sessionService,
      final UserDetailsService userDetailsService) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.sessionService = sessionService;
    this.userDetailsService = userDetailsService;
  }

  public TokenResponse refreshToken(HttpServletRequest request) {
    final String header = request.getHeader(AUTHORIZATION);

    if (header == null || !header.startsWith("Bearer ")) {
      throw new RuntimeException();
    }

    final String token = header.split(" ")[1].trim();

    if (!jwtTokenUtil.validate(token)) {
      throw new RuntimeException();
    }

    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(jwtTokenUtil.getUsername(token));

    final UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(), null, userDetails.getAuthorities());

    String refreshToken = jwtTokenUtil.createRefreshToken(authenticationToken);

    while (refreshToken.equals(token)) {
      refreshToken = jwtTokenUtil.createRefreshToken(authenticationToken);
    }

    if (!sessionService.validate(token, request.getRemoteAddr())) {
      throw new RuntimeException("Invalid session");
    }

    final String accessToken = jwtTokenUtil.createAccessToken(authenticationToken);

    sessionService.updateSession(token, refreshToken);

    return new TokenResponse().setAccessToken(accessToken).setRefreshToken(refreshToken);
  }
}
