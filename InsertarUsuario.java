

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;

public class InsertarUsuario {

	public static void insertarUsuario(String nombre, String apellido, String correo, String contraseña, String rol) {
	    String verificar = "SELECT * FROM usuarios_huellitas WHERE correo = ?";
	    String insertar = "INSERT INTO usuarios_huellitas (nombre, apellido, correo, contraseña, rol) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = ConexionBD.conectar();
	         PreparedStatement stmtVerificar = conn.prepareStatement(verificar)) {

	        stmtVerificar.setString(1, correo);
	        ResultSet rs = stmtVerificar.executeQuery();

	        if (rs.next()) {
	            System.out.println("⚠️ Ya existe un usuario con ese correo.");
	            return;
	        }

	        try (PreparedStatement stmtInsertar = conn.prepareStatement(insertar)) {
	            stmtInsertar.setString(1, nombre);
	            stmtInsertar.setString(2, apellido);
	            stmtInsertar.setString(3, correo);
	            stmtInsertar.setString(4, contraseña);
	            stmtInsertar.setString(5, rol);

	            int filas = stmtInsertar.executeUpdate();
	            System.out.println(filas > 0 ? "✅ Usuario insertado correctamente." : "⚠️ No se pudo insertar.");
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error: " + e.getMessage());
	    }
	}


    // Prueba
    public static void main(String[] args) {
        insertarUsuario("Carlos", "Gomez", "daniel_123@huellitas.com", "daniel231", "Empleado");
    }
}
