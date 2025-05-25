package screens;

import Main.ConnectiontoDatabase;
import utils.BotonPersonalizado;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignIn {
    public JPanel signInPanel;
    public SignIn(JFrame frame) {
        signInPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawBackground(g, this, "FONDO.png");
            }

        };
        signInPanel.setLayout(null);
        signInPanel.setBackground(Color.decode("#CDB4D5"));

        //welcome
        JLabel welcome = new JLabel("Welcome Again!");
        welcome.setFont(Utils.getQuicksand(50f).deriveFont(Font.BOLD));
        welcome.setForeground(Color.decode("#4C1E4F"));
        welcome.setBounds(320, 90, 700, 80);
        signInPanel.add(welcome);


        //username
        JLabel username = new JLabel("Username:");
        username.setFont(Utils.getQuicksand(20f).deriveFont(Font.BOLD));
        username.setForeground(Color.decode("#4C1E4F"));
        username.setBounds(330, 200, 200, 30);
        signInPanel.add(username);
        JTextField usernameText = new JTextField();
        usernameText.setFont(Utils.getQuicksand(20f));
        usernameText.setBounds(450, 200, 200, 30);
        signInPanel.add(usernameText);

        //password
        JLabel password = new JLabel("Password:");
        password.setFont(Utils.getQuicksand(20f).deriveFont(Font.BOLD));
        password.setForeground(Color.decode("#4C1E4F"));
        password.setBounds(330, 300, 100, 30);
        signInPanel.add(password);
        JPasswordField passwordText = new JPasswordField();
        passwordText.setFont(Utils.getQuicksand(20f));
        passwordText.setBounds(450, 300, 200, 30);
        signInPanel.add(passwordText);

        BotonPersonalizado signIn = new BotonPersonalizado("Sign In");
        signIn.setColorFondo(Color.decode("#4C1E4F"));
        signIn.setBordePersonalizado(Color.decode("#CDB4D5"),3,30);
        signIn.setFont(Utils.getQuicksand(20f));
        signIn.setBounds(450, 400, 120, 40);
        signInPanel.add(signIn);

        JButton homeButton = Utils.createHomeButton(frame,"Back",15,15);
        homeButton.setFont(Utils.getQuicksand(16f));
        homeButton.setBackground(Color.decode("#4C1E4F"));
        homeButton.setBounds(0, 0, 100, 40);
        signInPanel.add(homeButton);


        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());

                if(checkUser(username, password)){
                    JOptionPane.showMessageDialog(signInPanel, "Welcome Again " + username);
                    Icons icons = new Icons(frame,"Sign In");
                    frame.setContentPane(icons.IconsPanel);
                    frame.validate();
                    frame.repaint();

                } else {
                    JOptionPane.showMessageDialog(signInPanel, "Invalid Username or Password");
                }

            }
        });




    }
    public boolean checkUser (String username, String password){
        String query = "SELECT * FROM users WHERE username = ? AND password_hash = ?";

        try (Connection conn = ConnectiontoDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Inicio de sesión exitoso para " + username);
                return true;
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }





}
