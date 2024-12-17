<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Usuario</title>

    <!-- Vincular Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJx3Rn7S3vHY6FqvS7lX1aWlfuFY2aXwqZ9yivFhZZlG3Yr7KpU4mn2HftCu" crossorigin="anonymous">

    <!-- Vincular Font Awesome para iconos -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg, #5d9cec, #4c9b9b); /* Colores fríos azules y verdes */
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .form-container {
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
            color: #4c9b9b; /* Verde claro */
            margin-bottom: 10px;
            font-weight: bold;
        }

        .form-control, .form-select {
            border-radius: 8px;
            padding: 12px;
            font-size: 14px;
            border: 1px solid #4c9b9b;
            transition: 0.3s;
        }

        .form-control:focus, .form-select:focus {
            border-color: #2b7f7f;
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
            background-color: #4c9b9b;
            color: white;
            width: 100%;
            margin-top: 20px;
        }

        .btn-secondary:hover {
            background-color: #357a7a;
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
    </style>
</head>
<body>

<div class="form-container">
    <h1><i class="fas fa-user-plus"></i> Agregar Nuevo Usuario</h1>

    <form action="agregarUsuario" method="post">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre de Usuario</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required placeholder="Ingrese el nombre de usuario">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password" required placeholder="Ingrese la contraseña">
        </div>

        <div class="mb-3">
            <label for="rol" class="form-label">Rol</label>
            <select id="rol" name="rol" class="form-select" required>
                <option value="admin">Administrador</option>
                <option value="user">Usuario</option>
            </select>
        </div>

        <button type="submit" class="btn w-100 mb-3"><i class="fas fa-user-plus"></i> Agregar Usuario</button>
    </form>

    <br>
    <a href="login.jsp" class="btn btn-secondary w-100">Volver al Login</a>
</div>

<!-- Vincular scripts de Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0yIUl9vLOr8g6bDLFv5xfLfi/jhReY27a+KCpGkdjDjqFdp9" crossorigin="anonymous"></script>

</body>
</html>
