package eak.arcilla.menubar;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import eak.arcilla.editor.EditorPanel;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * @version v1.1
 * @author João L.
 */
public class MenubarActions {
    
    public static void handleNuevoArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona la ubicación y el nombre del archivo");

        int result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        try {
            if (file.createNewFile()) {
                JOptionPane.showMessageDialog(null, "¡Archivo creado con éxito!\n(" + file.getAbsolutePath() + ")", "Arcilla Code Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El archivo ya existe.", "Arcilla Code Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear un archivo\n" + ex.getMessage(), "Arcilla Code Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void handleAbrirArchivo(EditorPanel editor, JLabel estadoBarra) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona el archivo de texto");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto", "txt", "md", "css", "html"));

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            editor.setText(content.toString());
            estadoBarra.setText("Abierto: " + file.getAbsolutePath());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo\n" + ex.getMessage(), "Arcilla Code Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void handleGuardarArchivo(EditorPanel editor, File archivoActual, JLabel estadoBarra) {
        if (archivoActual == null) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar como");

            if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
                return;
            }
            
            archivoActual = chooser.getSelectedFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoActual))) {
            writer.write(editor.getText());
            estadoBarra.setText("Archivo guardado: " + archivoActual.getAbsolutePath());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo\n" + ex.getMessage(), "Arcilla Code Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void handleCambiarTamanoFuente(EditorPanel editor) {
        String input = JOptionPane.showInputDialog(null, "Ingrese el tamaño de la fuente:", "Arcilla Code Info", JOptionPane.PLAIN_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            return;
        }

        int nuevoTamano;

        try {
            nuevoTamano = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Arcilla Code Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nuevoTamano < 8 || nuevoTamano > 72) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un tamaño de fuente válido entre 8 y 72.", "Arcilla Code Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        editor.setFontSize(nuevoTamano);
    }
    
    public static void handleCambiarFamiliaFuente(EditorPanel editor) {
        String[] fuentesDisponibles = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        JComboBox<String> comboBox = new JComboBox<>(fuentesDisponibles);
        comboBox.setSelectedItem(editor.getCurrentFontFamily());
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Selecciona una familia de fuentes:"));
        panel.add(comboBox);
        
        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Seleccionar familia de fuentes",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        
        if(opcion == JOptionPane.OK_OPTION) {
            String fuenteSeleccionada = (String) comboBox.getSelectedItem();
            editor.setFontFamily(fuenteSeleccionada);
        }
    }
}
