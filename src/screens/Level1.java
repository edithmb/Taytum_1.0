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
    private int collectorY = 330;

    // Listas para las posiciones X e Y de los objetos que caen
    private List<Integer> fallingX = new ArrayList<>();
    private List<Integer> fallingY = new ArrayList<>();

    private int score = 0;
    private final int collectorWidth = 350;
    private final int collectorHeight = 250;
    private final int objectWidth = 50;
    private final int objectHeight = 50;

    private Timer gameTimer;
    private Random random = new Random();
    private JLabel scoreLabel;
    private Clip backgroundClip;

    public Level1(JFrame frame) {
        playBackgroundMusic();

        Level1Panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawImage(g, Level1Panel, "src/resources/cespedd.png", 0.5f, 0, 300, 1050, 264);
                Utils.drawCollector(g, Level1Panel, "src/resources/hatbrown.png", collectorX, collectorY, collectorWidth, collectorHeight);

                // Dibuja los objetos que caen
                for (int i = 0; i < fallingX.size(); i++) {
                    Utils.drawImage(g, Level1Panel, "src/resources/verde.png", 0.5f, fallingX.get(i), fallingY.get(i), objectWidth, objectHeight);
                }

            }
        };
        Level1Panel.setLayout(new BorderLayout());
        Level1Panel.setBackground(Color.decode("#EDFDEE"));
        Level1Panel.setFocusable(true);
        SwingUtilities.invokeLater(() -> Level1Panel.requestFocusInWindow());

        // Barra superior
        JPanel paneltop = new JPanel(new BorderLayout());
        paneltop.setBackground(Color.decode("#88527F"));
        paneltop.setPreferredSize(new Dimension(0, 60));

        // Botón volver
        JButton homeButton = Utils.createHomeButton(frame, "Back Home", 30, 30);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        paneltop.add(homeButton, BorderLayout.WEST);

        // Título
        JLabel levellabel = new JLabel("Debut level");
        levellabel.setFont(Utils.getQuicksand(25f));
        paneltop.add(levellabel, BorderLayout.CENTER);

        scoreLabel = new JLabel("Puntuación: 0");
        scoreLabel.setFont(Utils.getQuicksand(24f));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        paneltop.add(scoreLabel, BorderLayout.NORTH);

        Level1Panel.add(paneltop, BorderLayout.NORTH);


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

        homeButton.addActionListener(e -> {
            StartScreen start = new StartScreen(frame);
            frame.setContentPane(start.MenuPrincipal);
            frame.validate();
            frame.repaint();
        });

        // Timer para el juego (caída de objetos + repaint + colisiones)
        gameTimer = new Timer(30, e -> gameLoop());
        gameTimer.start();
    }

    private void gameLoop() {
        // Añadir un nuevo objeto cayendo cada cierto tiempo (ejemplo cada 20 ticks aprox)
        if (random.nextInt(40) == 0) {
            int x = random.nextInt(Level1Panel.getWidth() - objectWidth);
            fallingX.add(x);
            fallingY.add(0);
        }

        // Mover objetos hacia abajo
        for (int i = 0; i < fallingY.size(); i++) {
            fallingY.set(i, fallingY.get(i) + 5); // velocidad de caída
        }

        // Eliminar objetos que han caído fuera del panel
        for (int i = fallingY.size() - 1; i >= 0; i--) {
            if (fallingY.get(i) > Level1Panel.getHeight()) {
                fallingX.remove(i);
                fallingY.remove(i);
            }
        }

        // Comprobar colisiones
        checkCollisions();

        // Repintar
        Level1Panel.repaint();
    }

    private void checkCollisions() {
        Rectangle collectorBounds = new Rectangle(collectorX, collectorY, collectorWidth, collectorHeight);
        List<Integer> indicesToRemove = new ArrayList<>();

        for (int i = 0; i < fallingX.size(); i++) {
            Rectangle objBounds = new Rectangle(fallingX.get(i), fallingY.get(i), objectWidth, objectHeight);
            if (collectorBounds.intersects(objBounds)) {
                indicesToRemove.add(i);
                score++;
                scoreLabel.setText("Puntuación: " + score);
            }
        }

        // Remover objetos recogidos, desde el final para no desordenar indices
        for (int i = indicesToRemove.size() - 1; i >= 0; i--) {
            int index = indicesToRemove.get(i);
            fallingX.remove(index);
            fallingY.remove(index);
        }
    }
    public void playBackgroundMusic() {
        try {
            // Ruta al archivo de audio
            File audioFile = new File("src/resources/Taylor Swift - Daylight (Official Audio)(MP3_128K) (online-audio-converter.com).wav");

            // Obtén un clip de audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);

            // Reproduce en bucle continuo
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);

            // Empieza a reproducir
            backgroundClip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Error al cargar el audio de fondo");
        }
    }
}



