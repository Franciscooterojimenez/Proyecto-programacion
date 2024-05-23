/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author Usuario
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.TableModel;

public class LibroVista {
    public JFrame frame;
    private JPanel contentPane;
    private JTable table;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnBuscar;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAnioPublicacion;
    private JTextField txtGenero;
    private JTextField txtPrecio;
    private JTextField txtBuscar;

    public LibroVista() {
        frame = new JFrame("Gestión de Libros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("background.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new GridLayout(2, 1));

        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");
        btnBuscar = new JButton("Buscar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnBuscar);
        panelSur.add(panelBotones);

        JPanel panelForm = new JPanel(new GridLayout(6, 2));
        panelForm.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelForm.add(txtTitulo);

        panelForm.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panelForm.add(txtAutor);

        panelForm.add(new JLabel("Año de Publicación:"));
        txtAnioPublicacion = new JTextField();
        panelForm.add(txtAnioPublicacion);

        panelForm.add(new JLabel("Género:"));
        txtGenero = new JTextField();
        panelForm.add(txtGenero);

        panelForm.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);

        panelForm.add(new JLabel("Buscar (ID):"));
        txtBuscar = new JTextField();
        panelForm.add(txtBuscar);

        panelSur.add(panelForm);

        contentPane.add(panelSur, BorderLayout.SOUTH);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void setTableModel(TableModel tableModel) {
        table.setModel(tableModel);
    }

    public String getTitulo() {
        return txtTitulo.getText();
    }

    public String getAutor() {
        return txtAutor.getText();
    }

    public int getAnioPublicacion() {
        return Integer.parseInt(txtAnioPublicacion.getText());
    }

    public String getGenero() {
        return txtGenero.getText();
    }

    public double getPrecio() {
        return Double.parseDouble(txtPrecio.getText());
    }

    public int getBuscarId() {
        return Integer.parseInt(txtBuscar.getText());
    }

    public int getSelectedLibroId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            return -1;
        }
        Object value = table.getValueAt(selectedRow, 0);
        if (value instanceof Integer) {
            return (int) value;
        } else {
            try {
                return Integer.parseInt(value.toString());
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    public void addAgregarLibroListener(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    public void addEliminarLibroListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    public void addActualizarLibroListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }

    public void addBuscarLibroListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }
}