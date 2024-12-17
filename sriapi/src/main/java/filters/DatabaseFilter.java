package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import utils.DatabaseConnection;

@WebFilter("/*")
public class DatabaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Configuración inicial si es necesaria
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Intentar obtener una conexión a la base de datos
        try (Connection connection = DatabaseConnection.getConnection()) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // Asignar la conexión a la solicitud para su uso posterior en el servlet o JSP
            httpRequest.setAttribute("connection", connection);

            // Continuar con la cadena de filtros/servidores
            chain.doFilter(request, response);
        } catch (Exception e) {
            // Manejar excepciones de conexión a la base de datos
            e.printStackTrace();
            throw new ServletException("Error establishing database connection", e);
        }
    }

    @Override
    public void destroy() {
        // Limpieza de recursos si es necesario
    }
}
