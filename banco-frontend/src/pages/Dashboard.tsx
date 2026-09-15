import { useState } from "react";
import { cerrarSesion } from "../services/authService";

function Dashboard() {
  const [menuAbierto, setMenuAbierto] = useState(false);

  const handleLogout = () => {
    cerrarSesion();
    window.location.href = "/";
  };

  return (
    <div className="dashboard">

      <header className="dashboard-header">
        <div className="header-left">
          <button
            className="menu-button"
            onClick={() => setMenuAbierto(!menuAbierto)}
          >
            ☰
          </button>

          <h1>Banco Cloud</h1>
        </div>

        <button
          className="logout-button"
          onClick={handleLogout}
        >
          Cerrar sesión
        </button>
      </header>

      <aside className={`sidebar ${menuAbierto ? "open" : ""}`}>
        <nav>
          <a href="/dashboard">Inicio</a>
          <a href="/cuentas">Mis cuentas</a>
          <a href="/solicitudes">Solicitudes</a>
        </nav>
      </aside>

      <main className="dashboard-content">

        <section className="welcome">
          <h2>Bienvenido a Banco Cloud</h2>

          <p>
            Gestiona tus cuentas bancarias y solicitudes
            desde un solo lugar.
          </p>
        </section>

        <section className="dashboard-cards">

          <article className="dashboard-card">
            <h3>Mis cuentas</h3>

            <p>
              Consulta tus cuentas y revisa tus saldos.
            </p>

            <a href="/cuentas">
              Ver cuentas
            </a>
          </article>

          <article className="dashboard-card">
            <h3>Solicitudes</h3>

            <p>
              Consulta el estado de tus solicitudes
              de apertura de cuenta.
            </p>

            <a href="/solicitudes">
              Ver solicitudes
            </a>
          </article>

        </section>

      </main>

    </div>
  );
}

export default Dashboard;
