package screens;
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
        MenuPrincipal = new JPanel();
        MenuPrincipal.setLayout(null);
        MenuPrincipal.setPreferredSize(new Dimension(1050, 600));
        MenuPrincipal.setBackground(Color.decode("#CDB4D5"));


        addButtons(frame);
    }
    private void addButtons(JFrame frame) {

        JLabel fraseInicial = new JLabel("Welcome to your Swiftie Catch Game");
        fraseInicial.setFont(Utils.getQuicksand(40f));
        fraseInicial.setForeground(Color.decode("#4C1E4F"));
        fraseInicial.setBounds(200, 100, 700, 50);
        MenuPrincipal.add(fraseInicial);

        JLabel titulo = new JLabel("Taytumn");
        titulo.setFont(Utils.getQuicksand(70f));
        titulo.setForeground(Color.decode("#4C1E4F"));
        titulo.setBounds((1050-300)/2, 150, 500, 150);
        MenuPrincipal.add(titulo);


        JButton Start = new JButton("Start Game");
        Start.setFocusPainted(false);
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
