import { createSignal, JSX, onCleanup, onMount } from "solid-js";
import {
  MessageDisplayModel,
  MessageInputModel,
  MessageType,
} from "./types.ts";
import { ChatService } from "./ChatService.ts";
import classes from "./Chat.module.css";

function Chat(this: any): JSX.Element {
  let [messages, setMessages] = createSignal<MessageDisplayModel[]>([]);
  let [message, setMessage] = createSignal<MessageInputModel>();
  let [room, setRoom] = createSignal<string>("1");

  const chatService = new ChatService();

  onMount(async () => {
    chatService.connect(setMessages);
  });

  let input!: HTMLInputElement;

  onCleanup(async () => {
    chatService.disconnect();
  });

  const onSend = () => {
    chatService.send(message()!);
    setMessage({ message: "" });
    input.value = "";
  };

  const onSelectRoom = (
    e: Event & { currentTarget: HTMLSelectElement; target: HTMLSelectElement },
  ) => {
    if (room()) {
      chatService.leaveRoom();
    }
    setRoom(e.target.value);
    chatService.joinRoom(room());
  };

  return (
    <>
      <select onChange={onSelectRoom}>
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
      <h1>Chat</h1>
      {messages().map((message: MessageDisplayModel) => () => (
        <div class={classes.message}>
          <div class={classes.meta}>
            <div>{message.time}</div>
            <div>{message.sender}</div>
            {message.messageType === MessageType.PRIVATE ? (
              <div> whispered:</div>
            ) : (
              <div> says:</div>
            )}
          </div>
          <div>{message.message}</div>
        </div>
      ))}
      <input
        ref={input}
        placeholder="message"
        onChange={(e) => setMessage({ message: e.target.value })}
      />
      <button onclick={onSend}>Senden</button>
    </>
  );
}

export default Chat;
