package de.kipper.websocket.configs.websocket;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import de.kipper.websocket.configs.security.JwtTokenUtil;
import java.util.Objects;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class WebSocketInterceptor implements ChannelInterceptor {

  private final JwtTokenUtil jwtTokenUtil;
  private final UserDetailsService userDetailsService;

  public WebSocketInterceptor(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {

    final StompHeaderAccessor accessor =
        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

    if (accessor == null) {
      throw new AccessDeniedException("Token is not valid");
    }

    if (!StompCommand.CONNECT.equals(accessor.getCommand())) {
      return message;
    }

    final String token =
        Objects.requireNonNull(accessor.getNativeHeader(AUTHORIZATION)).get(0).split(" ")[1].trim();

    if (!jwtTokenUtil.validate(token)) {
      throw new AccessDeniedException("Token is not valid");
    }

    final String username = jwtTokenUtil.getUsername(token);

    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    final UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    accessor.setUser(authentication);
    return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
  }
}
