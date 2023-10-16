package de.kipper.websocket.messages;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

  private final SimpMessagingTemplate simpMessagingTemplate;

  public MessageController(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  @MessageMapping("/message")
  @SendTo("/topic/messages")
  public MessageOutputDto getNachricht(final MessageDto messageDto, Principal principal) {
    final String zeit = new SimpleDateFormat("HH:mm:ss").format(new Date());
    return new MessageOutputDto(
        principal.getName(), messageDto.getMessage(), zeit, messageDto.getMessageType());
  }

  @MessageMapping("/private-message")
  public void erfolg(@Payload final MessageDto messageDto, Principal principal) {
    final String zeit = new SimpleDateFormat("HH:mm:ss").format(new Date());
    simpMessagingTemplate.convertAndSend(
        "/user/" + messageDto.getReceiver() + "/queue/user",
        new MessageOutputDto(
            principal.getName(), messageDto.getMessage(), zeit, messageDto.getMessageType()));
  }

  @MessageExceptionHandler
  @SendToUser("/queue/errors")
  public String handleException(Throwable exception) {
    return exception.getMessage();
  }
}
