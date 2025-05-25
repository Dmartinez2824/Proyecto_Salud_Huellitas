import conexion.ConexionBD;

public class TextConexion {
    public static void main(String[] args) {
        if (ConexionBD.conectar() != null) {
            System.out.println("Conexión exitosa.");
        } else {
            System.out.println("No se pudo conectar.");
        }
    }
}
