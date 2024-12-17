<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="utils.DatabaseConnection" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>

    <!-- Vincular Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJx3Rn7S3vHY6FqvS7lX1aWlfuFY2aXwqZ9yivFhZZlG3Yr7KpU4mn2HftCu" crossorigin="anonymous">

    <!-- Vincular Font Awesome para iconos -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #0072c6; /* Colores fríos azules y verdes */
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            background-color: white;
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            text-align: center;
            animation: bounceIn 1s ease-in-out;
        }

        h1 {
            color: #1e3d58; /* Azul oscuro */
            margin-bottom: 30px;
            font-size: 2rem;
        }

        .form-label {
            color: #1e3d58; /* Verde claro */
            margin-bottom: 10px;
            font-weight: bold;
        }

        .form-control, .form-select {
            border-radius: 8px;
            padding: 12px;
            font-size: 14px;
            border: 1px solid #1e3d58;
            transition: 0.3s;
        }

        .form-control:focus, .form-select:focus {
            color: #1e3d58;
            box-shadow: 0 0 10px rgba(43, 127, 127, 0.3);
        }

        .btn {
            background-color: #5d9cec;
            color: white;
            padding: 12px;
            font-size: 16px;
            border-radius: 8px;
            transition: background-color 0.3s, transform 0.3s;
        }

        .btn:hover {
            background-color: #2a6fa3; /* Azul más oscuro */
            transform: scale(1.05);
        }

        .btn-secondary {
            color: #1e3d58;
            color: white;
            width: 100%;
            margin-top: 20px;
        }

        .btn-secondary:hover {
            color: #1e3d58;
        }

        /* Espaciado adicional entre los campos */
        .mb-3 {
            margin-bottom: 20px;
        }

        /* Animación de entrada */
        @keyframes bounceIn {
            0% { transform: translateY(-200px); opacity: 0; }
            60% { transform: translateY(30px); opacity: 1; }
            100% { transform: translateY(0); }
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        @keyframes shake {
            0% { transform: translateX(0); }
            25% { transform: translateX(-5px); }
            50% { transform: translateX(5px); }
            75% { transform: translateX(-5px); }
            100% { transform: translateX(0); }
        }
    </style>
</head>
<body>

<%
    // Obtener la sesión actual
    session = request.getSession();
    String username = (session != null) ? (String) session.getAttribute("usuario") : null;
%>

<% if (username != null) { %>
<!-- Si el usuario está autenticado, mostrar la vista de bienvenida -->
<div class="login-container">
    <h1><i class="fas fa-user-circle"></i> Bienvenido, <%= username %>!</h1>
    <a href="consulta.html" class="btn btn-secondary w-100">
        <i class="fas fa-search"></i> Ir a Consultas
    </a>
    <a href="/sriapi/logout" class="btn btn-secondary w-100">
        <i class="fas fa-search"></i> Cerrar Sesion
    </a>
</div>
<% } else { %>
<!-- Si el usuario no está autenticado, mostrar el formulario de inicio de sesión -->
<div class="login-container">
    <h1><i class="fas fa-sign-in-alt"></i> Iniciar Sesión</h1>

    <form action="login" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Usuario</label>
            <input type="text" class="form-control" id="username" name="username" required placeholder="Ingrese su usuario">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password" required placeholder="Ingrese su contraseña">
        </div>

        <button type="submit" class="btn w-100 mb-3"><i class="fas fa-sign-in-alt"></i> Ingresar</button>
        <a href="index.html" class="btn btn-secondary w-100 mb-3">Inicio</a>

    </form>

    <% if (request.getParameter("error") != null) { %>
    <div class="alert alert-danger mt-4" role="alert">
        <i class="fas fa-exclamation-triangle"></i> Usuario o contraseña incorrectos.
    </div>
    <% } %>
</div>
<% } %>

<!-- Vincular scripts de Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
