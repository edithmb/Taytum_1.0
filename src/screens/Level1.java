package screens;

import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Level1 {
    public JPanel Level1Panel;

    public Level1() {

        Level1Panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawImage(g,this,"src/resources/cespedd.png",0.5f,0,300,1050,264);


            }
        };
        Level1Panel.setLayout(null);
        Level1Panel.setBackground(Color.decode("#EDFDEE"));

    }

}
