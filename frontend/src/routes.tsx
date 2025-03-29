import { RouteObject } from "react-router";
import Login from "./Login";
import App from "./App";

const routes: RouteObject[] = [
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/login",
    element: <Login />,
  },
];

export default routes;
