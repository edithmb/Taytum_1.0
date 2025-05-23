package utils;

import javax.swing.*;
import java.awt.*;

public class BotonPersonalizado extends JButton {

    private Color colorFondo = new Color(100, 100, 255);
    private Color colorBorde = Color.BLACK;
    private int grosorBorde = 2;
    private int radio = 20;

    public BotonPersonalizado(String texto) {
        super(texto);
        setFont(new Font("Quicksand", Font.BOLD, 18));
        setFocusPainted(false);
        setContentAreaFilled(false); // Desactiva el fondo por defecto
        setBorderPainted(false);     // Elimina el borde por defecto
        setOpaque(false);
        setForeground(Color.WHITE);  // Color del texto
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void setColorFondo(Color color) {
        this.colorFondo = color;
        repaint();
    }

    public void setBordePersonalizado(Color color, int grosor, int radio) {
        this.colorBorde = color;
        this.grosorBorde = grosor;
        this.radio = radio;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo redondeado
        g2.setColor(colorFondo);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);

        // Borde redondeado
        g2.setStroke(new BasicStroke(grosorBorde));
        g2.setColor(colorBorde);
        g2.drawRoundRect(grosorBorde / 2, grosorBorde / 2,
                getWidth() - grosorBorde, getHeight() - grosorBorde,
                radio, radio);

        // Texto centrado
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
        g2.setColor(getForeground());
        g2.setFont(getFont());
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}


