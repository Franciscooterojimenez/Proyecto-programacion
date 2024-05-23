/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Modelo.LibroDAO;
import Vista.LibroVista;
import Controlador.LibroControlador;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        try {
            LibroVista vista = new LibroVista();
            LibroDAO modelo = new LibroDAO("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            new LibroControlador(vista, modelo);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}