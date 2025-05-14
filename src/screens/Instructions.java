package screens;

import javax.swing.*;
import java.awt.*;

public class Instructions {
    JPanel InstructionsPanel;
    public Instructions() {
        InstructionsPanel = new JPanel();
        InstructionsPanel.setLayout(null);
        InstructionsPanel.setPreferredSize(new Dimension(1050, 600));
        InstructionsPanel.setBackground(Color.decode("#CDB4D5"));
        createButtons();
    }

    private void createButtons() {
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(425, 350, 200, 50);

        JButton guestButton = new JButton("Play as a guest");
        guestButton.setBounds(440, 350, 200, 50);


    }


}
