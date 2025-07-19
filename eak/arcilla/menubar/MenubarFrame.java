package eak.arcilla.menubar;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import eak.arcilla.editor.EditorPanel;
import static eak.arcilla.menubar.MenubarActions.handleNuevoArchivo;
import static eak.arcilla.menubar.MenubarActions.handleAbrirArchivo;
import static eak.arcilla.menubar.MenubarActions.handleGuardarArchivo;
import static eak.arcilla.menubar.MenubarActions.handleCambiarTamanoFuente;
import static eak.arcilla.menubar.MenubarActions.handleCambiarFamiliaFuente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
/**
 *
 * @version v1.1
 * @author João L.
 */
public class MenubarFrame extends  JMenuBar {
    
    private File archivoActual = null;
    
    public MenubarFrame(EditorPanel editor, JLabel estadoBarra) {
        
        JMenu archivoMenu = new JMenu("Archivo");
        JMenuItem archivoNuevoItem = new JMenuItem("Nuevo");
        JMenuItem archivoAbrirItem = new JMenuItem("Abrir");
        JMenuItem archivoGuardarItem = new JMenuItem("Guardar");
        JMenuItem archivoSalirItem = new JMenuItem("Salir");
        
        // MENU SECTION: ACTION LISTENER----------------------------------------
        archivoSalirItem.addActionListener((ActionEvent e) -> System.exit(0));
        archivoSalirItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        
        archivoNuevoItem.addActionListener((ActionEvent e) -> handleNuevoArchivo());
        archivoNuevoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        
        archivoAbrirItem.addActionListener((ActionEvent e) -> handleAbrirArchivo(editor, estadoBarra));
        archivoAbrirItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        
        archivoGuardarItem.addActionListener((ActionEvent e) -> handleGuardarArchivo(editor, archivoActual, estadoBarra));
        archivoGuardarItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        
        archivoMenu.add(archivoNuevoItem);
        archivoMenu.add(archivoAbrirItem);
        archivoMenu.add(archivoGuardarItem);
        archivoMenu.add(archivoSalirItem);
        //----------------------------------------------------------------------
        
        // MENU SECTION: EDITAR-------------------------------------------------
        JMenu preferenciasMenu = new JMenu("Preferencias");
        JCheckBoxMenuItem preferenciasVerLineasItem = new JCheckBoxMenuItem("Mostrar Números de Línea");
        JMenuItem preferenciasFontSizeItem = new JMenuItem("Cambiar Tamaño de Fuente");
        JMenuItem preferenciasFontFamilyItem = new JMenuItem("Cambiar Familia de Fuente");
        
        
        preferenciasFontSizeItem.addActionListener((ActionEvent e) -> handleCambiarTamanoFuente(editor));
        preferenciasFontSizeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        
        preferenciasFontFamilyItem.addActionListener((ActionEvent e) -> handleCambiarFamiliaFuente(editor));
        preferenciasFontFamilyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
        
        preferenciasVerLineasItem.addActionListener((ActionEvent e) -> editor.toggleLineNumbers(preferenciasVerLineasItem.getState()));
        preferenciasVerLineasItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        
        preferenciasMenu.add(preferenciasVerLineasItem);
        preferenciasMenu.add(preferenciasFontSizeItem);
        preferenciasMenu.add(preferenciasFontFamilyItem);
        
        //----------------------------------------------------------------------
        
        // MENU SECTION: AYUDA--------------------------------------------------
        JMenu sobreMenu = new JMenu("Ayuda");
        JMenuItem sobreInfoItem = new JMenuItem("Acerca de");
        sobreInfoItem.addActionListener((ActionEvent e) -> 
                JOptionPane.showMessageDialog
                    (null, "Editor de texto/código simplista\nCopyright © 2025 João L.", "About Arcilla", JOptionPane.INFORMATION_MESSAGE));
        
        sobreMenu.add(sobreInfoItem);
        //----------------------------------------------------------------------
        
        add(archivoMenu);
        add(preferenciasMenu);
        add(sobreMenu);
    }
}