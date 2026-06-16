package bank.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Transaction extends JFrame implements ActionListener {

    JButton Deposit,FastCash,Delete,Withdrawl,MiniStmt,Balance,Exit;
    JLabel enter;
    String cardno;


    public Transaction(String cardno) {
        this.cardno = cardno;

        setLayout(null);

        ImageIcon i1 = new ImageIcon("C:\\Users\\saffa\\Downloads\\BankManagementSystem\\src\\ICONS\\atm.jpg");

        Image i2 = i1.getImage().getScaledInstance(
                800, 850, Image.SCALE_DEFAULT);

        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);

        label.setBounds(0, 0, 800, 850);
        enter=new JLabel("PLEASE SELECT YOUR TRANSACTION");
        enter.setBounds(150,260,600,30);
        enter.setFont(new Font("Arial", Font.BOLD, 16));
        enter.setForeground(Color.WHITE);


        Deposit=new JButton("Deposit");
        Deposit.setBounds(150,350,100,20);
        Deposit.setFont(new Font("Arial", Font.BOLD, 13));
        Deposit.addActionListener(this);
        

        FastCash=new JButton("Fast Cash");
        FastCash.setBounds(300,350,100,20);
        FastCash.setFont(new Font("Arial", Font.BOLD, 13));
        FastCash.addActionListener(this);


        Delete =new JButton("Delete");
        Delete.setBounds(150,400,100,20);
        Delete.setFont(new Font("Arial", Font.BOLD, 13));
        Delete.addActionListener(this);

        Withdrawl=new JButton("Withdrawl");
        Withdrawl.setBounds(300,400,100,20);
        Withdrawl.setFont(new Font("Arial", Font.BOLD, 13));
        Withdrawl.addActionListener(this);

        MiniStmt=new JButton("Statement");
        MiniStmt.setBounds(150,450,100,20);
        MiniStmt.setFont(new Font("Arial", Font.BOLD, 13));
        MiniStmt.addActionListener(this);

        Balance = new JButton("Balance Enquiry");
        Balance.setBounds(300,450,150,20);
        Balance.setFont(new Font("Arial", Font.BOLD, 13));
        Balance.addActionListener(this);
                
        Exit = new JButton("Exit");
        Exit.setBounds(225,500,100,20);
        Exit.setFont(new Font("Arial", Font.BOLD, 13));
        Exit.addActionListener(this);
        
        add(enter);
        add(Exit);
        add(Withdrawl);
        add(FastCash);
        add(Delete);
        add(MiniStmt);
        add(Balance);
        add(Deposit);
        add(label);
       
        setTitle("ATM Simulator");
        setSize(800, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==Deposit)
        {
            JOptionPane.showMessageDialog(this, "Deposit page will appear soon ");
            new Deposit(cardno);
            dispose();
        }
        else if(ae.getSource()==Withdrawl)
        {
            JOptionPane.showMessageDialog(this, "Withdrawl page will appear soon "); 
            new Withdrawl(cardno);
            dispose();

        }
        else if(ae.getSource()==FastCash)
        {
            JOptionPane.showMessageDialog(this, " FastCash page will come soon ");
            new FastCash(cardno);
            dispose();

        }
        else if(ae.getSource()==Balance){
            new Balance(cardno);
            dispose();
        }
        else if(ae.getSource()==Delete)
        {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this account permanently?",
                "Delete Account",
                JOptionPane.YES_NO_OPTION
            );

            if(choice == JOptionPane.YES_OPTION)
            {
                try
                {
                     DBConnection conn = new DBConnection();
                     Connection c = conn.con;

                    String formno = "";

                    PreparedStatement ps =
                        c.prepareStatement(
                            "SELECT FORMNO FROM ACCOUNT_DETAILS WHERE CARD_NUMBER=?"
                        );

                    ps.setString(1, cardno);

                    ResultSet rs = ps.executeQuery();

                    if(rs.next())
                    {
                        formno = rs.getString("FORMNO");
                    }
                    PreparedStatement ps1 =
                     c.prepareStatement(
                     "DELETE FROM TRANSACTIONS WHERE CARD_NUMBER=?"
                     );

                    ps1.setString(1, cardno);
                    ps1.executeUpdate();

                     PreparedStatement ps2 =
                        c.prepareStatement(
                            "DELETE FROM ACCOUNT_DETAILS WHERE CARD_NUMBER=?"
                        );

                    ps2.setString(1, cardno);
                    ps2.executeUpdate();

                    PreparedStatement ps3 =
                        c.prepareStatement(
                            "DELETE FROM ADDITIONAL_DETAILS WHERE FORMNO=?"
                        );

                    ps3.setString(1, formno);
                    ps3.executeUpdate();
                                        
                    PreparedStatement ps4 =
                    c.prepareStatement(
                     "DELETE FROM SIGNUP WHERE FORMNO=?"
                     );

                    ps4.setString(1, formno);

                    int result = ps4.executeUpdate();

                    


                    
                    

                    if(result > 0)
                    {
                        JOptionPane.showMessageDialog(
                            this,
                            "Account Deleted Successfully"
                        );

                        dispose();
                        new Login();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(
                            this,
                            "Account Not Found"
                        );
                    }

                    ps1.close();
                    ps2.close();
                    ps3.close();
                    ps4.close();
                    c.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }
        else if(ae.getSource()==MiniStmt)
        {
            new Statement(cardno);
            dispose();
            JOptionPane.showMessageDialog(this, "Statement page will come soon ");
        }
        else if(ae.getSource()==Exit)
        {
            System.exit(0);
        }

    }
}