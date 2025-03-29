import axios from "axios";
import { useState, useMemo } from "react";
import { useQuery } from "@tanstack/react-query";
import { AuthControllerApi, LoginRequestDto } from "./api";

function Login() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const authControllerApi = new AuthControllerApi();

  const { refetch, isError, isLoading, isSuccess } = useQuery({
    queryKey: ["login"],
    enabled: false,
    queryFn: () => {
      const requestBody: LoginRequestDto = {
        email: "jcloeter@gmail.com",
        password: "COYS",
      };

      return authControllerApi.login(requestBody);
    },
  });

  const submitLogin = async () => {
    const { data } = await refetch();
    const accessToken: string | undefined = data?.data.accessToken;

    if (accessToken) {
      axios.defaults.headers.common = {
        Authorization: `Bearer ` + accessToken,
      };
      setIsLoggedIn(true);
    }
  };

  const displayMessage: string = useMemo(() => {
    return isLoading
      ? "Loading..."
      : isError
      ? `Error logging in user ${username}`
      : isSuccess
      ? "User is logged in."
      : "User is not logged in";
  }, [isError, isSuccess, isLoading, username]);

  if (isLoggedIn) {
    console.log("redirecting now...");
  }

  return (
    <>
      <h1>Sales App</h1>

      <div className="card">{displayMessage}</div>

      <input
        type="text"
        placeholder="name"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />

      <input
        type="text"
        placeholder="pass"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />

      <div className="card">
        <button onClick={submitLogin}>Login</button>
      </div>
    </>
  );
}

export default Login;
