package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DatabaseConnection;

public class RepresentanteLegalDAO {

    // Método para guardar un representante legal en la base de datos
    public boolean guardarRepresentanteLegal(int idConsulta, String nombre, String identificacion) {
        String sql = "INSERT INTO representantes_legales (idDetalle,identificacion, nombre) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idConsulta);
            ps.setString(2, identificacion);
            ps.setString(3, nombre);


            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Otros métodos como buscar, actualizar, eliminar
}
