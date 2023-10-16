package de.kipper.websocket.messages;

import java.util.Objects;

public class MessageOutputDto {

  private String sender;
  private String message;
  private String time;
  private MessageType messageType;

  public MessageOutputDto(String sender, String message, String time, MessageType messageType) {
    this.sender = sender;
    this.message = message;
    this.time = time;
    this.messageType = messageType;
  }

  public String getSender() {
    return sender;
  }

  public MessageOutputDto setSender(String sender) {
    this.sender = sender;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public MessageOutputDto setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getTime() {
    return time;
  }

  public MessageOutputDto setTime(String time) {
    this.time = time;
    return this;
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public MessageOutputDto setMessageType(MessageType messageType) {
    this.messageType = messageType;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MessageOutputDto that = (MessageOutputDto) o;
    return Objects.equals(sender, that.sender)
        && Objects.equals(message, that.message)
        && Objects.equals(time, that.time)
        && messageType == that.messageType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(sender, message, time, messageType);
  }
}
