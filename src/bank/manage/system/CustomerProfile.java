package  bank.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerProfile extends JFrame implements ActionListener
{
    JLabel  Profile,appNo, App, personalHeading,additionalHeading;
    JLabel nameLabel, fnameLabel, dobLabel;
    JLabel name, fname, dob;
    JLabel addressLabel, address, stateLabel;
    JLabel religionLabel, categoryLabel, educationLabel;
    JLabel occupationLabel, panLabel, aadhaarLabel,seniorLabel;
    JLabel existingLabel;
    JLabel details, type, CardValue, PINValue;
    JButton continueButton, EditButton;
    String formno;
    String cardno;
   

    public CustomerProfile(String Cardno)
    {
        cardno = Cardno;
        String nameStr = "";
        String dobStr = "";
        String addressStr = "";
        String fnameStr = "";
        String photoPath = "";
        String religion = "";
        String category = "";
        String education = "";
        String occupation = "";
        String pan = "";
        String aadhaar = "";
        String senior = "";
        String Exist="";
        String accountType = "";
        String cardNo = "";
        String Pin="";

        setLayout(null);
        setSize(900,700);

         JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(850, 1400));
        Profile=new JLabel("Profile of the Account Holder");
        Profile.setBounds(300,10,300,30);
        Profile.setFont(new Font("Arial", Font.BOLD, 16));
        Profile.setForeground(Color.BLACK);

        personalHeading =
        new JLabel("PERSONAL DETAILS");

        personalHeading.setBounds(100,75,250,30);
        personalHeading.setFont(
                new Font("Arial",Font.BOLD,16));

        panel.add(personalHeading);

        appNo=new JLabel("Application\nNO :");
        appNo.setBounds(100,100,100,30);
        appNo.setFont(new Font("Arial", Font.PLAIN, 14));
        appNo.setForeground(Color.BLACK);

        nameLabel=new JLabel("Name :");
        nameLabel.setBounds(100,150,100,30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setForeground(Color.BLACK);
       

        dobLabel=new JLabel("Dob");
        dobLabel.setBounds(100,200,100,30);
        dobLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dobLabel.setForeground(Color.BLACK);

        addressLabel=new JLabel("Address :");
        addressLabel.setBounds(100,250,100,30);
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addressLabel.setForeground(Color.BLACK);

        fnameLabel=new JLabel("Father`s Name");
        fnameLabel.setBounds(100,300,100,30);
        fnameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fnameLabel.setForeground(Color.BLACK);


        try 
        {
            DBConnection con=new DBConnection();
            String query =
        "SELECT * FROM SIGNUP s " +
        "JOIN ADDITIONAL_DETAILS a ON s.FORMNO = a.FORMNO " +
        "JOIN ACCOUNT_DETAILS ac ON s.FORMNO = ac.FORMNO " +
        "WHERE ac.CARD_NUMBER = '" + Cardno + "'";
        ResultSet result=con.s.executeQuery(query);
        if(result.next())

        {
            formno = result.getString("FORMNO");
            nameStr= result.getString("NAME");
            dobStr=result.getString("DOB");
            addressStr=result.getString("ADDRESS");
            fnameStr=result.getString("FNAME");
            photoPath = result.getString("PHOTO_PATH");
           

            religion = result.getString("RELIGION");
            category = result.getString("CATEGORY");
            education = result.getString("EDUCATION");
            occupation = result.getString("OCCUPATION");
            pan = result.getString("PAN");
            aadhaar = result.getString("AADHAAR");
            senior = result.getString("SENIOR_CITIZEN");
            Exist = result.getString("EXISTING_ACCOUNT");

            accountType = result.getString("ACCOUNT_TYPE");
            cardNo = result.getString("CARD_NUMBER");
            Pin=result.getString("PIN_NUMBER");
            result.close();
            con.s.close();
            con.con.close();
        }
        }
        catch(Exception e){
           e. printStackTrace();
        }
        ImageIcon icon = new ImageIcon(photoPath);

        Image img = icon.getImage().getScaledInstance(
                120,
                120,
                Image.SCALE_SMOOTH);

        JLabel photoLabel =
                new JLabel(new ImageIcon(img));

        photoLabel.setBounds(600,80,120,120);

        panel.add(photoLabel);


        App=new JLabel(formno);
        App.setBounds(200,100,100,30);
        App.setFont(new Font("Arial", Font.PLAIN, 14));
        App.setForeground(Color.BLACK);

        name=new JLabel(nameStr);
        name.setBounds(200,150,100,30);
        name.setFont(new Font("Arial", Font.PLAIN, 14));
        name.setForeground(Color.BLACK);
       

        dob=new JLabel(dobStr);
        dob.setBounds(200,200,100,30);
        dob.setFont(new Font("Arial", Font.PLAIN, 14));
        dob.setForeground(Color.BLACK);

        address=new JLabel(addressStr);
        address.setBounds(200,250,150,30);
        address.setFont(new Font("Arial", Font.PLAIN, 14));
        address.setForeground(Color.BLACK);

        fname=new JLabel(fnameStr);
        fname.setBounds(200,300,150,30);
        fname.setFont(new Font("Arial", Font.PLAIN, 14));
        fname.setForeground(Color.BLACK);

         additionalHeading =
        new JLabel("ADDITIONAL DETAILS");

        additionalHeading.setBounds(100,350,250,30);
        additionalHeading.setFont(
                new Font("Arial",Font.BOLD,16));

        panel.add(additionalHeading);


        JLabel religionLabel = new JLabel("Religion :");
        religionLabel.setBounds(100,400,150,30);
        panel.add(religionLabel);

        JLabel religionValue = new JLabel(religion);
        religionValue.setBounds(250,400,250,30);
        panel.add(religionValue);

        JLabel categoryLabel = new JLabel("Category :");
        categoryLabel.setBounds(100,450,150,30);
        panel.add(categoryLabel);

        JLabel categoryValue = new JLabel(category);
        categoryValue.setBounds(250,450,250,30);
        panel.add(categoryValue);

        JLabel educationLabel = new JLabel("Education :");
        educationLabel.setBounds(100,500,150,30);
        panel.add(educationLabel);

        JLabel educationValue = new JLabel(education);
        educationValue.setBounds(250,500,250,30);
        panel.add(educationValue);

        JLabel occupationLabel = new JLabel("Occupation :");
        occupationLabel.setBounds(100,550,150,30);
        panel.add(occupationLabel);

        JLabel occupationValue = new JLabel(occupation);
        occupationValue.setBounds(250,550,250,30);
        panel.add(occupationValue);

        JLabel panLabel = new JLabel("PAN Number :");
        panLabel.setBounds(100,600,150,30);
        panel.add(panLabel);

        JLabel panValue = new JLabel(pan);
        panValue.setBounds(250,600,250,30);
        panel.add(panValue);

        JLabel aadhaarLabel = new JLabel("Aadhaar Number :");
        aadhaarLabel.setBounds(100,650,150,30);
        panel.add(aadhaarLabel);

        JLabel aadhaarValue = new JLabel(aadhaar);
        aadhaarValue.setBounds(250,650,250,30);
        panel.add(aadhaarValue);

        JLabel seniorLabel = new JLabel("Senior Citizen :");
        seniorLabel.setBounds(100,700,150,30);
        panel.add(seniorLabel);

        JLabel seniorValue = new JLabel(senior);
        seniorValue.setBounds(250,700,250,30);
        panel.add(seniorValue);

        JLabel existingLabel = new JLabel("Existing Account :");
        existingLabel.setBounds(100,750,150,30);
        panel.add(existingLabel);

        JLabel existingValue = new JLabel(Exist);
        existingValue.setBounds(250,750,250,30);
        panel.add(existingValue);

        JLabel accountHeading =
        new JLabel("ACCOUNT DETAILS");

        accountHeading.setBounds(100,850,250,30);
        accountHeading.setFont(
                new Font("Arial",Font.BOLD,16));
        panel.add(accountHeading);
        JLabel accountTypeLabel = new JLabel("Account Type :");
        accountTypeLabel.setBounds(100,900,150,30);
        panel.add(accountTypeLabel);

        JLabel accountTypeValue = new JLabel(accountType);
        accountTypeValue.setBounds(250,900,250,30);
        panel.add(accountTypeValue);

        JLabel cardLabel = new JLabel("Card Number :");
        cardLabel.setBounds(100,950,150,30);
        panel.add(cardLabel);

        JLabel cardValue = new JLabel(cardNo);
        cardValue.setBounds(250,950,300,30);
        panel.add(cardValue);

        JLabel pinLabel = new JLabel("PIN :");
        pinLabel.setBounds(100,1000,150,30);
        panel.add(pinLabel);

        JLabel pinValue = new JLabel(Pin);
        pinValue.setBounds(250,1000,250,30);
        panel.add(pinValue);

        continueButton = new JButton("CONTINUE TO ATM");
        continueButton.setBounds(250,1100,200,40);
        continueButton.addActionListener(this);
        panel.add(continueButton);

        EditButton = new JButton("EDIT PROFILE");
        EditButton.setBounds(500,1100,150,40);
        EditButton.addActionListener(this);
        panel.add(EditButton);



         panel.add(name);
         panel.add(fname);
         panel.add(address);
         panel.add(dob);
         panel.add(App);
         panel.add(fnameLabel);
         panel.add(nameLabel);
         panel.add(addressLabel);
         panel.add(dobLabel);
         panel.add(Profile);
         panel.add(appNo);
        
       

         JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        

        setContentPane(scrollPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    public  void actionPerformed(ActionEvent ae )
    {
        if(ae.getSource()==continueButton)

        try{
            new Transaction(cardno);
            dispose();
        }
          catch (Exception e)
        {
            JOptionPane.showMessageDialog(this,e);
        }
      
        else if(ae.getSource()==EditButton)
        {
            try{
            new EditProfile(formno);
            dispose();
        }
          catch (Exception e)
        {
            JOptionPane.showMessageDialog(this,e);
        }
      

        }
    }
}