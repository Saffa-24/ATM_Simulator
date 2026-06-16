package bank.manage.system;

import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class DeleteCustomer extends JFrame
{
    JTextField cardNo;
    JButton delete;
    JLabel card;

    DeleteCustomer()
    {
        setLayout(null);
        setSize(400,250);

        cardNo = new JTextField();
        cardNo.setBounds(100,50,200,30);
        add(cardNo);

        card=new JLabel("Card Number");
        card.setBounds(0,50,200,30);
        add(card);


        delete = new JButton("Delete");
        delete.setBounds(130,110,120,30);
        add(delete);

        delete.addActionListener(e -> {

            try
            {
                DBConnection con = new DBConnection();

                PreparedStatement ps =
                    con.con.prepareStatement(
                        "DELETE FROM ACCOUNT_DETAILS WHERE CARD_NUMBER=?"
                    );

                ps.setString(1, cardNo.getText());

                int rows = ps.executeUpdate();

                if(rows > 0)
                {
                    JOptionPane.showMessageDialog(this,
                            "Customer Deleted");
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Customer Not Found");
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}
