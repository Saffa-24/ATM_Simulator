package bank.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame implements ActionListener {


JButton Login,Register,Clear;
JTextField cardtext;
JPasswordField pintext;
 

    Login() {

        setTitle("AUTOMATED TELLER MACHINE");
        setFocusable(false);

        // Load image directly from file and scale it
        ImageIcon i1 = new ImageIcon("C:/Users/saffa/OneDrive/Desktop/BankManagementSystem/src/ICONS/logo.jpg");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(50, 40, 100, 100);
        add(label);
        setLayout(null);
       
        setSize(800,850);


        JLabel text= new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Times New Roman",Font.BOLD,38));
        text.setBounds(200,40,400,70);
        add(text);

        JLabel cardno= new JLabel("CARD NO:");
        cardno.setFont(new Font("Times New Roman",Font.BOLD,18));
        cardno.setBounds(120,150,150 ,30);
        add(cardno);
        cardtext= new JTextField();
        cardtext.setBounds(300,150,200,30);
        add(cardtext);

        JLabel pin= new JLabel("PIN NO:");
        pin.setFont(new Font("Times New Roman",Font.BOLD,18));
        pin.setBounds(120,220,200,30);
        add(pin);
        pintext= new JPasswordField();
        pintext.setBounds(300,220,200,30);
        add(pintext);

        Login=new JButton("SIGN IN");
        Login.setBounds(300,300,100,30);
        Login.setBackground(Color.BLACK);
        Login.setForeground(Color.WHITE);
        Login.addActionListener(this);
        add(Login);

        Clear=new JButton("Clear");
        Clear.setBounds(400,300,100,30);
        Clear.setBackground(Color.BLACK);
        Clear.setForeground(Color.WHITE);
        Clear.addActionListener(this);
        add(Clear);

        Register=new JButton("SIGN UP");
        Register.setBounds(300,350,200,30);
        Register.setBackground(Color.BLACK);
        Register.setForeground(Color.WHITE);
        Register.addActionListener(this);
        add(Register);


        
        
        setLocation(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==Clear)
        {
            cardtext.setText("");
            pintext.setText(""); 
            
        }
        else if(ae.getSource()==Register)
        {
            new SignUp();
            dispose();
        }
        else if(ae.getSource()==Login)
        {
            String Cardno=cardtext.getText();
            String Pin=new String(pintext.getPassword());
            try{
                DBConnection con =new DBConnection();
                
                String query =
                        "SELECT * FROM ACCOUNT_DETAILS WHERE CARD_NUMBER='" +
                        Cardno +
                        "' AND PIN_NUMBER='" +
                        Pin +
                        "'";
                java.sql.ResultSet rs= con.s.executeQuery(query);
                if(rs.next())
                {
                    JOptionPane.showMessageDialog(this,"Login Successfully");
                    new CustomerProfile(Cardno);
                    dispose();
                    rs.close();
                    con.s.close();
                    con.con.close();
    
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Invalid Card Number or Pin Number");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            


        }
    }
    public static void main(String[] args) {

        new Login();


    }
}