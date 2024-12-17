package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DatabaseConnection;

public class DetallesDAO {

    // Método para guardar un detalle en la base de datos
    public boolean guardarDetalle(int idConsulta,
                                  String numeroRuc,
                                  String razonSocial,
                                  String estadoContribuyenteRuc,
                                  String actividadEconomicaPrincipal,
                                  String tipoContribuyente,
                                  String regimen,
                                  String categoria,
                                  String obligadoLlevarContabilidad,
                                  String agenteRetencion,
                                  String contribuyenteEspecial,
                                  java.sql.Date fechaInicioActividades, // Puede ser nulo
                                  java.sql.Date fechaCese,             // Puede ser nulo
                                  java.sql.Date fechaReinicioActividades, // Puede ser nulo
                                  java.sql.Date fechaActualizacion,    // Puede ser nulo
                                  String contribuyenteFantasma,
                                  String transaccionesInexistente,
                                  String motivoCancelacionSuspension) {
        String sql = "INSERT INTO detalles (idConsulta, numeroRuc, razonSocial, estadoContribuyenteRuc, " +
                "actividadEconomicaPrincipal, tipoContribuyente, regimen, categoria, " +
                "obligadoLlevarContabilidad, agenteRetencion, contribuyenteEspecial, " +
                "fechaInicioActividades, fechaCese, fechaReinicioActividades, fechaActualizacion, " +
                "contribuyenteFantasma, transaccionesInexistente, motivoCancelacionSuspension) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idConsulta);
            ps.setString(2, numeroRuc);
            ps.setString(3, razonSocial);
            ps.setString(4, estadoContribuyenteRuc);
            ps.setString(5, actividadEconomicaPrincipal);
            ps.setString(6, tipoContribuyente);
            ps.setString(7, regimen);
            ps.setString(8, categoria);
            ps.setString(9, obligadoLlevarContabilidad);
            ps.setString(10, agenteRetencion);
            ps.setString(11, contribuyenteEspecial);
            ps.setDate(12, fechaInicioActividades);
            ps.setDate(13, fechaCese);
            ps.setDate(14, fechaReinicioActividades);
            ps.setDate(15, fechaActualizacion);
            ps.setString(16, contribuyenteFantasma);
            ps.setString(17, transaccionesInexistente);
            ps.setString(18, motivoCancelacionSuspension);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
