package screens;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Level2 {
    public JPanel level2panel;
    private int collectorX=350;
    private int collectorY=330;

    public Level2() {
        level2panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawImage(g,this,"src/resources/suelo.png",0.5f,0,300,1050,264);
                Utils.drawCollector(g,this,"src/resources/canasta.png",collectorX,collectorY,350,250);

            }
        };
        level2panel.setLayout(null);
        level2panel.setBackground(Color.decode("#DBF5FA"));
        level2panel.setFocusable(true);
        SwingUtilities.invokeLater(() -> level2panel.requestFocusInWindow());
       level2panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int panelWidth=level2panel.getWidth();
                int collectorWidth=350;

                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    collectorX+=5;
                } else if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    collectorX-=5;
                }

                if(collectorX<0)collectorX=0;
                if(collectorX> panelWidth-collectorWidth)collectorX=panelWidth-collectorWidth;
                level2panel.repaint();

            }
        });




    }
}
