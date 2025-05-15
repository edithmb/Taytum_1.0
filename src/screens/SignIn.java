package screens;

import Main.ConnectiontoDatabase;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignIn {
    public JPanel signInPanel;
    public SignIn() {
        signInPanel = new JPanel();
        signInPanel.setLayout(null);
        signInPanel.setPreferredSize(new Dimension(1050,600));
        signInPanel.setBackground(Color.decode("#CDB4D5"));

        //welcome
        JLabel welcome = new JLabel("Welcome Again!!!");
        welcome.setFont(Utils.getQuicksand(50f));
        welcome.setForeground(Color.decode("#4C1E4F"));
        welcome.setBounds(400, 100, 700, 50);
        signInPanel.add(welcome);


        //username
        JLabel username = new JLabel("Username");
        username.setFont(Utils.getQuicksand(20f));
        username.setBounds(350, 200, 100, 30);
        signInPanel.add(username);
        JTextField usernameText = new JTextField();
        usernameText.setFont(Utils.getQuicksand(20f));
        usernameText.setBounds(450, 200, 200, 30);
        signInPanel.add(usernameText);

        //password
        JLabel password = new JLabel("Password");
        password.setFont(Utils.getQuicksand(20f));
        password.setBounds(350, 300, 100, 30);
        signInPanel.add(password);
        JPasswordField passwordText = new JPasswordField();
        passwordText.setFont(Utils.getQuicksand(20f));
        passwordText.setBounds(450, 300, 200, 30);
        signInPanel.add(passwordText);

        JButton signIn = new JButton("Sign In");
        signIn.setFont(Utils.getQuicksand(20f));
        signIn.setBounds(480, 400, 120, 40);
        signInPanel.add(signIn);

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());

                if(checkUser(username, password)){
                    JOptionPane.showMessageDialog(signInPanel, "Welcome Again " + username);

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
