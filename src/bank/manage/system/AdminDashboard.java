package bank.manage.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminDashboard extends JFrame implements ActionListener
{
    String role;
    JButton createAdmin;
    JButton deleteCustomer;
    JButton customersBtn;
    int totalAccounts = 0;
    int savingsAccounts = 0;
    int currentAccounts = 0;
    int totalTransactions = 0;
    JButton statisticsBtn;
   
    JPanel contentPanel;
    JPanel totalAccountsCard;
    JPanel savings;
    JPanel current;
    JPanel deposits;
    JPanel withdrawals;
    JPanel transactions;
    JButton logoutBtn;
    long totalDeposits = 0;
    long totalWithdrawals = 0;


    public AdminDashboard(String role)
    {
        this.role = role;

        setTitle("Admin Dashboard");
        setLayout(null);
        setSize(1400,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadDashboardData();
        
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(new Color(255,255,255));
        sidebar.setBounds(0,0,200,600);

        add(sidebar);
        
        totalAccountsCard = createCard(
            "Total Accounts",
            String.valueOf(this.totalAccounts),
            "Registered",
            new Color(52,152,219));

        totalAccountsCard.setBounds(150,40,220,120);

        savings = createCard(
            "Savings Accounts",
            String.valueOf(this.savingsAccounts),
            "Active",
            new Color(46,204,113));

        savings.setBounds(400,40,220,120);

        current = createCard(
            "Current Accounts",
            String.valueOf(currentAccounts),
            "Active",
            new Color(155,89,182));

        current.setBounds(650,40,220,120);

        deposits = createCard(
            "Deposits",
            "₹" + totalDeposits,
            "Total",
            new Color(230,126,34));

        deposits.setBounds(150,200,220,120);

        withdrawals = createCard(
            "Withdrawals",
            "₹" + totalWithdrawals,
            "Total",
            new Color(231,76,60));

        withdrawals.setBounds(400,200,220,120);

        transactions = createCard(
            "Transactions",
            String.valueOf(totalTransactions),
            "Completed",
            new Color(26,188,156));

        transactions.setBounds(650,200,220,120);
    
        JButton dashboardBtn = new JButton("Dashboard");
        dashboardBtn.setBounds(20,50,160,40);

         customersBtn = new JButton("Customers");
        customersBtn.setBounds(20,110,160,40);
        customersBtn.addActionListener(this);


        statisticsBtn = new JButton("Statistics");
        statisticsBtn.setBounds(20,170,160,40);
        statisticsBtn.addActionListener(this);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(20,500,160,40);

        sidebar.add(dashboardBtn);
        sidebar.add(customersBtn);
        
        sidebar.add(statisticsBtn);
        sidebar.add(logoutBtn);
        if(role.equals("SUPER_ADMIN"))
         {
            createAdmin = new JButton("Create Admin");
            createAdmin.setBounds(20,230,160,40);
            createAdmin.addActionListener(this);
            sidebar.add(createAdmin);

            deleteCustomer = new JButton("Delete Customer");
            deleteCustomer.setBounds(20,290,160,40);
            deleteCustomer.addActionListener(this);
            sidebar.add(deleteCustomer);
        }

      
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(220,20,1160,760);
        contentPanel.add(totalAccountsCard);
        contentPanel.add(savings);
        contentPanel.add(current);
        contentPanel.add(deposits);
        contentPanel.add(withdrawals);
        contentPanel.add(transactions);

        add(contentPanel);

        if(role.equals("ADMIN"))
        {
            if(createAdmin != null)
            {
                createAdmin.setVisible(false);
            }
            if(deleteCustomer != null)
            {
                deleteCustomer.setVisible(false);
            }
        }
        setVisible(true);
    }
    
    private JPanel createCard(String title, String value, String subtitle, Color bgColor)
    {
        JPanel card = new JPanel();
        card.setBackground(bgColor);
        card.setLayout(null);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(10, 10, 200, 20);
        card.add(titleLabel);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setBounds(10, 35, 200, 30);
        card.add(valueLabel);
        
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setBounds(10, 70, 200, 20);
        card.add(subtitleLabel);
        
        return card;

       
    }

    private void loadDashboardData()
    {
        try
        {
            DBConnection conn = new DBConnection();
            Connection c = conn.con;

            // Total Accounts
            PreparedStatement ps1 =
                c.prepareStatement(
                    "SELECT COUNT(*) FROM SIGNUP"
                );

            ResultSet rs1 = ps1.executeQuery();

            if(rs1.next())
            {
                totalAccounts = rs1.getInt(1);
            }

            // Savings Accounts
            PreparedStatement ps2 =
                c.prepareStatement(
                    "SELECT COUNT(*) FROM ACCOUNT_DETAILS WHERE ACCOUNT_TYPE='Savings'"
                );

            ResultSet rs2 = ps2.executeQuery();

            if(rs2.next())
            {
                savingsAccounts = rs2.getInt(1);
            }

            // Current Accounts
            PreparedStatement ps3 =
                c.prepareStatement(
                    "SELECT COUNT(*) FROM ACCOUNT_DETAILS WHERE ACCOUNT_TYPE='Current'"
                );

            ResultSet rs3 = ps3.executeQuery();

            if(rs3.next())
            {
                currentAccounts = rs3.getInt(1);
            }

            // Total Deposits
            PreparedStatement ps4 =
                c.prepareStatement(
                    "SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE USE_TYPE='Deposit'"
                );

            ResultSet rs4 = ps4.executeQuery();

            if(rs4.next())
            {
                totalDeposits = rs4.getLong(1);
            }

            // Total Withdrawals
            PreparedStatement ps5 =
                c.prepareStatement(
                    "SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE USE_TYPE='Withdraw'"
                );

            ResultSet rs5 = ps5.executeQuery();

            if(rs5.next())
            {
                totalWithdrawals = rs5.getLong(1);
            }

        // Total Transactions
        PreparedStatement ps6 =
            c.prepareStatement(
                "SELECT COUNT(*) FROM TRANSACTIONS"
            );

            ResultSet rs6 = ps6.executeQuery();

            if(rs6.next())
            {
                totalTransactions = rs6.getInt(1);
            }

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
        if(ae.getSource()==customersBtn)
        {
                new ViewCustomers();
        }
        if(ae.getSource()==statisticsBtn)
        {
            new StatisticsReport();
        }
        if(ae.getSource()==logoutBtn)
        {
            System.exit(0);
        }
       if(ae.getSource() == createAdmin)
        {
            new CreateAdmin();
        }
        else if(ae.getSource() == deleteCustomer)
        {
             new DeleteCustomer();
        }
    }

    public static void main(String []args)
    {
        new AdminDashboard("SUPER_ADMIN");
    }
}
