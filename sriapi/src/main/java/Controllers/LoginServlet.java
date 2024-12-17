package Controllers;

import dao.UsuarioDAO;
import service.LoginService;
import service.LoginServiceSessionImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera los parámetros del formulario de login
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Valida el usuario y la contraseña con la base de datos
        boolean esValido = new UsuarioDAO().validarUsuario(username, password);
        String rol=new UsuarioDAO().obtenerRolUsuario(username);

        if (esValido) {
            // Si el usuario es válido, crea una sesión
            request.getSession().setAttribute("usuario", username);
            request.getSession().setAttribute("rol", rol);
            response.sendRedirect("login.jsp"); // Redirige a una página de consultas
        } else {
            response.sendRedirect("login.jsp?error=true"); // Redirige a login con error
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupera la sesión y verifica si el usuario está autenticado
        String username = (String) req.getSession().getAttribute("usuario");
        String rol = (String) req.getSession().getAttribute("rol");

        if (username != null) {
            // Si el usuario está autenticado, mostrar la página de bienvenida
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.println("<html lang='es'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("<title>Hola " + username + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container mt-5'>");
                out.println("<h1 class='text-center'>Hola " + username + ", ya has iniciado sesión anteriormente</h1>");

                // Si el rol es "admin", muestra el botón para agregar usuario
                if ("admin".equals(rol)) {
                    out.println("<div class='text-center mt-4'>");
                    out.println("<a class='btn btn-success' href='" + req.getContextPath() + "/agregarUsuario.jsp'>Agregar Usuario</a>");
                    out.println("</div>");
                }

                out.println("<div class='text-center mt-4'>");
                out.println("<a class='btn btn-primary' href='" + req.getContextPath() + "/login.jsp'>Volver al inicio</a>");
                out.println("<a class='btn btn-danger ms-2' href='" + req.getContextPath() + "/logout'>Cerrar sesión</a>");
                out.println("</div>");
                out.println("</div>");
                out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js'></script>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // Si el usuario no está autenticado, redirigir al formulario de login
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
