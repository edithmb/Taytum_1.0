package screens;

import utils.Utils;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level1 {
    public JPanel Level1Panel;
    private int collectorX = 350;
    private int collectorY = 420;

    private List<Integer> fallingX = new ArrayList<>();
    private List<Integer> fallingY = new ArrayList<>();
    private List<String> fallingImages = new ArrayList<>();
    private List<Boolean> isCorrectList = new ArrayList<>();

    private String[] correctImages = {
            "src/resources/debut.png",
            "src/resources/fearlesstv.png",
            "src/resources/speaknowtv.png",
            "src/resources/redtv.png",
            "src/resources/1989tv.png",
            "src/resources/lover.png",
            "src/resources/folklore.png",
            "src/resources/evermore.png",
            "src/resources/midnights.png",
            "src/resources/ttpd.png",
    };

    private String[] incorrectImages = {
            "src/resources/fearless.png",
            "src/resources/speaknow.png",
            "src/resources/red.png",
            "src/resources/1989.png",
            "src/resources/reputation.png",
    };

    private int score = 0;
    private final int collectorWidth = 150;
    private final int collectorHeight = 100;
    private final int objectWidth = 105;
    private final int objectHeight = 100;

    private Timer gameTimer;
    private Random random = new Random();
    private JLabel scoreLabel;
    private Clip backgroundClip;

    public Level1(JFrame frame) {
        initLevel1Panel();
        JPanel paneltop = createTopPanel(frame);
        Level1Panel.add(paneltop, BorderLayout.NORTH);
        setupKeyListener();
        setupHomeButtonAction(frame, paneltop);
        gameTimer = new Timer(30, e -> gameLoop(frame));
        gameTimer.start();
    }

    private void initLevel1Panel() {
        Level1Panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawImage(g, Level1Panel, "src/resources/cesped.png", 0.8f, 0, 400, 1050, 200);
                Utils.drawCollector(g, Level1Panel, "src/resources/hat.png", collectorX, collectorY, collectorWidth, collectorHeight);
                for (int i = 0; i < fallingX.size(); i++) {
                    String imagePath = fallingImages.get(i);
                    Utils.drawImage(g, Level1Panel, imagePath, 1f, fallingX.get(i), fallingY.get(i), objectWidth, objectHeight);
                }
            }
        };
        Level1Panel.setLayout(new BorderLayout());
        Level1Panel.setBackground(Color.decode("#EDFDEE"));
        Level1Panel.setFocusable(true);
        SwingUtilities.invokeLater(() -> Level1Panel.requestFocusInWindow());
    }

    private JPanel createTopPanel(JFrame frame) {
        JPanel paneltop = new JPanel();
        paneltop.setLayout(new BoxLayout(paneltop, BoxLayout.X_AXIS));
        paneltop.setBackground(Color.decode("#88527F"));
        paneltop.setPreferredSize(new Dimension(0, 60));
        paneltop.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));

        JButton homeButton = Utils.createHomeButton(frame, "Back Home", 30, 30);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        paneltop.add(homeButton);

        paneltop.add(Box.createHorizontalGlue());

        JLabel levellabel = new JLabel("DEBUT LEVEL");
        levellabel.setFont(Utils.getQuicksand(30f).deriveFont(Font.BOLD));
        levellabel.setForeground(Color.decode("#CDB4D5"));
        paneltop.add(levellabel);

        paneltop.add(Box.createHorizontalGlue());

        scoreLabel = new JLabel("Taycoins: 0");
        scoreLabel.setFont(Utils.getQuicksand(24f));
        scoreLabel.setForeground(Color.decode("#CDB4D5"));
        paneltop.add(scoreLabel);

        ImageIcon avatarIcon = new ImageIcon("src/resources/usuario.png");
        Image image = avatarIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel userIconLabel = new JLabel(new ImageIcon(image));
        userIconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        paneltop.add(Box.createRigidArea(new Dimension(10, 0)));
        paneltop.add(userIconLabel);

        return paneltop;
    }

    private void setupKeyListener() {
        Level1Panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int panelWidth = Level1Panel.getWidth();
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    collectorX += 15;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    collectorX -= 15;
                }
                if (collectorX < 0) collectorX = 0;
                if (collectorX > panelWidth - collectorWidth) collectorX = panelWidth - collectorWidth;
                Level1Panel.repaint();
            }
        });
    }

    private void setupHomeButtonAction(JFrame frame, JPanel paneltop) {
        JButton homeButton = (JButton) paneltop.getComponent(0);
        homeButton.addActionListener(e -> {
            StartScreen start = new StartScreen(frame);
            frame.setContentPane(start.MenuPrincipal);
            frame.validate();
            frame.repaint();
        });
    }

    private void gameLoop(JFrame frame) {
        // Asegura caída constante: intenta crear un nuevo objeto cada ciclo
        if (fallingX.size() < 10) { // Limita el número máximo de objetos simultáneos
            int maxTries = 20;
            boolean added = false;
            while (maxTries-- > 0 && !added) {
                int x = random.nextInt(Level1Panel.getWidth() - objectWidth);

                // Evita solapamientos: comprobar si el nuevo objeto se solaparía
                boolean overlaps = false;
                for (int i = 0; i < fallingX.size(); i++) {
                    int otherX = fallingX.get(i);
                    int otherY = fallingY.get(i);
                    if (Math.abs(x - otherX) < objectWidth && otherY < objectHeight + 50) {
                        overlaps = true;
                        break;
                    }
                }

                if (!overlaps) {
                    fallingX.add(x);
                    fallingY.add(0);

                    boolean isCorrect = random.nextBoolean();
                    String selectedImage = isCorrect
                            ? correctImages[random.nextInt(correctImages.length)]
                            : incorrectImages[random.nextInt(incorrectImages.length)];

                    fallingImages.add(selectedImage);
                    isCorrectList.add(isCorrect);
                    added = true;
                }
            }
        }

        // Mueve los objetos
        for (int i = 0; i < fallingY.size(); i++) {
            fallingY.set(i, fallingY.get(i) + 5);
        }

        // Elimina objetos que caen fuera de la pantalla (sin penalizar si no se recogen)
        for (int i = fallingY.size() - 1; i >= 0; i--) {
            if (fallingY.get(i) > Level1Panel.getHeight()) {
                // Solo eliminar, no penalizar
                fallingX.remove(i);
                fallingY.remove(i);
                fallingImages.remove(i);
                isCorrectList.remove(i);
            }
        }

        checkCollisions(frame);
        Level1Panel.repaint();
    }

    private void checkCollisions(JFrame frame) {
        Rectangle collectorBounds = new Rectangle(collectorX, collectorY, collectorWidth, collectorHeight);
        List<Integer> indicesToRemove = new ArrayList<>();

        for (int i = 0; i < fallingX.size(); i++) {
            Rectangle objBounds = new Rectangle(fallingX.get(i), fallingY.get(i), objectWidth, objectHeight);
            if (collectorBounds.intersects(objBounds)) {
                boolean isCorrect = isCorrectList.get(i);
                if (isCorrect) {
                    score += 1;
                } else {
                    score -= 2;
                }
                indicesToRemove.add(i);
            }
        }

        // Elimina los objetos después de procesarlos (en orden inverso)
        for (int i = indicesToRemove.size() - 1; i >= 0; i--) {
            int index = indicesToRemove.get(i);
            fallingX.remove(index);
            fallingY.remove(index);
            fallingImages.remove(index);
            isCorrectList.remove(index);
        }

        scoreLabel.setText("Taycoins: " + score);

        if (score < 0) {
            gameTimer.stop();
            JOptionPane.showMessageDialog(Level1Panel, "Back to the menu loser!!!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Level1Panel);
            Levels levelScreen = new Levels(frame);
            topFrame.setContentPane(levelScreen.LevelsPanel);
            topFrame.revalidate();
            topFrame.repaint();
        }
    }


    public void playBackgroundMusic() {
        try {
            File audioFile = new File("src/resources/Taylor Swift - Daylight (Official Audio)(MP3_128K) (online-audio-converter.com).wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Error al cargar el audio de fondo");
        }
    }
}






