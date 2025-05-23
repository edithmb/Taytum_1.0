package screens;

import utils.BotonPersonalizado;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Levels {
    public JPanel LevelsPanel;

    public Levels(JFrame frame) {
        LevelsPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawBackground(g, this, "FONDO.png");
            }
        };
        LevelsPanel.setLayout(null);
        LevelsPanel.setBackground(Color.decode("#CDB4D5"));
        showLevels(frame);
    }
     public void showLevels(JFrame frame) {
        BotonPersonalizado level1 = new BotonPersonalizado("Level 1");
        level1.setColorFondo(Color.decode("#4C1E4F"));
        level1.setBordePersonalizado(Color.decode("#CDB4D5"),3,30);
        level1.setFont(Utils.getQuicksand(16f).deriveFont(Font.BOLD));
        level1.setBounds(430,220,230,70);
        LevelsPanel.add(level1);

        BotonPersonalizado level2 = new BotonPersonalizado("Level 2");
        level2.setColorFondo(Color.decode("#4C1E4F"));
        level2.setBordePersonalizado(Color.decode("#CDB4D5"),3,30);
        level2.setFont(Utils.getQuicksand(16f));
        level2.setBounds(430,300,230,70);
        LevelsPanel.add(level2);

        BotonPersonalizado level3 = new BotonPersonalizado("Level 3");
        level3.setColorFondo(Color.decode("#4C1E4F"));
        level3.setBordePersonalizado(Color.decode("#CDB4D5"),3,30);
        level3.setFont(Utils.getQuicksand(16f));
        level3.setBounds(430,380,230,70);
        LevelsPanel.add(level3);

         JButton homeButton = Utils.createHomeButton(frame,"Back",15,15);
         homeButton.setFont(Utils.getQuicksand(16f));
         homeButton.setBackground(Color.decode("#4C1E4F"));
         homeButton.setBounds(0, 0, 100, 40);
         LevelsPanel.add(homeButton);

         level1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Level1 level1 = new Level1(frame);
                 frame.setContentPane(level1.Level1Panel);
                 frame.validate();
                 frame.repaint();


             }
         });

         level2.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Level2 level2 = new Level2();
                 frame.setContentPane(level2.level2panel);
                 frame.validate();
                 frame.repaint();

             }
         });

     }

}
