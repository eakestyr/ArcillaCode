package eak.arcilla.editor;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
/**
 * @version v1.0
 * @author João L.
 */
public class EditorPanel extends JPanel {
    
    private final JTextArea textArea;
    private final JScrollPane scrollPane;
    private EditorLineNumber lineNumberView;

    public EditorPanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void toggleLineNumbers(boolean visible) {
        if (!visible) {
            scrollPane.setRowHeaderView(null);
            return;
        }

        if (lineNumberView == null) {
            lineNumberView = new EditorLineNumber(textArea);
        }

        scrollPane.setRowHeaderView(lineNumberView);
    }

    public String getText() { return textArea.getText(); }

    public void setText(String text) { textArea.setText(text); }

    public void setFontSize(int size) {
        Font currentFont = textArea.getFont();
        textArea.setFont(new Font(currentFont.getName(), currentFont.getStyle(), size));
    }

    public void setFontFamily(String family) {
        Font currentFont = textArea.getFont();
        textArea.setFont(new Font(family, currentFont.getStyle(), currentFont.getSize()));
    }

    public String getCurrentFontFamily() { return textArea.getFont().getFamily(); }

    public JTextArea getTextArea() { return textArea; }
}