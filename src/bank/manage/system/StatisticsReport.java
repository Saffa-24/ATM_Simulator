package bank.manage.system;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import javax.swing.*;
import java.sql.*;

public class StatisticsReport extends JFrame implements ActionListener
{
JLabel totalAccountsLabel;
JLabel savingsLabel;
JLabel currentLabel;
JLabel depositsLabel;
JLabel withdrawalsLabel;
JLabel netHoldingsLabel;
JLabel largestDepositLabel;
JLabel largestWithdrawalLabel;

JButton generatePdf;

public StatisticsReport()
{
    setTitle("Statistics Report");
    setSize(900,600);
    setLayout(null);

    JLabel heading =
        new JLabel("BANK STATISTICS REPORT");

    heading.setBounds(320,20,300,30);

    add(heading);

    totalAccountsLabel =
        new JLabel("Total Accounts : 0");
    totalAccountsLabel.setBounds(
        50,100,300,30);
    add(totalAccountsLabel);

    savingsLabel =
        new JLabel("Savings Accounts : 0");
    savingsLabel.setBounds(
        50,140,300,30);
    add(savingsLabel);

    currentLabel =
        new JLabel("Current Accounts : 0");
    currentLabel.setBounds(
        50,180,300,30);
    add(currentLabel);

    depositsLabel =
        new JLabel("Total Deposits : ₹0");
    depositsLabel.setBounds(
        50,220,300,30);
    add(depositsLabel);

    withdrawalsLabel =
        new JLabel("Total Withdrawals : ₹0");
    withdrawalsLabel.setBounds(
        50,260,300,30);
    add(withdrawalsLabel);

    netHoldingsLabel =
        new JLabel("Net Holdings : ₹0");
    netHoldingsLabel.setBounds(
        50,300,300,30);
    add(netHoldingsLabel);

    largestDepositLabel =
        new JLabel("Largest Deposit : ₹0");
    largestDepositLabel.setBounds(
        50,340,300,30);
    add(largestDepositLabel);

    largestWithdrawalLabel =
        new JLabel("Largest Withdrawal : ₹0");
    largestWithdrawalLabel.setBounds(
        50,380,300,30);
    add(largestWithdrawalLabel);

    generatePdf =
        new JButton("Generate PDF");

    generatePdf.setBounds(
        350,500,150,40);
    generatePdf.addActionListener(this);

    add(generatePdf);

    loadStatistics();

    setLocationRelativeTo(null);
    setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE
    );
    setVisible(true);
}

