package Controllers;

import dao.ConsultaDAO;
import dao.DetallesDAO;
import dao.RepresentanteLegalDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;
import java.util.Properties;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;


@WebServlet("/consultar")
public class SriServlet extends HttpServlet {
    private String authToken;
    private String cookieValues;

    @Override
    public void init() throws ServletException {
        // Cargar las propiedades desde el archivo config.properties
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new ServletException("Archivo config.properties no encontrado.");
            }
            properties.load(input);
            authToken = properties.getProperty("auth.token");
            cookieValues = properties.getProperty("cookie.values");
        } catch (IOException e) {
            throw new ServletException("Error al cargar el archivo config.properties", e);
        }
    }

    // Método GET: Obtener datos desde la API externa y devolver al cliente
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ruc = request.getParameter("ruc");

        if (ruc == null || ruc.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Se requiere el parámetro RUC\"}");
            return;
        }

        try {
            // Configurar la conexión HTTP al servicio externo
            URL url = new URL("https://srienlinea.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/obtenerPorNumerosRuc?ruc=" + ruc);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", authToken);
            connection.setRequestProperty("Cookie", cookieValues);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                // Leer la respuesta del servicio externo
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder jsonResponse = new StringBuilder();
                while (scanner.hasNextLine()) {
                    jsonResponse.append(scanner.nextLine());
                }
                scanner.close();

                // Devolver los datos obtenidos de la API como respuesta
                response.setContentType("application/json");
                response.getWriter().write(jsonResponse.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Error al consultar el SRI\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Error al procesar la solicitud\"}");
        }
    }

    // Método POST: Recibir los datos y guardarlos en la base de datos
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recoger los parámetros del formulario
            String rucConsulta = request.getParameter("numeroRuc");
            String razonSocial = request.getParameter("razonSocial");
            String estadoContribuyenteRuc = request.getParameter("estadoContribuyenteRuc");
            String actividadEconomicaPrincipal = request.getParameter("actividadEconomicaPrincipal");
            String tipoContribuyente = request.getParameter("tipoContribuyente");
            String regimen = request.getParameter("regimen");
            String categoria = request.getParameter("categoria");
            String obligadoLlevarContabilidad = request.getParameter("obligadoLlevarContabilidad");
            String agenteRetencion = request.getParameter("agenteRetencion");
            String contribuyenteEspecial = request.getParameter("contribuyenteEspecial");
            String contribuyenteFantasma = request.getParameter("contribuyenteFantasma");
            String transaccionesInexistente = request.getParameter("transaccionesInexistente");
            String motivoCancelacionSuspension = request.getParameter("motivoCancelacionSuspension");

            // Fechas de la consulta
            String fechaInicioActividadesStr = request.getParameter("fechaInicioActividades");
            String fechaCeseStr = request.getParameter("fechaCese");
            String fechaReinicioActividadesStr = request.getParameter("fechaReinicioActividades");
            String fechaActualizacionStr = request.getParameter("fechaActualizacion");

            // Parsear fechas
            java.sql.Date fechaInicioActividades = java.sql.Date.valueOf(fechaInicioActividadesStr);
            java.sql.Date fechaCese = java.sql.Date.valueOf(fechaCeseStr);
            java.sql.Date fechaReinicioActividades = java.sql.Date.valueOf(fechaReinicioActividadesStr);
            java.sql.Date fechaActualizacion = java.sql.Date.valueOf(fechaActualizacionStr);

            // Crear objetos para DAOs
            ConsultaDAO consultaDAO = new ConsultaDAO();
            int consultaGuardada = consultaDAO.guardarConsulta(rucConsulta, fechaActualizacion, java.sql.Time.valueOf("12:00:00"));

            if (consultaGuardada != -1) {
                // Obtener el ID de la consulta insertada
                int idConsulta = consultaDAO.getLastGeneratedId();

                // Guardar los detalles
                DetallesDAO detallesDAO = new DetallesDAO();
                detallesDAO.guardarDetalle(
                        idConsulta, rucConsulta, razonSocial, estadoContribuyenteRuc,
                        actividadEconomicaPrincipal, tipoContribuyente, regimen, categoria,
                        obligadoLlevarContabilidad, agenteRetencion, contribuyenteEspecial,
                        fechaInicioActividades, fechaCese, fechaReinicioActividades, fechaActualizacion,
                        contribuyenteFantasma, transaccionesInexistente, motivoCancelacionSuspension
                );

                // Guardar los representantes legales (si los hay)
                String[] nombresRepresentantes = request.getParameterValues("nombreRepresentante");
                String[] identificacionesRepresentantes = request.getParameterValues("identificacionRepresentante");

                RepresentanteLegalDAO representanteLegalDAO = new RepresentanteLegalDAO();
                for (int i = 0; i < nombresRepresentantes.length; i++) {
                    String nombre = nombresRepresentantes[i];
                    String identificacion = identificacionesRepresentantes[i];
                    representanteLegalDAO.guardarRepresentanteLegal(idConsulta, nombre, identificacion);
                }

                // Enviar respuesta exitosa
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": \"Datos guardados correctamente\"}");
            } else {
                // En caso de error al guardar la consulta
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Error al guardar los datos de la consulta\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Error al procesar la solicitud\"}");
        }
    }

}
