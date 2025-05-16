package screens;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Levels {
    public JPanel LevelsPanel;

    public Levels(JFrame frame) {
        LevelsPanel = new JPanel();
        LevelsPanel.setLayout(null);
        LevelsPanel.setBackground(Color.decode("#CDB4D5"));
        showLevels(frame);
    }
     public void showLevels(JFrame frame) {
        JButton level1 = new JButton("Level 1");
        level1.setFont(Utils.getQuicksand(16f));
        level1.setBounds(430,250,200,50);
        LevelsPanel.add(level1);

        JButton level2 = new JButton("Level 2");
        level2.setFont(Utils.getQuicksand(16f));
        level2.setBounds(430,320,200,50);
        LevelsPanel.add(level2);

        JButton level3 = new JButton("Level 3");
        level3.setFont(Utils.getQuicksand(16f));
        level3.setBounds(430,390,200,50);
        LevelsPanel.add(level3);

         JButton homeButton = Utils.createHomeButton(frame,"Back",15,15);
         homeButton.setFont(Utils.getQuicksand(16f));
         homeButton.setBounds(10, 10, 100, 40);
         LevelsPanel.add(homeButton);

         level1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Level1 level1 = new Level1();
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
