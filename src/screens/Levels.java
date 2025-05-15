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
        LevelsPanel.setPreferredSize(new Dimension(1050,600));
        LevelsPanel.setBackground(Color.decode("#CDB4D5"));
        showLevels(frame);
    }
     public void showLevels(JFrame frame) {
        JButton level1 = new JButton("Level 1");
        level1.setFont(Utils.getQuicksand(14f));
        level1.setBounds(430,250,200,50);
        LevelsPanel.add(level1);

        JButton level2 = new JButton("Level 2");
        level2.setFont(Utils.getQuicksand(14f));
        level2.setBounds(430,320,200,50);
        LevelsPanel.add(level2);

        JButton level3 = new JButton("Level 3");
        level3.setFont(Utils.getQuicksand(14f));
        level3.setBounds(430,390,200,50);
        LevelsPanel.add(level3);

         JButton homeButton = new JButton("Back to Home");
         homeButton.setFont(Utils.getQuicksand(20f));
         homeButton.setBounds(5, 10, 200, 50);
         LevelsPanel.add(homeButton);
         homeButton.addActionListener(Utils.backHome(frame));

         level1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Level1 level1 = new Level1();
                 frame.setContentPane(level1.Level1Panel);
                 frame.validate();
                 frame.repaint();


             }
         });

     }

}
