package bank.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class SignUpTwo extends JFrame implements ActionListener {
    String formno;
    JLabel details, type, CardValue, PINValue;

    JRadioButton
     savings, current, fixed, recurring;
    JRadioButton seniorYes, seniorNo;
    JRadioButton existingYes, existingNo;

    JButton next;

    SignUpTwo(String formno) {

        this.formno=formno;
        setTitle("Account Details");

        setLayout(null);

       

        Random random = new Random();

        long first12 =
                Math.abs(random.nextLong() % 900000000000L)
                + 100000000000L;
        
        String cardNumber = "504093" + first12;
        
        long first4=Math.abs(random.nextLong()%9000L)+1000;
        String pinNumber=Long.toString(first4);
        ImageIcon i1 = new ImageIcon(
                "C:/Users/saffa/OneDrive/Desktop/BankManagementSystem/src/ICONS/logo.jpg");

        Image i2 = i1.getImage().getScaledInstance(
                100, 100, Image.SCALE_DEFAULT);

        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);

        image.setBounds(50, 20, 100, 100);

        add(image);

        // Heading
        details = new JLabel("Page 3 : Account Details");

        details.setFont(
                new Font("Raleway", Font.BOLD, 26));

        details.setBounds(250, 50, 350, 40);

        add(details);

        // Account Type
        type = new JLabel("Account Type:");

        type.setFont(
                new Font("Raleway", Font.BOLD, 18));

        type.setBounds(100, 180, 150, 30);

        add(type);

        savings = new JRadioButton("Savings Account");

        savings.setBounds(250, 180, 180, 30);

        savings.setBackground(Color.WHITE);

        add(savings);

        current = new JRadioButton("Current Account");

        current.setBounds(450, 180, 180, 30);

        current.setBackground(Color.WHITE);

        add(current);

        fixed = new JRadioButton("Fixed Deposit Account");

        fixed.setBounds(250, 230, 220, 30);

        fixed.setBackground(Color.WHITE);

        add(fixed);

        recurring = new JRadioButton("Recurring Deposit Account");

        recurring.setBounds(450, 230, 300, 30);

        recurring.setBackground(Color.WHITE);

        add(recurring);

        ButtonGroup accountGroup =
                new ButtonGroup();

        accountGroup.add(savings);
        accountGroup.add(current);
        accountGroup.add(fixed);
        accountGroup.add(recurring);
        JLabel CardNo = new JLabel("Card NO:");
        CardNo.setBounds(250, 300,300,30);
        add(CardNo);
        CardValue = new JLabel(cardNumber);
        CardValue.setBounds(350, 300,300,30);
        add(CardValue);


         JLabel PINNo = new JLabel("PIN NO:");
        PINNo.setBounds(250, 350,300,30);
        add(PINNo);
        PINValue = new JLabel(pinNumber);
        PINValue.setBounds(350, 350,300,30);
        add(PINValue);
        
    
        next =
                new JButton("NEXT");

        next.setBounds(500, 550, 120, 40);

        next.setBackground(Color.BLACK);

        next.setForeground(Color.WHITE);

        next.addActionListener(this);

        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        String accountType = null;

        if (savings.isSelected()) {
            accountType = "Savings";
        } else if (current.isSelected()) {
            accountType = "Current";
        } else if (fixed.isSelected()) {
            accountType = "Fixed Deposit";
        } else if (recurring.isSelected()) {
            accountType = "Recurring Deposit";
        }
        String Cardno = CardValue.getText();
        String PIN = PINValue.getText();
        
        
        DBConnection con = new DBConnection();

            String createTable =
                "CREATE TABLE IF NOT EXISTS ACCOUNT_DETAILS (" +
                "FORMNO VARCHAR(20) PRIMARY KEY," +
                "ACCOUNT_TYPE VARCHAR(50)," +
                "CARD_NUMBER VARCHAR(20) UNIQUE," +
                "PIN_NUMBER VARCHAR(10)" +
                ")";

            try {
                con.s.executeUpdate(createTable);
            } catch (Exception e) {
                e.printStackTrace();
            }


            String query =
    "INSERT INTO ACCOUNT_DETAILS " +
    "(FORMNO, ACCOUNT_TYPE, CARD_NUMBER, PIN_NUMBER) " +
    "VALUES ('" + formno + "','" +
    accountType + "','" +
    Cardno + "','" +
    PIN + "')";

try {
    con.s.executeUpdate(query);

    JOptionPane.showMessageDialog(
            null,
            "Account Created Successfully");

} catch (Exception e) {
    e.printStackTrace();
}


        if (accountType == null) {
            JOptionPane.showMessageDialog(this, "Please select an account type.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Selected account type: " + accountType, "Account Type", JOptionPane.INFORMATION_MESSAGE);
            new Transaction();
            dispose();
                }

    }
}
/*    public static void main(String[] args) {

        new SignUpTwo();
    }
}*/ 