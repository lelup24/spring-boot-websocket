import { Message } from "../../common/models/messages.tsx";
import { useParams } from "@solidjs/router";
import { createSignal, onMount } from "solid-js";

function Detail() {
  const params = useParams();

  let [message, setMessage] = createSignal<Message>();
  let [isLoading, setLoading] = createSignal(true); // Trac

  onMount(async () => {
    const response = await fetch(`/api/messages/${params.id}`);
    const message = await response.json();
    setMessage(message);
    setLoading(false);
  });

  return (
    <>
      <h1>Message Details</h1>
      {isLoading() ? <div>Loading...</div> : <div>{message()!.message}</div>}
    </>
  );
}

export default Detail;
