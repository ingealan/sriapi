package Controllers;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/agregarUsuario")
public class AgregarUsuarioServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        if (nombre != null && !nombre.isEmpty() && password != null && !password.isEmpty() && rol != null && !rol.isEmpty()) {
            // Crear el nuevo usuario en la base de datos
            boolean exito = usuarioDAO.agregarUsuario(nombre, password, rol);

            if (exito) {
                // Usuario agregado con éxito, redirigir al login
                response.sendRedirect("login.jsp?success=true");
            } else {
                // Si hubo un error, mostrar un mensaje de error
                response.sendRedirect("agregarUsuario.jsp?error=true");
            }
        } else {
            response.sendRedirect("agregarUsuario.jsp?error=true");
        }
    }
}
