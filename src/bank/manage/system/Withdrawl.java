package bank.manage.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Withdrawl extends JFrame implements ActionListener {

    JLabel EnterLabel;
    JTextField Amount;
    JButton Withdrawl, Back;
    int amt = 0;
    String card_no;

    public Withdrawl(String Card_no)
    {
        this.card_no = Card_no;

        setSize(800,850);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(
            "C:\\Users\\saffa\\Downloads\\BankManagementSystem\\src\\ICONS\\atm.jpg"
        );

        Image i2 = i1.getImage().getScaledInstance(
            800,
            850,
            Image.SCALE_DEFAULT
        );

        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(0,0,800,850);
        label.setLayout(null);

        add(label);

        EnterLabel = new JLabel("PLEASE ENTER YOUR AMOUNT");
        EnterLabel.setBounds(175,260,600,30);
        EnterLabel.setFont(new Font("Arial",Font.BOLD,16));
        EnterLabel.setForeground(Color.WHITE);

        label.add(EnterLabel);

        Amount = new JTextField();
        Amount.setBounds(200,350,200,30);

        label.add(Amount);

        Withdrawl = new JButton("Withdraw");
        Withdrawl.setBounds(150,450,100,30);
        Withdrawl.addActionListener(this);

        label.add(Withdrawl);

        Back = new JButton("Back");
        Back.setBounds(350,450,100,30);
        Back.addActionListener(this);

        label.add(Back);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Withdraw");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == Withdrawl)
        {
            try
            {
                amt = Integer.parseInt(
                    Amount.getText().trim()
                );

                if(amt <= 0)
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid amount"
                    );
                    return;
                }

                DBConnection con =
                    new DBConnection();

                int currentBalance = 0;

                String selectSQL =
                    "SELECT BALANCE " +
                    "FROM TRANSACTIONS " +
                    "WHERE CARD_NUMBER='" +
                    card_no +
                    "' " +
                    "ORDER BY ID DESC LIMIT 1";

                java.sql.ResultSet rs =
                    con.s.executeQuery(selectSQL);

                if(rs.next())
                {
                    currentBalance =
                        rs.getInt("BALANCE");
                }

                if(amt > currentBalance)
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Insufficient Balance"
                    );
                    return;
                }

                int newBalance =
                    currentBalance - amt;

                String query =
                    "INSERT INTO TRANSACTIONS " +
                    "(CARD_NUMBER,USE_TYPE,AMOUNT,DATE_TIME,BALANCE) " +
                    "VALUES('" +
                    card_no +
                    "','Withdraw'," +
                    amt +
                    ",datetime('now')," +
                    newBalance +
                    ")";

                con.s.executeUpdate(query);

                JOptionPane.showMessageDialog(
                    this,
                    "₹" + amt +
                    " withdrawn successfully\n" +
                    "Remaining Balance : ₹" +
                    newBalance
                );

                new Transaction(card_no);

                dispose();
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(
                    this,
                    "Enter numbers only"
                );
            }
            catch(Exception e)
            {
                e.printStackTrace();

                JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
                );
            }
        }

        else if(ae.getSource() == Back)
        {
            new Transaction(card_no);
            dispose();
        }
    }

    public static void main(String[] args)
    {
        new Withdrawl(
            "504093198047526824"
        );
    }
}