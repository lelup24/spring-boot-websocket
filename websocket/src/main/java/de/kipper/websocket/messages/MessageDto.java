package de.kipper.websocket.messages;

import java.util.Objects;

public class MessageDto {

  private String sender;
  private String receiver;
  private String message;
  private MessageType messageType;

  public String getSender() {
    return sender;
  }

  public MessageDto setSender(String sender) {
    this.sender = sender;
    return this;
  }

  public String getReceiver() {
    return receiver;
  }

  public MessageDto setReceiver(String receiver) {
    this.receiver = receiver;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public MessageDto setMessage(String message) {
    this.message = message;
    return this;
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public MessageDto setMessageType(MessageType messageType) {
    this.messageType = messageType;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MessageDto that = (MessageDto) o;
    return Objects.equals(sender, that.sender)
        && Objects.equals(receiver, that.receiver)
        && Objects.equals(message, that.message)
        && messageType == that.messageType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(sender, receiver, message, messageType);
  }
}
