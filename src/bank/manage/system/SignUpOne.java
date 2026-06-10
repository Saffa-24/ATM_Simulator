package bank.manage.system;

import javax.swing.*;
import java.awt.*;
//import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignUpOne extends JFrame implements ActionListener {

    JLabel appNo, personalDetails;
    JLabel religionLabel, categoryLabel, educationLabel;
    JLabel occupationLabel, panLabel, aadhaarLabel,seniorLabel;
    JLabel existingLabel;
    JRadioButton existingYes, existingNo;

    JRadioButton seniorYes,seniorNo;
    JComboBox<String> religionBox;
    JComboBox<String> CategoryBox;
    JComboBox<String> EducationBox;
    JComboBox<String> OccupationBox;
    JTextField panField;
    JTextField aadhaarField;

    String formno;
    JButton nextButton;

    public SignUpOne(String formno) {
        this.formno = formno;

        setTitle("Application Form Page-2");
        setSize(850, 800);
        setLayout(null);

        ImageIcon i1 = new ImageIcon("C:/Users/saffa/OneDrive/Desktop/BankManagementSystem/src/ICONS/logo.jpg");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(50, 40, 100, 100);
        add(label);

        getContentPane().setBackground(Color.WHITE);
       
    
        appNo = new JLabel("APPLICATION FORM NO . "+ formno);
        //appNo.setFont(new Font("Times New Roman", Font.BOLD, 15));
        appNo.setBounds(600, 30, 500, 40);
        add(appNo);

      
        personalDetails = new JLabel("Page 2 : Personal Details");
       // personalDetails.setFont(new Font("Times New Roman", Font.BOLD, 24));
        personalDetails.setBounds(250, 80, 350, 30);
        add(personalDetails);

      
        religionLabel = new JLabel("Religion:");
        //religionLabel.setFont(new Font("Arial", Font.BOLD, 15));
        religionLabel.setBounds(100, 150, 150, 30);
        add(religionLabel);

       String[] religions = {
            "Hindu",
            "Muslim",
            "Christian",
            "Sikh",
            "Buddhist",
            "Other"
        };
        religionBox = new JComboBox<>(religions);
        religionBox.setBounds(250,150,250,30);
        add(religionBox);

      
        categoryLabel = new JLabel("Category:");
        //categoryLabel.setFont(new Font("Arial", Font.BOLD, 15));
        categoryLabel.setBounds(100, 200, 150, 30);
        add(categoryLabel);

        String[] categories = {
            "General",
            "OBC",
            "SC",
            "ST",
            "Other"
        };

        CategoryBox = new JComboBox<>(categories);
        CategoryBox.setBounds(250,200,250,30);
        add(CategoryBox);


        educationLabel = new JLabel("Education:");
        //educationLabel.setFont(new Font("Arial", Font.BOLD, 15));
        educationLabel.setBounds(100, 250, 150, 30);
        add(educationLabel);

       String[] education = {
                "Non-Graduate",
                "Graduate",
                "Post-Graduate",
                "Doctorate",
                "Other"
            };

        EducationBox = new JComboBox<>(education);
        EducationBox.setBounds(250,250,250,30);
        add(EducationBox);


        occupationLabel = new JLabel("Occupation:");
        //occupationLabel.setFont(new Font("Arial", Font.BOLD, 15));
        occupationLabel.setBounds(100, 300, 150, 30);
        add(occupationLabel);
        
        String[]Occupation={
            "Salaried",
            "Unsalaried",
            "Part time",
            "Online"
        };
        OccupationBox = new JComboBox<>(Occupation);
        OccupationBox.setBounds(250,300,250,30);
        add(OccupationBox);
        
      
        panLabel = new JLabel("PAN Number:");
        //panLabel.setFont(new Font("Arial", Font.BOLD,15));
        panLabel.setBounds(100, 350, 150, 30);
        add(panLabel);

        panField = new JTextField();
        panField.setBounds(250, 350, 250, 30);
        add(panField);

      
        aadhaarLabel = new JLabel("Aadhaar Number:");
        //aadhaarLabel.setFont(new Font("Arial", Font.BOLD, 15));
        aadhaarLabel.setBounds(100, 400, 180, 30);
        add(aadhaarLabel);

        aadhaarField = new JTextField();
        aadhaarField.setBounds(250, 400, 250, 30);
        add(aadhaarField);
        seniorLabel = new JLabel("Senior Citizen:");
        //seniorLabel.setFont(new Font("Arial", Font.BOLD, 15));
        seniorLabel.setBounds(100, 450, 150, 30);
        add(seniorLabel);

        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(250, 450, 80, 30);
        seniorYes.setBackground(Color.WHITE);
        add(seniorYes);

        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(350, 450, 80, 30);
        seniorNo.setBackground(Color.WHITE);
        add(seniorNo);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);


        existingLabel = new JLabel("Existing Account:");
       // existingLabel.setFont(new Font("Arial", Font.BOLD, 15));
        existingLabel.setBounds(100, 500, 180, 30);
        add(existingLabel);

        existingYes = new JRadioButton("Yes");
        existingYes.setBounds(250, 500, 80, 30);
        existingYes.setBackground(Color.WHITE);
        add(existingYes);

        existingNo = new JRadioButton("No");
        existingNo.setBounds(350, 500, 80, 30);
        existingNo.setBackground(Color.WHITE);
        add(existingNo);

        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(existingYes);
        existingGroup.add(existingNo);

        
        nextButton = new JButton("NEXT");
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setBounds(350, 600, 120, 40);
        nextButton.addActionListener(this);
        add(nextButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
       
       String religion =
        (String) religionBox.getSelectedItem();

        String category =
                (String) CategoryBox.getSelectedItem();

        String education =
                (String) EducationBox.getSelectedItem();

        String occupation =
                (String) OccupationBox.getSelectedItem();

        String pan =
                panField.getText();

        String aadhaar =
                aadhaarField.getText();

        String seniorCitizen = null;
        if(seniorYes.isSelected()) {
            seniorCitizen = "Yes";
        }
        else {
            seniorCitizen = "No";
        }

        String existingAccount = null;

        if(existingYes.isSelected()) {
            existingAccount = "Yes";
        }
        else {
            existingAccount = "No";
        }

        DBConnection con = new DBConnection();

        String createTable =
            "CREATE TABLE IF NOT EXISTS ADDITIONAL_DETAILS (" +
            "FORMNO VARCHAR(20) PRIMARY KEY," +
            "RELIGION VARCHAR(50)," +
            "CATEGORY VARCHAR(50)," +
            "EDUCATION VARCHAR(100)," +
            "OCCUPATION VARCHAR(100)," +
            "PAN VARCHAR(20)," +
            "AADHAAR VARCHAR(20)," +
            "SENIOR_CITIZEN VARCHAR(10)," +
            "EXISTING_ACCOUNT VARCHAR(10)" +
            ")";

        try {
            con.s.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query =
        "INSERT INTO ADDITIONAL_DETAILS " +
        "(FORMNO,RELIGION,CATEGORY,EDUCATION,OCCUPATION,PAN,AADHAAR,SENIOR_CITIZEN,EXISTING_ACCOUNT) " +
        "VALUES('" + formno + "','" +
        religion + "','" +
        category + "','" +
        education + "','" +
        occupation + "','" +
        pan + "','" +
        aadhaar + "','" +
        seniorCitizen + "','" +
        existingAccount + "')";
        
        try {
            con.s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(
        this,
        "Details Saved Successfully");
        
        setVisible(false);
        new SignUpTwo(formno);
    }
}
