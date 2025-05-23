package screens;

import Main.ConnectiontoDatabase;
import jdk.jshell.execution.Util;
import utils.BotonPersonalizado;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp {
    public JPanel SignUpPanel;

    public SignUp(JFrame frame) {
        SignUpPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Utils.drawBackground(g, this, "FONDO.png");
            }
        };
        SignUpPanel.setLayout(null);
        SignUpPanel.setBackground(Color.decode("#CDB4D5"));


        //welcome
        JLabel welcome = new JLabel("Welcome!!!");
        welcome.setFont(Utils.getQuicksand(50f).deriveFont(Font.BOLD));
        welcome.setForeground(Color.decode("#4C1E4F"));
        welcome.setBounds(370, 100, 700, 60);
        SignUpPanel.add(welcome);

        //username
        JLabel username = new JLabel("Username:");
        username.setFont(Utils.getQuicksand(20f).deriveFont(Font.BOLD));
        username.setForeground(Color.decode("#4C1E4F"));
        username.setBounds(330, 200, 150, 30);
        SignUpPanel.add(username);
        JTextField usernameText = new JTextField();
        usernameText.setFont(Utils.getQuicksand(20f));
        usernameText.setBounds(450, 200, 200, 30);
        SignUpPanel.add(usernameText);

        //password
        JLabel password = new JLabel("Password:");
        password.setFont(Utils.getQuicksand(20f).deriveFont(Font.BOLD));
        password.setForeground(Color.decode("#4C1E4F"));
        password.setBounds(330, 300, 150, 30);
        SignUpPanel.add(password);
        JPasswordField passwordText = new JPasswordField();
        passwordText.setFont(Utils.getQuicksand(20f));
        passwordText.setBounds(450, 300, 200, 30);
        SignUpPanel.add(passwordText);

        BotonPersonalizado signUp = new BotonPersonalizado("Sign Up");
        signUp.setColorFondo(Color.decode("#4C1E4F"));
        signUp.setBordePersonalizado(Color.decode("#CDB4D5"),3,30);
        signUp.setFont(Utils.getQuicksand(20f));
        signUp.setBounds(480, 400, 120, 40);
        SignUpPanel.add(signUp);

        JButton homeButton = Utils.createHomeButton(frame,"Back",15,15);
        homeButton.setFont(Utils.getQuicksand(16f));
        homeButton.setBounds(0, 0, 100, 40);
        SignUpPanel.add(homeButton);


        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());

                if(registerPerson(username, password)){
                    JOptionPane.showMessageDialog(SignUpPanel,  "User registered successfully!");
                    Levels levels = new Levels(frame);
                    frame.setContentPane(levels.LevelsPanel);
                    frame.validate();
                    frame.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(SignUpPanel, "Error registering user. It may already exist.");
                }

            }
        });
    }

    private boolean registerPerson(String username, String password) {

        try (Connection conn = ConnectiontoDatabase.getConnection()){
            String query = "select * from users where username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return false;
            }

            String insertQuery = "insert into users(username,password_hash, is_guest) values(?,?, false)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);

            int rowsInserted = insertStmt.executeUpdate();
            return rowsInserted > 0;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            return false;

        }

    }
}
