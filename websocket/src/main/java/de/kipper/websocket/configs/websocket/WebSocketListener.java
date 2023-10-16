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
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketListener {

  private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);

  private final SimpMessageSendingOperations messagingTemplate;

  public WebSocketListener(SimpMessageSendingOperations messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  @EventListener
  public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    logger.info("Received a new web socket connection");

    Principal principal = event.getUser();
    if (principal != null) {
      logger.info("User Connected : " + principal.getName());
      final String zeit = new SimpleDateFormat("HH:mm:ss").format(new Date());
      MessageOutputDto chatMessage =
          new MessageOutputDto(
              "System", principal.getName() + " joined the chat!", zeit, MessageType.JOIN);
      messagingTemplate.convertAndSend("/topic/messages", chatMessage);
    }
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {

    Principal principal = event.getUser();
    if (principal != null) {
      logger.info("User Disconnected : " + principal.getName());
      final String zeit = new SimpleDateFormat("HH:mm:ss").format(new Date());
      MessageOutputDto chatMessage =
          new MessageOutputDto(
              "System", principal.getName() + " left the chat!", zeit, MessageType.LEAVE);

      messagingTemplate.convertAndSend("/topic/messages", chatMessage);
    }
  }
}
