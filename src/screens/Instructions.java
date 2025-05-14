package screens;

import utils.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instructions {
    public JPanel InstructionsPanel;
    public Instructions(JFrame frame) {
        InstructionsPanel = new JPanel();
        InstructionsPanel.setLayout(null);
        InstructionsPanel.setPreferredSize(new Dimension(1050, 600));
        InstructionsPanel.setBackground(Color.decode("#CDB4D5"));
        showInstructions();
        createButtons(frame);

    }

    private void showInstructions() {
        JTextArea instructionsText = new JTextArea();
        instructionsText.setText("Instruccionesssssss " +
                "dfjksgsdfghsdfsdffdf");
        instructionsText.setBounds(300, 100, 450, 200);
        instructionsText.setFont(Fonts.getQuicksand(30f));
        instructionsText.setEditable(false);
        instructionsText.setOpaque(false);
        instructionsText.setFocusable(false);


        InstructionsPanel.add(instructionsText);

    }

    private void createButtons(JFrame frame) {
        JButton signIn = new JButton("Sign In");
        signIn.setBounds(300, 350, 200, 50);
        InstructionsPanel.add(signIn);

        JButton signUp = new JButton("Sign Up");
        signUp.setBounds(520, 350, 200, 50);
        InstructionsPanel.add(signUp);

        JButton guest = new JButton("Play as Guest");
        guest.setBounds(400, 450, 200, 50);
        InstructionsPanel.add(guest);

        signIn.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
                frame.setContentPane(signIn.signInPanel);
                frame.validate();
                frame.repaint();

            }
        });

        signUp.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signUp = new SignUp();
                frame.setContentPane(signUp.SignUpPanel);
                frame.validate();
                frame.repaint();
            }
        });

        guest.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });





    }


}
