package Main;

import screens.Instructions;
import screens.StartScreen;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Principal {
    private JPanel Principal;

    public Principal(JFrame frame) {
        StartScreen start = new StartScreen(frame);
        Principal.setLayout(new BorderLayout());
        Principal.add(start.MenuPrincipal, BorderLayout.CENTER);

    }

    public static void main(String[] args) {

        designMessages();
        JFrame frame = new JFrame("Taytumn");
        frame.setContentPane(new Principal(frame).Principal);
        frame.setPreferredSize(new Dimension(1050, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //Icono
        Toolkit screen = Toolkit.getDefaultToolkit();
        Image image = screen.getImage("src/resources/logotulip.png");
        frame.setIconImage(image);
    }
    private static void designMessages(){
        // mensajes
        UIManager.put("OptionPane.messageFont", Utils.getQuicksand(14f).deriveFont(Font.BOLD));
        UIManager.put("OptionPane.buttonFont", Utils.getQuicksand(14f));
        UIManager.put("Panel.background", Color.decode("#88527F")); // Color del fondo del cuadro de diálogo
        UIManager.put("OptionPane.background", Color.decode("#88527F"));//Color del fondo del mensaje
        UIManager.put("OptionPane.messageForeground", Color.decode("#CDB4D5"));//Color del texto del mensaje
        UIManager.put("Button.foreground", Color.decode("#CDB4D5"));//Color del texto del botton
    }
}
