package bank.manage.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewCustomers extends JFrame implements ActionListener
{
    JTable table;
    DefaultTableModel model;
    JTextField searchField;
    JButton searchButton;
    JComboBox<String> accountFilter;
    JButton filterButton;

    public ViewCustomers()
    {
        setTitle("View Customers");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("SEARCH");
        searchButton.addActionListener(this);
        topPanel.add(new JLabel("Search Name/Card:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("NAME");
        model.addColumn("EMAIL");
        model.addColumn("ACCOUNT_TYPE");
        model.addColumn("CARD_NUMBER");
       

        table = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane,BorderLayout.CENTER);

        accountFilter = new JComboBox<>();

        accountFilter.addItem("All");
        accountFilter.addItem("Savings");
        accountFilter.addItem("Current");

        filterButton = new JButton("Filter");

        filterButton.addActionListener(this);

        topPanel.add(new JLabel("Account Type:"));
        topPanel.add(accountFilter);
        topPanel.add(filterButton);
            

        loadCustomers();

        setVisible(true);
    }


    private void loadCustomers()
    {
        try
        {
            DBConnection conn = new DBConnection();

            Connection c = conn.con;

            PreparedStatement ps =
    c.prepareStatement(
        "SELECT s.NAME, s.EMAIL, a.ACCOUNT_TYPE, a.CARD_NUMBER FROM SIGNUP s JOIN ACCOUNT_DETAILS a ON s.FORMNO = a.FORMNO"
    );

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                model.addRow(new Object[]
                {
                    rs.getString("NAME"),
                    rs.getString("EMAIL"),
                    rs.getString("ACCOUNT_TYPE"),
                    rs.getString("CARD_NUMBER")
                    
                });
            }

            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void searchCustomer()
{
    try
    {
        model.setRowCount(0);

        DBConnection conn = new DBConnection();
        Connection c = conn.con;

        PreparedStatement ps =
            c.prepareStatement(
                "SELECT s.NAME, s.EMAIL, a.ACCOUNT_TYPE, a.CARD_NUMBER " +
                "FROM SIGNUP s " +
                "JOIN ACCOUNT_DETAILS a " +
                "ON s.FORMNO = a.FORMNO " +
                "WHERE s.NAME LIKE ? OR a.CARD_NUMBER LIKE ?"
            );

        ps.setString(1, "%" + searchField.getText() + "%");
        ps.setString(2, "%" + searchField.getText() + "%");

        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            int count = 0;
            model.addRow(new Object[]
            {
                rs.getString("NAME"),
                rs.getString("EMAIL"),
                rs.getString("ACCOUNT_TYPE"),
                rs.getString("CARD_NUMBER")

            });
           
            JOptionPane.showMessageDialog(
    this,
    
    " record(s) found"
);
        }

        rs.close();
        ps.close();
        c.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
private void filterCustomers()
{
    try
    {
        model.setRowCount(0);

        DBConnection conn = new DBConnection();
        Connection c = conn.con;

        String type =
            accountFilter.getSelectedItem().toString();

        PreparedStatement ps;

        if(type.equals("All"))
        {
            ps = c.prepareStatement(
                "SELECT s.NAME,s.EMAIL,a.ACCOUNT_TYPE,a.CARD_NUMBER " +
                "FROM SIGNUP s " +
                "JOIN ACCOUNT_DETAILS a " +
                "ON s.FORMNO=a.FORMNO"
            );
        }
        else
        {
            ps = c.prepareStatement(
                "SELECT s.NAME,s.EMAIL,a.ACCOUNT_TYPE,a.CARD_NUMBER " +
                "FROM SIGNUP s " +
                "JOIN ACCOUNT_DETAILS a " +
                "ON s.FORMNO=a.FORMNO " +
                "WHERE a.ACCOUNT_TYPE=?"
            );

            ps.setString(1, type);
        }

        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            model.addRow(new Object[]
            {
                rs.getString("NAME"),
                rs.getString("EMAIL"),
                rs.getString("ACCOUNT_TYPE"),
                rs.getString("CARD_NUMBER")
            });
        }

        rs.close();
        ps.close();
        c.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}




@Override
public void actionPerformed(ActionEvent ae)
{
    if(ae.getSource() == searchButton)
    {
        searchCustomer();
    }

    else if(ae.getSource() == filterButton)
    {
        filterCustomers();
    }
}
    public static void main(String[] args)
{
    new ViewCustomers();
}
        
 }
