package screens;

import utils.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Icons {
    public JPanel IconsPanel;
    private JLabel selectedAvatarPreview;

    public Icons(JFrame frame, String ruta) {
        IconsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawBackground(g, this, "FONDO.png"); // Cambia si usas otro fondo
            }
        };
        IconsPanel.setLayout(null);

        // Botón Home (arriba izquierda)
        JButton homeButton = Utils.createHomeButton(frame, "Back", 15, 15);
        homeButton.setBackground(Color.decode("#4C1E4F"));
        homeButton.setFont(Utils.getQuicksand(16f));
        homeButton.setBounds(0, 0, 100, 40);
        IconsPanel.add(homeButton);

        String[] avatarPaths = {
                "src/resources/guitar.png",
                "src/resources/microphone.png",
                "src/resources/usuario.png"
        };

        int x = 220;
        for (int i = 0; i < avatarPaths.length; i++) {
            String path = avatarPaths[i];
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JButton avatarButton = new JButton(new ImageIcon(img));
            avatarButton.setBounds(x, 150, 120, 120);
            avatarButton.setFocusPainted(false);
            avatarButton.setContentAreaFilled(false);
            avatarButton.setBorder(BorderFactory.createLineBorder(Color.decode("#4C1E4F"), 2));

            int finalI = i; // para usar en el listener
            avatarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ir a pantalla de niveles
                    Levels levels = new Levels(frame);
                    frame.setContentPane(levels.LevelsPanel);
                    frame.validate();
                    frame.repaint();
                }
            });

            IconsPanel.add(avatarButton);
            x += 160;
        }

        homeButton.addActionListener(e -> {
            StartScreen home = new StartScreen(frame);
            frame.setContentPane(home.MenuPrincipal);
            frame.validate();
            frame.repaint();
        });
    }
}

