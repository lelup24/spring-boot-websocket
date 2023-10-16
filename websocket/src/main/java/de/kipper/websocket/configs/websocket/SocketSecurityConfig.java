package de.kipper.websocket.configs.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;

// Use when CSRF is configurable or if you provide a custom CSRF implementation
// @Configuration
// @EnableWebSocketSecurity
public class SocketSecurityConfig {

  @Bean
  public MessageMatcherDelegatingAuthorizationManager.Builder messages() {
    return MessageMatcherDelegatingAuthorizationManager.builder();
  }

  @Bean
  AuthorizationManager<Message<?>> messageAuthorizationManager(
      MessageMatcherDelegatingAuthorizationManager.Builder messages) {
    messages
        .simpTypeMatchers(
            SimpMessageType.CONNECT,
            SimpMessageType.HEARTBEAT,
            SimpMessageType.UNSUBSCRIBE,
            SimpMessageType.DISCONNECT)
        .permitAll()
        .nullDestMatcher()
        .authenticated()
        .simpSubscribeDestMatchers("/user/queue/errors")
        .authenticated()
        .simpDestMatchers("/app/**")
        .authenticated()
        .simpSubscribeDestMatchers("/ws/**", "/user/**", "/topic/**", "/queue/**")
        .authenticated()
        .simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE)
        .authenticated()
        .anyMessage()
        .authenticated();

    return messages.build();
  }
}
