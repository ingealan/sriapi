package dao;

import models.VistaConsultaDetallesModelo;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VistaConsultaDetallesDAO {
    private Connection connection;

    public VistaConsultaDetallesDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();  // Usando la conexión de DatabaseConnection
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<VistaConsultaDetallesModelo> getDetalles() {
        List<VistaConsultaDetallesModelo> detallesList = new ArrayList<>();
        String query = "SELECT * FROM vista_consulta_detalle";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                VistaConsultaDetallesModelo detalles = new VistaConsultaDetallesModelo(
                        rs.getString("numeroRuc"),
                        rs.getString("razonSocial"),
                        rs.getString("estadoContribuyenteRuc"),
                        rs.getString("actividadEconomicaPrincipal"),
                        rs.getString("tipoContribuyente"),
                        rs.getString("regimen"),
                        rs.getString("categoria"),
                        rs.getString("obligadoLlevarContabilidad"),
                        rs.getString("agenteRetencion"),
                        rs.getString("contribuyenteEspecial"),
                        rs.getTimestamp("fechaInicioActividades"),
                        rs.getTimestamp("fechaCese"),
                        rs.getTimestamp("fechaReinicioActividades"),
                        rs.getTimestamp("fechaActualizacion"),
                        rs.getString("contribuyenteFantasma"),
                        rs.getString("transaccionesInexistente"),
                        rs.getString("motivoCancelacionSuspension")
                );
                detallesList.add(detalles);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return detallesList;
    }
}
