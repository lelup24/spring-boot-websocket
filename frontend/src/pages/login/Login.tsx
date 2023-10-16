import classes from "./Login.module.css";
import { createSignal } from "solid-js";
import { useLocation, useNavigate } from "@solidjs/router";
import { AuthService } from "../../common/Auth.service.tsx";

function Login() {
  const [formData, setFormData] = createSignal({ username: "", password: "" });
  const navigate = useNavigate();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const [isFormValid, setFormValid] = createSignal(true);

  if (AuthService.isAuthenticated()) {
    navigate("/dashboard");
  }

  async function handleSubmit(e: Event) {
    e.preventDefault();

    const form = e.target as HTMLFormElement;
    if (!form.checkValidity()) {
      setFormValid(false);
      return;
    }

    const response = await fetch("/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData()),
    });

    if (response.ok) {
      localStorage.setItem(
        "access-token",
        response.headers.get("access-token")!,
      );

      const fromParam = params.get("from");

      if (!fromParam || fromParam === "/") {
        navigate("/dashboard");
        return;
      }

      navigate(fromParam);
      return;
    }
  }

  return (
    <>
      <main class={classes.main}>
        <h1>Login</h1>
        <form
          noValidate
          autocomplete={"off"}
          class={classes.form}
          onSubmit={handleSubmit}
        >
          <input
            required
            type="text"
            onChange={(e) =>
              setFormData({ ...formData(), username: e.target.value })
            }
            onKeyDown={(e) => {
              if (e.keyCode === 13) {
                handleSubmit(e).then();
              }
            }}
          />
          {!isFormValid() && (
            <div class={classes.error}>Username is required.</div>
          )}

          <input
            required
            type="password"
            onInput={(e) =>
              setFormData({ ...formData(), password: e.target.value })
            }
            onKeyDown={(e) => {
              if (e.keyCode === 13) {
                handleSubmit(e).then();
              }
            }}
          />
          {!isFormValid() && (
            <div class={classes.error}>Password is required.</div>
          )}

          <button type="submit">Sign in</button>
        </form>
      </main>
    </>
  );
}

export default Login;
