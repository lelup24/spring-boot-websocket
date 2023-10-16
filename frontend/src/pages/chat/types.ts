export enum MessageType {
  CHAT = "CHAT",
  PRIVATE = "PRIVATE",
  JOIN = "JOIN",
  LEAVE = "LEAVE",
}

export interface MessageDisplayModel {
  sender: string;
  receiver?: string;
  message: string;
  time?: string;
  messageType: MessageType;
}

export interface MessageInputModel {
  receiver?: string;
  message: string;
}

export interface MessageSendModel {
  receiver?: string;
  message: string;
  time?: string;
  messageType: MessageType;
}
