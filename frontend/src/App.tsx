import "./App.css";
import { Route, Routes, useLocation, useNavigate } from "@solidjs/router";
import Login from "./pages/login/Login.tsx";
import RouteGuard from "./common/route.guard.tsx";
import Home from "./pages/home/Home.tsx";
import Detail from "./pages/detail/Detail.tsx";
import { lazy, onMount } from "solid-js";
import Chat from "./pages/chat/Chat.tsx";

function App() {
  const navigate = useNavigate();
  const location = useLocation();

  const target =
    location.pathname != ("" || "/") ? location.pathname : "/dashboard";

  onMount(() => {
    navigate(target);
  });

  const Dashboard = lazy(() => import("./pages/dashboard/Dashboard"));
  const About = lazy(() => import("./pages/about/About"));

  return (
    <>
      <Routes>
        <Route path="/login" component={Login} />
        <Route path="/" component={Home}>
          <Route
            path="/dashboard"
            element={<RouteGuard component={Dashboard} />}
          />
          <Route path="/chat" element={<RouteGuard component={Chat} />} />
          <Route
            path="/messages/:id"
            element={<RouteGuard component={Detail} />}
          />
          <Route path="/about" element={<RouteGuard component={About} />} />
        </Route>
        <Route path={"*"} element={<div>404</div>} />
      </Routes>
    </>
  );
}

export default App;
