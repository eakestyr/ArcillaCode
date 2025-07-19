package eak.arcilla;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import eak.arcilla.menubar.MenubarFrame;
import eak.arcilla.editor.EditorPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * @version v1.1
 * @author João L.
 */
public class MainFrame extends JFrame {
    
    private final JLabel estadoBarra = new JLabel("Listo");
    
    public MainFrame() {
        // Configuración básica de la ventana
        super("Arcilla Code");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Inicialización de componentes
        EditorPanel editor = new EditorPanel();
        MenubarFrame menubar = new MenubarFrame(editor, estadoBarra);

        // Configuración inicial del editor
        editor.toggleLineNumbers(false);

        // Configuración del layout y componentes visuales
        setJMenuBar(menubar);
        add(editor, BorderLayout.CENTER);
        add(estadoBarra, BorderLayout.SOUTH);
    }
}
