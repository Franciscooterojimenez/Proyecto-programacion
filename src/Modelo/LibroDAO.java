/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class LibroDAO {
    private Connection conexion;

    public LibroDAO(String url, String usuario, String password) throws SQLException {
        conexion = DriverManager.getConnection(url, usuario, password);
    }
    
    

    public void agregarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO libros (titulo, autor, anio_publicacion, genero, precio) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setInt(3, libro.getAnioPublicacion());
            pstmt.setString(4, libro.getGenero());
            pstmt.setDouble(5, libro.getPrecio());
            pstmt.executeUpdate();
        }
    }

    public void eliminarLibro(int id) throws SQLException {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void actualizarLibro(Libro libro) throws SQLException {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, anio_publicacion = ?, genero = ?, precio = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setInt(3, libro.getAnioPublicacion());
            pstmt.setString(4, libro.getGenero());
            pstmt.setDouble(5, libro.getPrecio());
            pstmt.setInt(6, libro.getId());
            pstmt.executeUpdate();
        }
    }

    public Libro obtenerLibroPorId(int id) throws SQLException {
        String sql = "SELECT * FROM libros WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("genero"),
                        rs.getDouble("precio")
                );
            } else {
                return null;
            }
        }
    }

    public DefaultTableModel obtenerLibrosTableModel() throws SQLException {
        String[] columnNames = {"ID", "Título", "Autor", "Año de Publicación", "Género", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String sql = "SELECT * FROM libros";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("genero"),
                        rs.getDouble("precio")
                };
                model.addRow(row);
            }
        }
        return model;
    }
}