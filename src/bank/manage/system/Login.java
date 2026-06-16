package bank.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JButton Login, Register, Clear, admin;
    JTextField cardtext;
    JPasswordField pintext;

    Login() {

        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Logo
        ImageIcon i1 = new ImageIcon(
            "C:/Users/saffa/OneDrive/Desktop/BankManagementSystem/src/ICONS/logo.jpg"
        );

        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(i2));
        logo.setBounds(50, 40, 100, 100);
        add(logo);

        // Heading
        JLabel text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Times New Roman", Font.BOLD, 38));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBounds(250, 40, 500, 70);
        add(text);

        // Card Number
        JLabel cardno = new JLabel("CARD NO:");
        cardno.setFont(new Font("Times New Roman", Font.BOLD, 18));
        cardno.setBounds(250, 180, 150, 30);
        add(cardno);

        cardtext = new JTextField();
        cardtext.setBounds(420, 180, 200, 30);
        add(cardtext);

        // PIN
        JLabel pin = new JLabel("PIN NO:");
        pin.setFont(new Font("Times New Roman", Font.BOLD, 18));
        pin.setBounds(250, 250, 150, 30);
        add(pin);

        pintext = new JPasswordField();
        pintext.setBounds(420, 250, 200, 30);
        add(pintext);

        // Sign In
        Login = new JButton("SIGN IN");
        Login.setBounds(420, 320, 100, 30);
        Login.setBackground(Color.BLACK);
        Login.setForeground(Color.WHITE);
        Login.addActionListener(this);
        add(Login);

        // Clear
        Clear = new JButton("CLEAR");
        Clear.setBounds(530, 320, 100, 30);
        Clear.setBackground(Color.BLACK);
        Clear.setForeground(Color.WHITE);
        Clear.addActionListener(this);
        add(Clear);

        // Sign Up
        Register = new JButton("SIGN UP");
        Register.setBounds(420, 370, 210, 30);
        Register.setBackground(Color.BLACK);
        Register.setForeground(Color.WHITE);
        Register.addActionListener(this);
        add(Register);

        // Admin Login
        admin = new JButton("LOGIN AS ADMIN");
        admin.setBounds(420, 430, 210, 30);
        admin.setBackground(Color.BLACK);
        admin.setForeground(Color.WHITE);
        admin.addActionListener(this);
        add(admin);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == Clear) {

            cardtext.setText("");
            pintext.setText("");

        } else if (ae.getSource() == Register) {

            new SignUp();
            dispose();

        } else if (ae.getSource() == Login) {

            String cardNo = cardtext.getText();
            String pin = new String(pintext.getPassword());

            try {

                DBConnection con = new DBConnection();

                String query =
                    "SELECT * FROM ACCOUNT_DETAILS WHERE CARD_NUMBER='" +
                    cardNo +
                    "' AND PIN_NUMBER='" +
                    pin +
                    "'";

                java.sql.ResultSet rs = con.s.executeQuery(query);

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Login Successful"
                    );

                    new CustomerProfile(cardNo);
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                        this,
                        "Invalid Card Number or PIN"
                    );
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == admin) {

            new AdminLogin();
            dispose();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}