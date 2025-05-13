package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartScreen {
    public JPanel MenuPrincipal;

    public StartScreen() {
        MenuPrincipal = new JPanel();
        MenuPrincipal.setLayout(null);
        MenuPrincipal.setPreferredSize(new Dimension(1050, 600));
        MenuPrincipal.setBackground(Color.decode("#CDB4D5"));
        adds();
    }
    private void adds(){

        // Cargar Fuente
        Font quicksand = null;
        try {
            // Ruta del archivo .ttf
            quicksand = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/Quicksand-VariableFont_wght.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        Font quicksandBig = quicksand.deriveFont(Font.BOLD, 70f);
        Font quicksandSmall = quicksand.deriveFont(40f);


        JLabel fraseInicial = new JLabel("Welcome to your Swiftie Catch Game");
        fraseInicial.setFont(quicksandSmall);
        fraseInicial.setForeground(Color.decode("#4C1E4F"));
        fraseInicial.setBounds((1050-600)/2, 100, 700, 50);
        MenuPrincipal.add(fraseInicial);

        JLabel titulo = new JLabel("Taytumn");
        titulo.setFont(quicksandBig);
        titulo.setForeground(Color.decode("#4C1E4F"));
        titulo.setBounds((1050-300)/2, 150, 500, 150);
        MenuPrincipal.add(titulo);


        JButton Start = new JButton("Start Game");
        Start.setBounds((1050 - 200) / 2, 350, 200, 50);
        MenuPrincipal.add(Start);

    }
}
