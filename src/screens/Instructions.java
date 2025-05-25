package screens;

import utils.BotonPersonalizado;
import utils.Utils;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instructions {
    public JPanel InstructionsPanel;

    public Instructions(JFrame frame) {
        InstructionsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawBackground(g, this, "FONDO.png");
            }
        };
        InstructionsPanel.setLayout(null);
        showInstructions();
        createButtons(frame);
    }

    private void showInstructions() {
        JPanel instructionsBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(136, 82, 127, 153));// Blanco translúcido
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Esquinas redondeadas
            }
        };

        instructionsBox.setLayout(new BorderLayout());
        instructionsBox.setBounds(220, 60, 580, 220);
        instructionsBox.setOpaque(false);

        JTextPane instructionsText = new JTextPane();
        instructionsText.setText(
                "INSTRUCTIONS\n\n" +
                        "Catch the right objects to earn a point.\n" +
                        "Catch the wrong ones and you’ll lose two.\n" +
                        "If your score drops below 0, the game is over."
        );
        instructionsText.setForeground(Color.decode("#4C1E4F"));
        instructionsText.setFont(Utils.getQuicksand(22f).deriveFont(Font.BOLD));
        instructionsText.setEditable(false);
        instructionsText.setOpaque(false);
        instructionsText.setFocusable(false);
        instructionsText.setMargin(new Insets(20, 20, 20, 20));

        StyledDocument doc = instructionsText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        instructionsBox.add(instructionsText, BorderLayout.CENTER);
        InstructionsPanel.add(instructionsBox);
    }

    private void createButtons(JFrame frame) {

        BotonPersonalizado signIn = new BotonPersonalizado("Sign in");
        signIn.setColorFondo(Color.decode("#4C1E4F"));
        signIn.setBordePersonalizado(Color.decode("#CDB4D5"), 3, 30);
        signIn.setFont(Utils.getQuicksand(20f));
        signIn.setBounds(300, 350, 200, 50);
        InstructionsPanel.add(signIn);

        BotonPersonalizado signUp = new BotonPersonalizado("Sign up");
        signUp.setColorFondo(Color.decode("#4C1E4F"));
        signUp.setBordePersonalizado(Color.decode("#CDB4D5"), 3, 30);
        signUp.setFont(Utils.getQuicksand(20f));
        signUp.setBounds(520, 350, 200, 50);
        InstructionsPanel.add(signUp);

        BotonPersonalizado guest = new BotonPersonalizado("Play as Guest");
        guest.setColorFondo(Color.decode("#4C1E4F"));
        guest.setBordePersonalizado(Color.decode("#CDB4D5"), 3, 30);
        guest.setFont(Utils.getQuicksand(20f));
        guest.setBounds(400, 430, 200, 50);
        InstructionsPanel.add(guest);

        JButton homeButton = Utils.createHomeButton(frame, "Back", 15, 15);
        homeButton.setBackground(Color.decode("#4C1E4F"));
        homeButton.setFont(Utils.getQuicksand(16f));
        homeButton.setBounds(0, 0, 100, 40);
        InstructionsPanel.add(homeButton);

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn(frame);
                frame.setContentPane(signIn.signInPanel);
                frame.validate();
                frame.repaint();
            }
        });

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signUp = new SignUp(frame);
                frame.setContentPane(signUp.SignUpPanel);
                frame.validate();
                frame.repaint();
            }
        });

        guest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showOptionDialog(
                        frame,
                        "Play as guest? No registration means no leaderboard score!",
                        "Register",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Play", "Cancel"},
                        "Cancel"
                );

                if (option == JOptionPane.YES_OPTION) {
                    Levels levels = new Levels(frame);
                    frame.setContentPane(levels.LevelsPanel);
                    frame.validate();
                    frame.repaint();
                }
            }
        });
    }
}

