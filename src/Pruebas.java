import Main.ConnectiontoDatabase;
import screens.Instructions;
import screens.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Pruebas {
    private static JPanel Principal;
    public static void main(String[] args) {
        Instructions start = new Instructions();
        Principal.setLayout(new BorderLayout());


        try {
            Connection con = ConnectiontoDatabase.getConnection();

            if (con != null && con.isClosed()) {
                System.out.println("Conexion hechsa");
            } else {
                System.out.println("algofallo");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
}
