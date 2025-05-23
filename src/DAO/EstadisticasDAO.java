package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

import Connection.DBConnection;

public class EstadisticasDAO {
    public static TreeMap<String, Integer> obtenerConteoReproducciones() {
        TreeMap<String, Integer> conteo = new TreeMap<>();
        String query = """
            SELECT nombre AS usuario, COUNT(*) AS veces_reproducido
            FROM reproducciones
            JOIN usuarios ON id_usuario = usuarios.id
            GROUP BY usuario
            ORDER BY usuario;
        """;

        try (Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String usuario = rs.getString("usuario");
                int veces = rs.getInt("veces_reproducido");
                conteo.put(usuario, veces);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener estadísticas: " + e.getMessage());
        }

        return conteo;
        }
    }
