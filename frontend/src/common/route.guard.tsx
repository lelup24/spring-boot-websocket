import { AuthService } from "./Auth.service.tsx";

import { useLocation, useNavigate } from "@solidjs/router";
import { Component } from "solid-js";

const RouteGuard = ({ component: Component }: { component: Component }) => {
  const navigate = useNavigate();
  const location = useLocation();

  if (!AuthService.isAuthenticated()) {
    navigate("/login?from=" + location.pathname, { replace: true });
    return;
  }

  return <Component />;
};

export default RouteGuard;
