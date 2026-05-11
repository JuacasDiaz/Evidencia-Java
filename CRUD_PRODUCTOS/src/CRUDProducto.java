import java.sql.*;
import java.util.Scanner;

public class CRUDProducto {
    // Cambia estos datos si tu MySQL tiene contraseña
    static final String URL = "jdbc:mysql://localhost:3306/tienda";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int op;
            do {
                System.out.println("\n1. Crear\n2. Listar\n3. Actualizar\n4. Eliminar\n5. Salir");
                System.out.print("Opción: ");
                op = sc.nextInt(); sc.nextLine();
                switch (op) {
                    case 1 -> crear(sc);
                    case 2 -> listar();
                    case 3 -> actualizar(sc);
                    case 4 -> eliminar(sc);
                    case 5 -> System.out.println("Adiós");
                    default -> System.out.println("Inválida");
                }
            } while (op != 5);
        } catch (SQLException e) {
            System.err.println("Error BD: " + e.getMessage());
        }
    }

    static void crear(Scanner sc) throws SQLException {
        System.out.print("Nombre: ");
        String nom = sc.nextLine();
        System.out.print("Precio: ");
        double pre = sc.nextDouble(); sc.nextLine();
        String sql = "INSERT INTO productos (nombre, precio) VALUES (?, ?)";
        try (Connection c = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nom);
            ps.setDouble(2, pre);
            ps.executeUpdate();
            System.out.println("Creado.");
        }
    }

    static void listar() throws SQLException {
        String sql = "SELECT * FROM productos";
        try (Connection c = DriverManager.getConnection(URL, USER, PASS);
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\nID | Nombre | Precio");
            while (rs.next()) {
                System.out.printf("%d | %s | %.2f\n", rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
            }
        }
    }

    static void actualizar(Scanner sc) throws SQLException {
        System.out.print("ID a modificar: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nom = sc.nextLine();
        System.out.print("Nuevo precio: ");
        double pre = sc.nextDouble(); sc.nextLine();
        String sql = "UPDATE productos SET nombre=?, precio=? WHERE id=?";
        try (Connection c = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nom);
            ps.setDouble(2, pre);
            ps.setInt(3, id);
            if (ps.executeUpdate() > 0) System.out.println("Actualizado.");
            else System.out.println("ID no existe.");
        }
    }

    static void eliminar(Scanner sc) throws SQLException {
        System.out.print("ID a eliminar: ");
        int id = sc.nextInt(); sc.nextLine();
        String sql = "DELETE FROM productos WHERE id=?";
        try (Connection c = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) System.out.println("Eliminado.");
            else System.out.println("ID no existe.");
        }
    }
}