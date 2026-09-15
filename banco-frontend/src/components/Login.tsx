import { useState } from "react";
import { iniciarSesion } from "../services/authService";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (event: React.FormEvent) => {
    event.preventDefault();
    setError("");

    try {
      await iniciarSesion(username, password);

      window.location.href = "/";
    } catch (err) {
      console.error(err);
      setError("Usuario o contraseña incorrectos.");
    }
  };

  return (
    <div>
      <h1>Banco Cloud</h1>

      <h2>Iniciar sesión</h2>

      <form onSubmit={handleLogin}>
        <div>
          <label>Correo electrónico</label>

          <input
            type="email"
            value={username}
            onChange={(event) => setUsername(event.target.value)}
            required
          />
        </div>

        <div>
          <label>Contraseña</label>

          <input
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            required
          />
        </div>

        <button type="submit">
          Iniciar sesión
        </button>
      </form>

      {error && (
        <p>{error}</p>
      )}
    </div>
  );
}

export default Login;
