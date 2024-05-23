/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.Libro;
import Modelo.LibroDAO;
import Vista.LibroVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LibroControlador {
    private LibroVista vista;
    private LibroDAO modelo;

    public LibroControlador(LibroVista vista, LibroDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.addAgregarLibroListener(new AgregarLibroListener());
        this.vista.addEliminarLibroListener(new EliminarLibroListener());
        this.vista.addActualizarLibroListener(new ActualizarLibroListener());
        this.vista.addBuscarLibroListener(new BuscarLibroListener());
        cargarLibrosEnTabla();
    }

    class AgregarLibroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Libro libro = new Libro(0, vista.getTitulo(), vista.getAutor(), vista.getAnioPublicacion(), vista.getGenero(), vista.getPrecio());
                modelo.agregarLibro(libro);
                cargarLibrosEnTabla();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    class EliminarLibroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int libroId = vista.getSelectedLibroId();
            if (libroId != -1) {
                try {
                    modelo.eliminarLibro(libroId);
                    cargarLibrosEnTabla();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(vista.frame, "Seleccione un libro para eliminar.");
            }
        }
    }

    class ActualizarLibroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int libroId = vista.getSelectedLibroId();
            if (libroId != -1) {
                try {
                    Libro libro = new Libro(libroId, vista.getTitulo(), vista.getAutor(), vista.getAnioPublicacion(), vista.getGenero(), vista.getPrecio());
                    modelo.actualizarLibro(libro);
                    cargarLibrosEnTabla();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(vista.frame, "Seleccione un libro para actualizar.");
            }
        }
    }

    class BuscarLibroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int libroId = vista.getBuscarId();
                Libro libro = modelo.obtenerLibroPorId(libroId);
                if (libro != null) {
                    JOptionPane.showMessageDialog(vista.frame, "Libro encontrado: \n" +
                            "Título: " + libro.getTitulo() + "\n" +
                            "Autor: " + libro.getAutor() + "\n" +
                            "Año de Publicación: " + libro.getAnioPublicacion() + "\n" +
                            "Género: " + libro.getGenero() + "\n" +
                            "Precio: " + libro.getPrecio());
                } else {
                    JOptionPane.showMessageDialog(vista.frame, "Libro no encontrado.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void cargarLibrosEnTabla() {
        try {
            vista.setTableModel(modelo.obtenerLibrosTableModel());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}