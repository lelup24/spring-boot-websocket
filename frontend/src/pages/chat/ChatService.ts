import { Client } from "@stomp/stompjs";
import {
  MessageDisplayModel,
  MessageInputModel,
  MessageSendModel,
  MessageType,
} from "./types.ts";
import * as SockJS from "sockjs-client";

export class ChatService {
  private messages: MessageDisplayModel[] = [];
  private callbackFn!: (v: unknown) => unknown;

  private client!: Client;

  public connect(callbackFn: (v: unknown) => unknown) {
    this.callbackFn = callbackFn;
    const token = localStorage.getItem("access-token");

    const ws = new SockJS(
      "/ws" + "?access-token=" + localStorage.getItem("access-token"),
    );

    this.client = new Client({
      webSocketFactory: () => ws,
      connectHeaders: { Authorization: "Bearer " + token },
      onConnect: () => {
        this.client.subscribe("/topic/messages", (message) =>
          this.handleMessage(message.body),
        );
        this.client.subscribe("/user/queue/user", (message) => {
          this.handleMessage(message.body);
        });

        this.client.subscribe("/user/queue/errors", (message) => {
          alert(message);
        });
      },
      onWebSocketError: this.errorCallBack,
    });
    this.client.activate();
  }

  public send(message: MessageInputModel) {
    let msg: MessageSendModel = {
      message: message.message,
      messageType: MessageType.CHAT,
    };

    if (message.message.includes("@")) {
      const words = message.message.split(" ");
      let receiver = "";
      for (let i = 0, n = words.length; i < n; i++) {
        if (words[i].charAt(0) === "@") {
          receiver = words[i].substring(1, words[i].length);
        }
      }
      msg.receiver = receiver;
      msg.message = message.message.replace(`@${receiver}`, "");
      msg.messageType = MessageType.PRIVATE;
    }

    if (!msg.receiver && msg.messageType === MessageType.CHAT) {
      this.client.publish({
        destination: "/app/message",
        body: JSON.stringify(msg),
      });
    } else {
      this.client.publish({
        destination: "/app/private-message",
        body: JSON.stringify(msg),
      });
    }
  }

  public disconnect() {
    if (this.client !== null) {
      this.client.deactivate().then();
    }
  }

  private errorCallBack(error: Error) {
    console.log("Error: " + error);
    setTimeout(() => {
      this.connect(() => console.log("reconnected"));
    }, 5000);
  }

  private handleMessage(message: string) {
    let msg: MessageDisplayModel = JSON.parse(message);

    this.messages = [...this.messages, msg];
    if (this.messages.length > 10) {
      this.messages.shift();
    }

    this.callbackFn(this.messages);
  }
}
