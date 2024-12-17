package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseConnection;

public class ConsultaDAO {
    private int lastGeneratedId;
    // Método para guardar una consulta en la base de datos y obtener el id generado
    public int guardarConsulta(String ruc, java.sql.Date fecha, java.sql.Time hora) {
        String sql = "INSERT INTO consulta (ruc, fecha, hora) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, ruc);
            ps.setDate(2, fecha);
            ps.setTime(3, hora);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // Obtener el ID generado automáticamente
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        lastGeneratedId = rs.getInt(1);
                        return rs.getInt(1);  // Retorna el id generado
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Retorna un valor negativo si hubo un error
    }
    public int getLastGeneratedId() {
        return lastGeneratedId;  // Retorna el ID generado
    }

    // Otros métodos como buscar, actualizar, eliminar
}
