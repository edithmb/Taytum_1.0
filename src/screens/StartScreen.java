package screens;
import utils.BotonPersonalizado;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartScreen {
    public JPanel MenuPrincipal;

    public StartScreen(JFrame frame) {
        MenuPrincipal = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawBackground(g, this, "FONDO.png");
            }

        };
        MenuPrincipal.setLayout(null);
        addButtons(frame);
    }
    private void addButtons(JFrame frame) {

        JLabel fraseInicial = new JLabel("Welcome to your Swiftie Catch Game");
        fraseInicial.setFont(Utils.getQuicksand(40f).deriveFont(Font.BOLD));
        fraseInicial.setForeground(Color.decode("#4C1E4F"));
        fraseInicial.setBounds(200, 100, 800, 50);
        MenuPrincipal.add(fraseInicial);

        JLabel titulo = new JLabel("Taytumn");
        titulo.setFont(Utils.getQuicksand(70f).deriveFont(Font.BOLD));
        titulo.setForeground(Color.decode("#4C1E4F"));
        titulo.setBounds((1050-300)/2, 150, 500, 150);
        MenuPrincipal.add(titulo);

        BotonPersonalizado Start = new BotonPersonalizado("Start Game");
        Start.setColorFondo(Color.decode("#4C1E4F"));
        Start.setBordePersonalizado(Color.decode("#CDB4D5"), 3, 35);
        Start.setFont(Utils.getQuicksand(20f));
        Start.setBounds((1050 - 200) / 2, 350, 200, 50);
        MenuPrincipal.add(Start);



        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instructions Instructions = new Instructions(frame);
                frame.setContentPane(Instructions.InstructionsPanel);
                frame.validate();
                frame.repaint();


            }
        });

    }
}
