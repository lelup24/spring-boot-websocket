import { NavLink, Outlet } from "@solidjs/router";
import classes from "./Home.module.css";

function Home() {
  return (
    <>
      <div class={classes.nav}>
        <NavLink href="/dashboard">Dashboard</NavLink>
        <NavLink href="/chat">Chat</NavLink>
        <NavLink href="/about">About</NavLink>
        <div
          onclick={() => {
            localStorage.removeItem("access-token");
          }}
        >
          <NavLink href="/login">Logout</NavLink>
        </div>
      </div>

      <main class={classes.main}>
        <Outlet />
      </main>
    </>
  );
}

export default Home;
