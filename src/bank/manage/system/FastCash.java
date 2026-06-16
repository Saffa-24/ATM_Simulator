package bank.manage.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FastCash extends JFrame implements ActionListener {

    JLabel EnterLabel;
    JButton one, five, ten, twenty, Withdrawl, Back;
    int amt = 0;
    String card_no;

    public FastCash(String Card_no)
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

        EnterLabel = new JLabel("SELECT WITHDRAWAL AMOUNT");
        EnterLabel.setBounds(175,260,600,30);
        EnterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        EnterLabel.setForeground(Color.WHITE);

        label.add(EnterLabel);

        one = new JButton("1000");
        one.setBounds(150,300,100,30);
        one.addActionListener(this);
        label.add(one);

        five = new JButton("5000");
        five.setBounds(300,300,100,30);
        five.addActionListener(this);
        label.add(five);

        ten = new JButton("10000");
        ten.setBounds(150,350,100,30);
        ten.addActionListener(this);
        label.add(ten);

        twenty = new JButton("20000");
        twenty.setBounds(300,350,100,30);
        twenty.addActionListener(this);
        label.add(twenty);

        Withdrawl = new JButton("Withdraw");
        Withdrawl.setBounds(150,450,100,30);
        Withdrawl.addActionListener(this);
        label.add(Withdrawl);

        Back = new JButton("Back");
        Back.setBounds(350,450,100,30);
        Back.addActionListener(this);
        label.add(Back);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Fast Cash");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object src = ae.getSource();

        if(src == one)
        {
            amt = 1000;
            JOptionPane.showMessageDialog(this,
                "Selected Amount : ₹" + amt);
        }

        else if(src == five)
        {
            amt = 5000;
            JOptionPane.showMessageDialog(this,
                "Selected Amount : ₹" + amt);
        }

        else if(src == ten)
        {
            amt = 10000;
            JOptionPane.showMessageDialog(this,
                "Selected Amount : ₹" + amt);
        }

        else if(src == twenty)
        {
            amt = 20000;
            JOptionPane.showMessageDialog(this,
                "Selected Amount : ₹" + amt);
        }

        else if(src == Withdrawl)
        {
            try
            {
                if(amt == 0)
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Please select an amount first"
                    );
                    return;
                }

                DBConnection con = new DBConnection();

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
            catch(Exception e)
            {
                e.printStackTrace();

                JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
                );
            }
        }

        else if(src == Back)
        {
            new Transaction(card_no);
            dispose();
        }
    }

    public static void main(String[] args)
    {
        new FastCash("504093198047526824");
    }
}