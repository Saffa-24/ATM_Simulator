package bank.manage.system;

import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAdmin extends JFrame
{
    JTextField username;
    JPasswordField password;
    JButton create;

    CreateAdmin()
    {
        setLayout(null);
        setSize(400,300);

        username = new JTextField();
        username.setBounds(100,50,200,30);
        add(username);

        password = new JPasswordField();
        password.setBounds(100,100,200,30);
        add(password);

        create = new JButton("Create");
        create.setBounds(130,160,120,30);
        add(create);

        create.addActionListener(e -> {

            try
            {
                DBConnection con = new DBConnection();

                PreparedStatement ps =
                    con.con.prepareStatement(
                        "INSERT INTO ADMIN_LOGIN(USERNAME,PASSWORD,ROLE) VALUES(?,?,?)"
                    );

                ps.setString(1, username.getText());
                ps.setString(2, new String(password.getPassword()));
                ps.setString(3, "ADMIN");

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Admin Created Successfully");

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}

