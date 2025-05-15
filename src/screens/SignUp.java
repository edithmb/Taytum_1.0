package screens;

import Main.ConnectiontoDatabase;
import jdk.jshell.execution.Util;
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

    public SignUp() {
        SignUpPanel = new JPanel();
        SignUpPanel.setLayout(null);
        SignUpPanel.setPreferredSize(new Dimension(1050,600));
        SignUpPanel.setBackground(Color.decode("#CDB4D5"));


        //welcome
        JLabel welcome = new JLabel("Welcome!!!");
        welcome.setFont(Utils.getQuicksand(50f));
        welcome.setForeground(Color.decode("#4C1E4F"));
        welcome.setBounds(350, 100, 700, 60);
        SignUpPanel.add(welcome);

        //username
        JLabel username = new JLabel("Username");
        username.setFont(Utils.getQuicksand(20f));
        username.setBounds(350, 200, 100, 30);
        SignUpPanel.add(username);
        JTextField usernameText = new JTextField();
        usernameText.setFont(Utils.getQuicksand(20f));
        usernameText.setBounds(450, 200, 200, 30);
        SignUpPanel.add(usernameText);

        //password
        JLabel password = new JLabel("Password");
        password.setFont(Utils.getQuicksand(20f));
        password.setBounds(350, 300, 100, 30);
        SignUpPanel.add(password);
        JPasswordField passwordText = new JPasswordField();
        passwordText.setFont(Utils.getQuicksand(20f));
        passwordText.setBounds(450, 300, 200, 30);
        SignUpPanel.add(passwordText);

        JButton singUp = new JButton("Sign Up");
        singUp.setFont(Utils.getQuicksand(20f));
        singUp.setBounds(480, 400, 120, 40);
        SignUpPanel.add(singUp);

        singUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());

                if(registerPerson(username, password)){
                    JOptionPane.showMessageDialog(SignUpPanel,  "User registered successfully!");
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
