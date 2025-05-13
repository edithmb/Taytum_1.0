package Main;

import screens.StartScreen;

import javax.swing.*;
import java.awt.*;

public class Principal {
    private JPanel Principal;

    public Principal() {
        StartScreen start = new StartScreen();
        Principal.setLayout(new BorderLayout());
        Principal.add(start.MenuPrincipal, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Taytumn");
        frame.setContentPane(new Principal().Principal);
        frame.setPreferredSize(new Dimension(1050, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        //Icono
        Toolkit screen = Toolkit.getDefaultToolkit();
        Image image = screen.getImage("src/resources/logotulip.png");
        frame.setIconImage(image);
    }
}
