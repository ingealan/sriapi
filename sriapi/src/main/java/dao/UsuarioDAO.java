package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import utils.DatabaseConnection;

public class UsuarioDAO {

    // Método para agregar un nuevo usuario a la base de datos
    public boolean agregarUsuario(String nombre, String password, String rol) {
        String sql = "INSERT INTO usuarios (nombre, contraseña, rol) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Cifrar la contraseña usando BCrypt
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Establecer los parámetros
            ps.setString(1, nombre);
            ps.setString(2, hashedPassword);
            ps.setString(3, rol);

            // Ejecutar la actualización
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0; // Si se insertó correctamente, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si algo falla, retorna false
    }

    // Método para validar usuario y contraseña
    public boolean validarUsuario(String username, String password) {
        String sql = "SELECT contraseña FROM usuarios WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Obtiene el hash de la contraseña desde la base de datos
                    String hashedPassword = rs.getString("contraseña");

                    // Compara el hash almacenado con la contraseña proporcionada
                    return BCrypt.checkpw(password, hashedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Verifica si el hash de la contraseña es válido según el formato de BCrypt
    private boolean isValidBCryptHash(String hash) {
        // Un hash de BCrypt válido tiene 60 caracteres y comienza con "$2a$", "$2b$", o "$2y$"
        return hash != null && hash.length() == 60 && (hash.startsWith("$2a$") || hash.startsWith("$2b$") || hash.startsWith("$2y$"));
    }

    // Método para actualizar el hash de la contraseña si es necesario
    public void actualizarHashDeContraseña(String username, String nuevaContraseña) {
        String sql = "UPDATE usuarios SET contraseña = ? WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Genera un nuevo hash para la contraseña
            String hashedPassword = BCrypt.hashpw(nuevaContraseña, BCrypt.gensalt());
            ps.setString(1, hashedPassword);
            ps.setString(2, username);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Contraseña actualizada correctamente para el usuario: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener el rol de un usuario, en caso de ser necesario (por ejemplo, para mostrar los datos)
    public String obtenerRolUsuario(String username) {
        String sql = "SELECT rol FROM usuarios WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("rol");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
