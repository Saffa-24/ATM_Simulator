package bank.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Transaction extends JFrame implements ActionListener {

    JButton Deposit,FastCash,PinChange,Withdrawl,MiniStmt,Balance,Exit;
    JLabel enter;

    public Transaction() {

        setLayout(null);

        ImageIcon i1 = new ImageIcon(
                "C:/Users/saffa/OneDrive/Downloads/BANKMANAGEMENTSYSTEM/src/ICONS/atm.jpg");

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


        PinChange=new JButton("Change");
        PinChange.setBounds(150,400,100,20);
        PinChange.setFont(new Font("Arial", Font.BOLD, 13));
        PinChange.addActionListener(this);

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
        add(PinChange);
        add(MiniStmt);
        add(Balance);
        add(Deposit);
        add(label);
       

        setSize(800, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==Deposit)
        {
            JOptionPane.showMessageDialog(this, "Deposit page will come soon ");
        }
        else if(ae.getSource()==Withdrawl)
        {
            JOptionPane.showMessageDialog(this, "Withdrawl page will come soon "); 
        }
        else if(ae.getSource()==FastCash)
        {
            JOptionPane.showMessageDialog(this, " FatCash page will come soon ");
        }
        else if(ae.getSource()==Balance)
        {
            JOptionPane.showMessageDialog(this, "Balance Enquiry page will come soon ");
        }
        else if(ae.getSource()==PinChange)
        {
            JOptionPane.showMessageDialog(this, "PIN Change page will come soon ");
        }
        else if(ae.getSource()==MiniStmt)
        {
            JOptionPane.showMessageDialog(this, "Statement page will come soon ");
        }
        else if(ae.getSource()==Exit)
        {
            System.exit(0);
        }

    }
    public static void main(String[] args) {
    new Transaction();
}
}