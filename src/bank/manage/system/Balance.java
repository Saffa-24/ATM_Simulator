package bank.manage.system;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.ResultSet;

public class Balance extends JFrame implements ActionListener {
    String cardno;
    JLabel EnterLabel;
    public Balance(String cardno)
    {
        this.cardno = cardno;
        setSize(800,850);
        setLayout(null);

        ImageIcon i1 = new ImageIcon("C:\\Users\\saffa\\Downloads\\BankManagementSystem\\src\\ICONS\\atm.jpg");
        Image i2 = i1.getImage().getScaledInstance(800, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(0,0,800,850);
        label.setLayout(null);
        add(label);

        int balance = 0;
        try {
            DBConnection con = new DBConnection();
            ResultSet rs = con.s.executeQuery(
                "SELECT USE_TYPE, AMOUNT FROM TRANSACTIONS WHERE CARD_NUMBER='" + cardno + "'"
            );

            while(rs.next())
            {
                String type = rs.getString("USE_TYPE");

                if("Deposit".equals(type))
                {
                    balance += rs.getInt("AMOUNT");
                }
                else if("Withdraw".equals(type))
                {
                    balance -= rs.getInt("AMOUNT");
                }
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching balance: " + e.getMessage());
        }


        EnterLabel = new JLabel("BALANCE AMOUNT :");
        EnterLabel.setBounds(175,350,600,30);
        EnterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        EnterLabel.setForeground(Color.WHITE);
        label.add(EnterLabel);
        
        JLabel amount = new JLabel(String.valueOf(balance));
        amount.setBounds(175,390,600,30);
        amount.setFont(new Font("Arial", Font.BOLD, 16));
        amount.setForeground(Color.WHITE);
        label.add(amount);

        JButton Back=new JButton("Back");
        Back.setBounds(350,450,100,30);
        Back.addActionListener(this);
        label.add(Back);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Balance");
        setVisible(true);
        setLocationRelativeTo(null);
}
    public void actionPerformed(ActionEvent ae){
        new Transaction(cardno);
        dispose();

    }
}