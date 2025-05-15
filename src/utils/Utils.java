package utils;

import screens.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Utils {
    private static Font Quicksand;


    static {
        try {
            // Carga la fuente una sola vez
            Quicksand = Font.createFont(Font.TRUETYPE_FONT, new File("src/utils/Quicksand-VariableFont_wght.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Quicksand);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error al cargar la fuente: " + e.getMessage());
            Quicksand = new Font("SansSerif", Font.PLAIN, 12); // fuente de respaldo
        }
    }
    public static Font getQuicksand(float size) {
        return Quicksand.deriveFont(size);
    }

    public static ActionListener backHome (JFrame frame){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartScreen start = new StartScreen(frame);
                frame.setContentPane(start.MenuPrincipal);
                frame.validate();
                frame.repaint();
            }
        };

    }


    public static void drawImage(Graphics g, JComponent component, String imagePath, float opacity, int x, int y, int width, int height){
        ImageIcon image = new ImageIcon(imagePath);
        Image img = image.getImage();

        Graphics2D graphic = (Graphics2D) g.create();
        graphic.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        graphic.drawImage(img,x,y,width,height,component);
        graphic.dispose();
    }





}