private void loadStatistics()
{
    try
    {
        DBConnection conn =
            new DBConnection();

        Connection c =
            conn.con;

        int totalAccounts = 0;
        int savingsAccounts = 0;
        int currentAccounts = 0;

        long totalDeposits = 0;
        long totalWithdrawals = 0;

        long largestDeposit = 0;
        long largestWithdrawal = 0;

        PreparedStatement ps1 =
            c.prepareStatement(
                "SELECT COUNT(*) FROM SIGNUP"
            );

        ResultSet rs1 =
            ps1.executeQuery();

        if(rs1.next())
        {
            totalAccounts =
                rs1.getInt(1);
        }

        PreparedStatement ps2 =
            c.prepareStatement(
                "SELECT COUNT(*) FROM ACCOUNT_DETAILS WHERE ACCOUNT_TYPE='Savings'"
            );

        ResultSet rs2 =
            ps2.executeQuery();

        if(rs2.next())
        {
            savingsAccounts =
                rs2.getInt(1);
        }

        PreparedStatement ps3 =
            c.prepareStatement(
                "SELECT COUNT(*) FROM ACCOUNT_DETAILS WHERE ACCOUNT_TYPE='Current'"
            );

        ResultSet rs3 =
            ps3.executeQuery();

        if(rs3.next())
        {
            currentAccounts =
                rs3.getInt(1);
        }

        PreparedStatement ps4 =
            c.prepareStatement(
                "SELECT IFNULL(SUM(AMOUNT),0) FROM TRANSACTIONS WHERE USE_TYPE='Deposit'"
            );

        ResultSet rs4 =
            ps4.executeQuery();

        if(rs4.next())
        {
            totalDeposits =
                rs4.getLong(1);
        }

        PreparedStatement ps5 =
            c.prepareStatement(
                "SELECT IFNULL(SUM(AMOUNT),0) FROM TRANSACTIONS WHERE USE_TYPE='Withdraw'"
            );

        ResultSet rs5 =
            ps5.executeQuery();

        if(rs5.next())
        {
            totalWithdrawals =
                rs5.getLong(1);
        }

        PreparedStatement ps6 =
            c.prepareStatement(
                "SELECT IFNULL(MAX(AMOUNT),0) FROM TRANSACTIONS WHERE USE_TYPE='Deposit'"
            );

        ResultSet rs6 =
            ps6.executeQuery();

        if(rs6.next())
        {
            largestDeposit =
                rs6.getLong(1);
        }

        PreparedStatement ps7 =
            c.prepareStatement(
                "SELECT IFNULL(MAX(AMOUNT),0) FROM TRANSACTIONS WHERE USE_TYPE='Withdraw'"
            );

        ResultSet rs7 =
            ps7.executeQuery();

        if(rs7.next())
        {
            largestWithdrawal =
                rs7.getLong(1);
        }

        long netHoldings =
            totalDeposits -
            totalWithdrawals;

        totalAccountsLabel.setText(
            "Total Accounts : " +
            totalAccounts
        );

        savingsLabel.setText(
            "Savings Accounts : " +
            savingsAccounts
        );

        currentLabel.setText(
            "Current Accounts : " +
            currentAccounts
        );

        depositsLabel.setText(
            "Total Deposits : ₹" +
            totalDeposits
        );

        withdrawalsLabel.setText(
            "Total Withdrawals : ₹" +
            totalWithdrawals
        );

        netHoldingsLabel.setText(
            "Net Holdings : ₹" +
            netHoldings
        );

        largestDepositLabel.setText(
            "Largest Deposit : ₹" +
            largestDeposit
        );

        largestWithdrawalLabel.setText(
            "Largest Withdrawal : ₹" +
            largestWithdrawal
        );

        c.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent ae)
    {
        if(ae.getSource() == generatePdf)
        {
            try
            {
                Document document =
                new Document();

            String fileName =
                "Statistics_Report.pdf";

            PdfWriter.getInstance(
                document,
                new FileOutputStream(
                    fileName
                )
            );

            document.open();

                document.add(
                    new Paragraph(
                        "BANK STATISTICS REPORT"
                    )
                );

                document.add(
                    new Paragraph(" ")
                );

                document.add(
                    new Paragraph(
                        totalAccountsLabel.getText()
                    )
                );

                document.add(
                    new Paragraph(
                        savingsLabel.getText()
                    )
                );

                document.add(
                    new Paragraph(
                        currentLabel.getText()
                    )
                );

                document.add(
                    new Paragraph(
                        depositsLabel.getText()
                    )
                );

                document.add(
                    new Paragraph(
                        withdrawalsLabel.getText()
                    )
                );

                document.add(
                    new Paragraph(
                        netHoldingsLabel.getText()
                    )
                );

                document.add(
                    new Paragraph(
                        largestDepositLabel.getText()
                    )
                );

                document.add(
                    new Paragraph(
                        largestWithdrawalLabel.getText()
                    )
                );

                document.close();

               java.io.File pdf =
                new java.io.File(fileName);

            JOptionPane.showMessageDialog(
                this,
                "PDF Generated Successfully\n\nLocation:\n" +
                pdf.getAbsolutePath()
            );
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
    }

    public static void main(String[] args)
    {
        new StatisticsReport();
    }
}