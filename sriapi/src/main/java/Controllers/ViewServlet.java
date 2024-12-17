package Controllers;

import dao.VistaConsultaDetallesDAO;
import models.VistaConsultaDetallesModelo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/consultarbase")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Crear una instancia del DAO para obtener los datos
            VistaConsultaDetallesDAO dao = new VistaConsultaDetallesDAO();
            List<VistaConsultaDetallesModelo> detallesList = dao.getDetalles();  // Obtener los detalles de la vista

            // Pasar los datos al JSP
            req.setAttribute("detallesList", detallesList);

            // Redirigir al JSP para mostrar los datos
            req.getRequestDispatcher("mostrarDetalles.jsp").forward(req, resp);  // Mostrar en el JSP

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al consultar la base de datos.");
        }
    }
}
