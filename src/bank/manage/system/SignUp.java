package bank.manage.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import com.toedter.calendar.JDateChooser;



public class SignUp extends JFrame implements ActionListener {

    JLabel appNo, personalDetails;
    JLabel nameLabel, fnameLabel, dobLabel;
    JLabel genderLabel, emailLabel, maritalLabel;
    JLabel addressLabel, pincodeLabel, stateLabel;

    JTextField nameField, fnameField;
    JTextField emailField, addressField;
    JTextField pincodeField, stateField;
    JDateChooser dateChooser;
    JButton uploadPhoto;
    JLabel photoLabel;
    String formno;
    String photoPath = "";

    JRadioButton male, female;
    JRadioButton married, unmarried, other;

    JButton next;

    SignUp() {

        setTitle("Application Form");

        setLayout(null);

        ImageIcon i1 = new ImageIcon("C:/Users/saffa/OneDrive/Desktop/BankManagementSystem/src/ICONS/logo.jpg");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(50, 35, 100, 100);
        add(label);


        Random random = new Random();
        formno = String.valueOf(Math.abs(random.nextLong() % 9000L) + 1000L);

        appNo = new JLabel("APPLICATION FORM NO. " + formno);
        appNo.setFont(new Font("Raleway", Font.BOLD, 28));
        appNo.setBounds(200, 20, 500, 40);
        add(appNo);

        personalDetails = new JLabel("Page 1 : Personal Details");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personalDetails.setBounds(220, 70, 300, 30);
        add(personalDetails);

    
        nameLabel = new JLabel("Name :");
        nameLabel.setBounds(100, 130, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(250, 130, 250, 30);
        add(nameField);

       
        fnameLabel = new JLabel("Father's Name :");
        fnameLabel.setBounds(100, 180, 120, 30);
        add(fnameLabel);

        fnameField = new JTextField();
        fnameField.setBounds(250, 180, 250, 30);
        add(fnameField);

        
        dobLabel = new JLabel("Date Of Birth :");
        dobLabel.setBounds(100, 230, 120, 30);
        add(dobLabel);

       dateChooser = new JDateChooser();
       dateChooser.setBounds( 250, 230, 250, 30);
       add(dateChooser);
        // Gender
        genderLabel = new JLabel("Gender :");
        genderLabel.setBounds(100, 280, 100, 30);
        add(genderLabel);

        male = new JRadioButton("Male");
        male.setBounds(250, 280, 80, 30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(350, 280, 100, 30);
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

      
        emailLabel = new JLabel("Email :");
        emailLabel.setBounds(100, 330, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 330, 250, 30);
        add(emailField);

       
        maritalLabel = new JLabel("Marital Status :");
        maritalLabel.setBounds(100, 380, 120, 30);
        add(maritalLabel);

        married = new JRadioButton("Married");
        married.setBounds(250, 380, 90, 30);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(350, 380, 100, 30);

        other = new JRadioButton("Other");
        other.setBounds(470, 380, 80, 30);

        married.setBackground(Color.WHITE);
        unmarried.setBackground(Color.WHITE);
        other.setBackground(Color.WHITE);

        add(married);
        add(unmarried);
        add(other);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);

  
        addressLabel = new JLabel("Address :");
        addressLabel.setBounds(100, 430, 100, 30);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(250, 430, 250, 30);
        add(addressField);

       
        stateLabel = new JLabel("State :");
        stateLabel.setBounds(100, 480, 100, 30);
        add(stateLabel);

        stateField = new JTextField();
        stateField.setBounds(250, 480, 250, 30);
        add(stateField);

       
        pincodeLabel = new JLabel("Pincode :");
        pincodeLabel.setBounds(100, 530, 100, 30);
        add(pincodeLabel);

        pincodeField = new JTextField();
        pincodeField.setBounds(250, 530, 250, 30);
        add(pincodeField);

       
        next = new JButton("NEXT");
        next.setBounds(400, 600, 100, 35);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        photoLabel = new JLabel();

        photoLabel.setBounds(550,150,150,150);

        photoLabel.setBorder(
        BorderFactory.createLineBorder(Color.BLACK));

        add(photoLabel);

        uploadPhoto = new JButton("Upload Photo");

        uploadPhoto.setBounds(550,320,150,30);

        add(uploadPhoto);

        uploadPhoto.addActionListener(e -> {

            JFileChooser chooser =
                    new JFileChooser();

            int result =
                    chooser.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION) {

                photoPath =chooser.getSelectedFile()
                                .getAbsolutePath();
                ImageIcon icon =
                        new ImageIcon(photoPath);

                Image image =
                        icon.getImage()
                            .getScaledInstance(
                                    150,
                                    150,
                                    Image.SCALE_SMOOTH);

                photoLabel.setIcon(
                        new ImageIcon(image));
            }
        });



        setSize(800, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String formno = this.formno;
        String name = nameField.getText();
        String fname = fnameField.getText();
        String DOB = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender=null;
        if(male.isSelected())
        {
             gender="Male";
        }
        else if(female.isSelected())
        {
             gender="Female";
        }
        String status=null;
        if(married.isSelected())
        {
            status="Married";
        }
        else if (unmarried.isSelected()) {
            status="Unmarried";    
        }
        else if(other.isSelected())
        {
            status="Others";
        }
        String email=emailField.getText();
        String address=addressField.getText();
        String state=stateField.getText();
        String pincode=pincodeField.getText();
        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(this, "Name is required");
            }
            else 
            {
                DBConnection con=new DBConnection();
                    
                    String query =
                    "INSERT INTO SIGNUP " +
                    "(FORMNO, NAME, FNAME, DOB, GENDER, EMAIL, MARITAL_STATUS, ADDRESS, STATE, PINCODE,PHOTO_PATH) " +
                    "VALUES('" + formno + "','" +
                    name + "','" +
                    fname + "','" +
                    DOB + "','" +
                    gender + "','" +
                    email + "','" +
                    status + "','" +
                    address + "','"+
                    state + "','" +
                    pincode + "','"+photoPath+"')";
                    System.out.println(query);
                    con.s.executeUpdate(query);

                    JOptionPane.showMessageDialog( this, "Application submitted successfully");
                    new SignUpOne(formno);
                    dispose();
            }
           
            
        } catch (Exception e) {
    e.printStackTrace();
}
    }
        
   /*  public static void main(String[] args) {
        new SignUp();
    }*/
}