import classes from "./Dashboad.module.css";

import { createSignal, onMount } from "solid-js";
import { Message } from "../../common/models/messages.tsx";
import { useNavigate } from "@solidjs/router";

async function handleDelete(id: string, setMessages: Function) {
  const response = await fetch(`/api/messages/${id}`, {
    method: "DELETE",
  });

  if (response.ok) {
    setMessages((prevMessages: Message[]) =>
      prevMessages.filter((message) => message.id !== id),
    );
  }
}

function Dashboard() {
  const navigate = useNavigate();
  let [messages, setMessages] = createSignal<Message[]>([]);

  onMount(async () => {
    const response = await fetch("/api/messages");
    const _messages = await response.json();

    const formattedMessages = _messages.map((message: Message) => {
      const sentAtDate = new Date(message.sentAt);
      const formattedSentAt = `${sentAtDate.getDate()}.${
        sentAtDate.getMonth() + 1
      }.${sentAtDate.getFullYear()}`;
      return { ...message, sentAt: formattedSentAt };
    });
    setMessages(formattedMessages);
  });

  return (
    <>
      <h1>Dashboard</h1>
      <div class={classes.tableWrapper}>
        <table class={classes.table}>
          <thead>
            <tr>
              <th>Name</th>
              <th>E-Mail</th>
              <th>Sent at</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {messages().map((message) => (
              <tr>
                <td>{message.name}</td>
                <td>{message.email}</td>
                <td>{message.sentAt}</td>
                <td>
                  <button onclick={() => navigate(`/messages/${message.id}`)}>
                    Details
                  </button>
                  <button onclick={() => handleDelete(message.id, setMessages)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default Dashboard;
