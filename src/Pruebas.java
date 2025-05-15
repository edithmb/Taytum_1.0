import screens.Instructions;
import screens.Levels;
import screens.SignIn;

import javax.swing.*;
import java.awt.*;

public class Pruebas {
    public static void main(String[] args) {

        JFrame frame = null;
       SignIn signIn = new SignIn();
        frame = new JFrame("Pantalla de Instrucciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(signIn.signInPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /*Instructions inst = new Instructions(frame);
        frame = new JFrame("Pantalla de Instrucciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(inst.InstructionsPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/


       /* Instructions inst = new Instructions(frame)
        frame = new JFrame("Pantalla de Instrucciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(inst.InstructionsPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/


        /*Instructions inst = new Instructions(frame)
        frame = new JFrame("Pantalla de Instrucciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(inst.InstructionsPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/
    }
}