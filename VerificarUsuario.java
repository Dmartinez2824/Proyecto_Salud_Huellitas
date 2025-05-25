//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import conexion.ConexionBD;
//
//public class VerificarUsuario {
//
//    public static boolean validarLogin(String correo, String contraseña) {
//        String sql = "SELECT * FROM usuarios_huellitas WHERE correo = ? AND contraseña = ?";
//
//        try (Connection conn = ConexionBD.conectar();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, correo);
//            stmt.setString(2, contraseña);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                System.out.println("✅ Usuario válido. Rol: " + rs.getString("rol"));
//                return true;
//            } else {
//                System.out.println("⚠️ Usuario o contraseña incorrectos.");
//                return false;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("❌ Error al validar usuario: " + e.getMessage());
//            return false;
//        }
//    }
//
//    // Prueba
//    public static void main(String[] args) {
//        validarLogin("empleado@huellitas.com", "empleado123");
//    }
//}
