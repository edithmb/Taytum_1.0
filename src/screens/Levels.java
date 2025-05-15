package screens;

import javax.swing.*;
import java.awt.*;

public class Levels {
    public JPanel LevelsPanel;

    public Levels() {
        LevelsPanel = new JPanel();
        LevelsPanel.setLayout(null);
        LevelsPanel.setPreferredSize(new Dimension(1050,600));
        LevelsPanel.setBackground(Color.decode("#CDB4D5"));
        showLevels();
    }
     public void showLevels() {
        JButton level1 = new JButton("Level 1");
        level1.setBounds(430,250,200,50);
        LevelsPanel.add(level1);

        JButton level2 = new JButton("Level 2");
        level2.setBounds(430,320,200,50);
        LevelsPanel.add(level2);

        JButton level3 = new JButton("Level 3");
        level3.setBounds(430,390,200,50);
        LevelsPanel.add(level3);

     }

}
