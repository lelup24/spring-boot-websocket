/* @refresh reload */
import {render} from "solid-js/web";

import "./index.css";
import App from "./App";
import {Router} from "@solidjs/router";
import {Interceptor} from "./common/interceptor.tsx";

const root = document.getElementById("root");
Interceptor.init();

render(
  () => (
    <Router>
      <App />
    </Router>
  ),
  root!,
);
