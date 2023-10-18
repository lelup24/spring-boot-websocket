package de.kipper.websocket.configs.websocket;

import de.kipper.websocket.messages.MessageOutputDto;
import de.kipper.websocket.messages.MessageType;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class WebSocketListener {

  private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);

  private final SimpMessageSendingOperations messagingTemplate;

  public WebSocketListener(SimpMessageSendingOperations messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  @EventListener
  public void handleWebSocketConnectListener(SessionSubscribeEvent event) {
    logger.info("Received a new web socket connection");

    final Principal principal = event.getUser();
    final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

    if (accessor.getDestination() == null) {
      throw new RuntimeException();
    }

    if (principal != null) {
      logger.info("User Connected : " + principal.getName());

      final String zeit = new SimpleDateFormat("HH:mm:ss").format(new Date());
      final MessageOutputDto chatMessage =
          new MessageOutputDto(
              "System", principal.getName() + " joined the chat!", zeit, MessageType.JOIN);
      messagingTemplate.convertAndSend(accessor.getDestination(), chatMessage);
    }
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionUnsubscribeEvent event) {

    final Principal principal = event.getUser();
    final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

    if (accessor.getDestination() == null) {
      throw new RuntimeException();
    }

    if (principal != null) {
      logger.info("User Disconnected : " + principal.getName());

      final String zeit = new SimpleDateFormat("HH:mm:ss").format(new Date());
      final MessageOutputDto chatMessage =
          new MessageOutputDto(
              "System", principal.getName() + " left the chat!", zeit, MessageType.LEAVE);

      messagingTemplate.convertAndSend(accessor.getDestination(), chatMessage);
    }
  }
}
