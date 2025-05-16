package screens;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Level1 {
    public JPanel Level1Panel;
    private int collectorX =350;
    private int collectorY =330;

    public Level1() {

        Level1Panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawImage(g,this,"src/resources/cespedd.png",0.5f,0,300,1050,264);
                Utils.drawCollector(g,this,"src/resources/hatbrown.png",collectorX,collectorY,350,250);

            }
        };
        Level1Panel.setLayout(null);
        Level1Panel.setBackground(Color.decode("#EDFDEE"));
        Level1Panel.setFocusable(true);
        SwingUtilities.invokeLater(() -> Level1Panel.requestFocusInWindow());
        Level1Panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int panelWidth = Level1Panel.getWidth();
                int collectorWidth= 350;

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    collectorX +=5;
                }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    collectorX -=5;
                }
                if(collectorX <0) collectorX=0;
                if(collectorX > panelWidth - collectorWidth) collectorX=panelWidth-collectorWidth;
                Level1Panel.repaint();
            }
        });

    }

}
