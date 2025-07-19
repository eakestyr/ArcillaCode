package eak.arcilla.editor;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import javax.swing.text.Element;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
/**
 * @version v1.1
 * @author João L.
 */
public class EditorLineNumber extends JComponent {
    private final JTextArea textArea;
    private final int MARGIN = 5;

    public EditorLineNumber(JTextArea textArea) {
        this.textArea = textArea;
        this.textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { repaint(); }
            @Override
            public void removeUpdate(DocumentEvent e)   { repaint(); }
            @Override
            public void changedUpdate(DocumentEvent e)  { repaint(); }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        int lines = textArea.getLineCount();
        int maxDigits = String.valueOf(lines).length();
        FontMetrics fm = getFontMetrics(textArea.getFont());
        return new Dimension(MARGIN * 2 + fm.charWidth('0') * maxDigits, textArea.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Font font = textArea.getFont();
        FontMetrics fontMetrics = textArea.getFontMetrics(font);
        Element root = textArea.getDocument().getDefaultRootElement();

        int y = 0;
        for (int i = 0; i < root.getElementCount(); i++) {
            Element lineElement = root.getElement(i);
            try {
                Rectangle lineBounds = textArea.modelToView2D(lineElement.getStartOffset()).getBounds();
                y = lineBounds.y + lineBounds.height - fontMetrics.getDescent();
            } catch (BadLocationException e) {
                continue;
            }

            g.drawString(Integer.toString(i + 1), MARGIN, y);
        }
    }
}