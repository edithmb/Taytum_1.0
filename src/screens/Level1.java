package screens;

import utils.FallingObject;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class Level1 {
    public JPanel Level1Panel;
    private int collectorX =350;
    private int collectorY =330;
    private List<FallingObject> falling= new ArrayList<>();

    public Level1(JFrame frame) {

        Level1Panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawImage(g,this,"src/resources/cespedd.png",0.5f,0,300,1050,264);
                Utils.drawCollector(g,this,"src/resources/hatbrown.png",collectorX,collectorY,350,250);

                for (FallingObject f : falling) {
                    f.draw(g,this);
                }
            }
        };
        Level1Panel.setLayout(new BorderLayout());
        Level1Panel.setBackground(Color.decode("#EDFDEE"));
        Level1Panel.setFocusable(true);
        SwingUtilities.invokeLater(() -> Level1Panel.requestFocusInWindow());


        //barra superior
        JPanel paneltop = new JPanel(new BorderLayout());
        paneltop.setBackground(Color.decode("#88527F"));
        paneltop.setPreferredSize(new Dimension(0,60));

        //bton volver
        JButton homeButton = Utils.createHomeButton(null,"Back Home",30,30);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        paneltop.add(homeButton,BorderLayout.WEST);

        //titulo

        JLabel levellabel = new JLabel("Debut level");
        levellabel.setFont(Utils.getQuicksand(30f));
        paneltop.add(levellabel,BorderLayout.EAST);

        Level1Panel.add(paneltop,BorderLayout.NORTH);

        Level1Panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int panelWidth = Level1Panel.getWidth();
                int collectorWidth= 350;

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    collectorX +=15;
                }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    collectorX -=15;
                }
                if(collectorX <0) collectorX=0;
                if(collectorX > panelWidth - collectorWidth) collectorX=panelWidth-collectorWidth;
                Level1Panel.repaint();
            }
        });



        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartScreen start = new StartScreen(frame);
                frame.setContentPane(start.MenuPrincipal);
                frame.validate();
                frame.repaint();
            }
        });

        // Timer para actualizar objetos
        Timer gameTimer = new Timer(30, e -> {
            for (FallingObject obj : falling) {
                obj.update();
            }
            checkCollisions();
            Level1Panel.repaint();
        });
        gameTimer.start();

        // Timer para crear nuevos objetos
        Timer spawnTimer = new Timer(2000, e -> {
            int x = (int) (Math.random() * (Level1Panel.getWidth() - 50));
            falling.add(new FallingObject(x, 0, 50, 50, 5, "src/resources/verde.png"));
        });
        spawnTimer.start();
    }

    private void checkCollisions() {
        Rectangle collectorBounds = new Rectangle(collectorX, collectorY, 350, 250);
        falling.removeIf(obj -> obj.getBounds().intersects(collectorBounds));
    }

}

