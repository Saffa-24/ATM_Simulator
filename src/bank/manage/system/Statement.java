package bank.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Statement extends JFrame implements ActionListener {

    String cardno;
    JTextArea statement;
    JButton back;

    public Statement(String cardno) {

        this.cardno = cardno;

        setTitle("Mini Statement");
        setSize(800, 850);
        setLayout(null);

        

        JLabel heading = new JLabel("MINI STATEMENT");
        heading.setBounds(160, 20, 200, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        statement = new JTextArea();
        statement.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(statement);
        scrollPane.setBounds(30, 70, 420, 400);
        add(scrollPane);

        int balance = 0;

        try {

            DBConnection con = new DBConnection();

            ResultSet rs = con.s.executeQuery(
                    "SELECT * FROM TRANSACTIONS WHERE CARD_NUMBER='" + cardno + "'");

            statement.append("CARD NUMBER : " + cardno + "\n\n");
            statement.append("----------------------------------\n");

            while (rs.next()) {

                String type = rs.getString("USE_TYPE");
                int amount = rs.getInt("AMOUNT");

                statement.append(type + "      Rs." + amount + "\n");

                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amount;
                }
                else if (type.equalsIgnoreCase("Withdraw")) {
                    balance -= amount;
                }
            }

            statement.append("\n----------------------------------\n");
            statement.append("CURRENT BALANCE : Rs." + balance);

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(190, 500, 100, 30);
        back.addActionListener(this);
        add(back);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            new Transaction(cardno);
            dispose();
        }
    }
}