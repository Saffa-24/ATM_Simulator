package bank.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, backBtn;

    public AdminLogin() {

        setTitle("Admin Login");
        setLayout(null);

        JLabel heading = new JLabel("ADMIN LOGIN");
        heading.setBounds(150, 30, 250, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        add(heading);

        JLabel user = new JLabel("Username:");
        user.setBounds(50, 100, 100, 25);
        add(user);

        usernameField = new JTextField();
        usernameField.setBounds(150, 100, 180, 25);
        add(usernameField);

        JLabel pass = new JLabel("Password:");
        pass.setBounds(50, 150, 100, 25);
        add(pass);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 180, 25);
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(80, 220, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(220, 220, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(800, 850

        );
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == loginBtn) {

            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            try {

                DBConnection conn = new DBConnection();
                Connection c = conn.con;

                PreparedStatement ps = c.prepareStatement(
                    "SELECT * FROM ADMIN WHERE USERNAME=? AND PASSWORD=?"
                );

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    String role = rs.getString("ROLE");

                    dispose();

                    new AdminDashboard(role);
                } else {

                    JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password"
                    );
                }

                rs.close();
                ps.close();
                c.close();

            } catch(Exception e) {

                e.printStackTrace();
            }

        } else if(ae.getSource() == backBtn) {

            dispose();
            new Login();
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}