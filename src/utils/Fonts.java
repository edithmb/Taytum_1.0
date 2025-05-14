package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {
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
}
