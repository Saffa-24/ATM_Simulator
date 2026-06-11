package  bank.manage.system;

import javax.swing.*;



import java.awt.*;
import java.sql.ResultSet;


public class CustomerProfile extends JFrame
{
    JLabel  Profile,appNo, App, personalHeading,additionalHeading;
    JLabel nameLabel, fnameLabel, dobLabel;
    JLabel name, fname, dob;
    JLabel addressLabel, address, stateLabel;
    JLabel religionLabel, categoryLabel, educationLabel;
    JLabel occupationLabel, panLabel, aadhaarLabel,seniorLabel;
    JLabel existingLabel;
    JLabel details, type, CardValue, PINValue;

    public CustomerProfile(String Cardno)
    {
        String appStr = "";
        String nameStr = "";
        String dobStr = "";
        String addressStr = "";
        String fnameStr = "";
        String photoPath = "";

        setLayout(null);
        setSize(800,850);
        Profile=new JLabel("Profile of the Account Holder");
        Profile.setBounds(300,25,300,30);
        Profile.setFont(new Font("Arial", Font.BOLD, 16));
        Profile.setForeground(Color.BLACK);

        personalHeading =
        new JLabel("PERSONAL DETAILS");

        personalHeading.setBounds(100,350,250,30);
        personalHeading.setFont(
                new Font("Arial",Font.BOLD,16));

        add(personalHeading);

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
            appStr=result.getString("FORMNO");
            nameStr= result.getString("NAME");
            dobStr=result.getString("DOB");
            addressStr=result.getString("ADDRESS");
            fnameStr=result.getString("FNAME");
            photoPath = result.getString("PHOTO_PATH");
            String gender = result.getString("GENDER");
            String email = result.getString("EMAIL");
            String state = result.getString("STATE");
            String pincode = result.getString("PINCODE");

            String religion = result.getString("RELIGION");
            String category = result.getString("CATEGORY");
            String education = result.getString("EDUCATION");
            String occupation = result.getString("OCCUPATION");
            String pan = result.getString("PAN");
            String aadhaar = result.getString("AADHAAR");
            String senior = result.getString("SENIOR_CITIZEN");

            String accountType = result.getString("ACCOUNT_TYPE");
            String cardNo = result.getString("CARDNUMBER");

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

        add(photoLabel);


        App=new JLabel(appStr);
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

        additionalHeading.setBounds(100,650,250,30);
        additionalHeading.setFont(
                new Font("Arial",Font.BOLD,16));

        add(additionalHeading);




        add(name);
        add(fname);
        add(address);
        add(dob);
        add(App);
        add(fnameLabel);
        add(nameLabel);
        add(addressLabel);
        add(dobLabel);
        add(Profile);
        add(appNo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    public static void main(String[]args)
    {
        new CustomerProfile("504093172002918975");
    }
}