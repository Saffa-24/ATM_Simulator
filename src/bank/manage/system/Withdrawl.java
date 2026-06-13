package bank.manage.system;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Withdrawl extends JFrame implements ActionListener {
    JLabel EnterLabel;
    JTextField Amount;
    JButton Withdrawl,Back;
    int amt=0;
    String card_no;

    public Withdrawl(String Card_no)
    {
        this.card_no = Card_no;
        setSize(800,850);
        setLayout(null);

        ImageIcon i1 = new ImageIcon("C:\\Users\\saffa\\Downloads\\BankManagementSystem\\src\\ICONS\\atm.jpg");
        Image i2 = i1.getImage().getScaledInstance(800, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(0,0,800,850);
        label.setLayout(null);
        add(label);

        EnterLabel = new JLabel("PLEASE ENTER YOUR AMOUNT ");
        EnterLabel.setBounds(175,260,600,30);
        EnterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        EnterLabel.setForeground(Color.WHITE);
        label.add(EnterLabel);

        Amount=new JTextField();
        Amount.setBounds(200,350,200,30);
        label.add(Amount);

        Withdrawl=new JButton("Withdrawl");
        Withdrawl.setBounds(150,450,100,30);
        Withdrawl.addActionListener(this);
        label.add(Withdrawl);

        Back=new JButton("Back");
        Back.setBounds(350,450,100,30);
        Back.addActionListener(this);
        label.add(Back);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Deposit");
        setVisible(true);
        setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==Withdrawl)
        {
            try{
               amt = Integer.parseInt(Amount.getText());
               DBConnection con=new DBConnection();
                 String createTable =
                        "CREATE TABLE IF NOT EXISTS TRANSACTIONS (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "CARD_NUMBER VARCHAR(30)," +
                        "USE_TYPE VARCHAR(20)," +
                        "AMOUNT INTEGER," +
                        "DATE_TIME VARCHAR(50)" +
                        ")";
                System.out.println("Transaction Created");
                con.s.executeUpdate(createTable);
                String query =
                    "INSERT INTO TRANSACTIONS " +
                    "(CARD_NUMBER,USE_TYPE,AMOUNT,DATE_TIME) " +
                    "VALUES('" +
                    card_no + "','Withdraw'," +
                    amt + ",datetime('now'))";
                    JOptionPane.showMessageDialog(this, "The "+amt+" is withdrawed");

                con.s.executeUpdate(query);
                 new Transaction(card_no);
                dispose();
            } catch(Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this ,e);
            }
        }
        else if (ae.getSource()==Back)
         {
            new Transaction(card_no);
            dispose();
        }
    }
}

    

