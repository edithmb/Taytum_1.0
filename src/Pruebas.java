import screens.Instructions;

import javax.swing.*;
import java.awt.*;

public class Pruebas {
    public static void main(String[] args) {

        JFrame frame = null;
        Instructions instructions = new Instructions(frame);
        frame = new JFrame("Pantalla de Instrucciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(instructions.InstructionsPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}