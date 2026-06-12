package bank.manage.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;

public class EditProfile extends JFrame implements ActionListener {

        JTextField nameField,fnameField,emailField,
        addressField,stateField,pincodeField;
        JComboBox<String> religionField;
        JComboBox<String> categoryField;
        JComboBox<String> educationField;
        JComboBox<String> occupationField;

        JTextField panField;
        JTextField aadhaarField;

        JComboBox<String> seniorCitizenField;
        JComboBox<String> existingAccountField;

    JButton uploadButton,saveButton;

        JTextField accountTypeField;

    JLabel photoLabel;

    String formno;
    String photoPath="";

    public EditProfile(String formno) {

        this.formno=formno;

        setLayout(null);
        setSize(900,700);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(850, 1400));

        JLabel heading=
                new JLabel("EDIT PROFILE");

        heading.setBounds(350,30,250,30);
        heading.setFont(
                new Font("Arial",
                        Font.BOLD,20));

        panel.add(heading);

        JLabel nameLabel=
                new JLabel("Name");

        nameLabel.setBounds(100,100,150,30);
        panel.add(nameLabel);

        nameField=new JTextField();
        nameField.setBounds(250,100,250,30);
        panel.add(nameField);

        JLabel fnameLabel=
                new JLabel("Father Name");

        fnameLabel.setBounds(100,150,150,30);
        panel.add(fnameLabel);

        fnameField=new JTextField();
        fnameField.setBounds(250,150,250,30);
        panel.add(fnameField);

        JLabel emailLabel=
                new JLabel("Email");

        emailLabel.setBounds(100,200,150,30);
        panel.add(emailLabel);

        emailField=new JTextField();
        emailField.setBounds(250,200,250,30);
        panel.add(emailField);

        JLabel addressLabel=
                new JLabel("Address");

        addressLabel.setBounds(100,250,150,30);
        panel.add(addressLabel);

        addressField=new JTextField();
        addressField.setBounds(250,250,250,30);
        panel.add(addressField);

        JLabel stateLabel=
                new JLabel("State");

        stateLabel.setBounds(100,300,150,30);
        panel.add(stateLabel);

        stateField=new JTextField();
        stateField.setBounds(250,300,250,30);
        panel.add(stateField);

        JLabel pinLabel=
                new JLabel("Pincode");

        pinLabel.setBounds(100,350,150,30);
        panel.add(pinLabel);

        pincodeField=new JTextField();
        pincodeField.setBounds(250,350,250,30);
        panel.add(pincodeField);

        photoLabel=new JLabel();
        photoLabel.setBounds(600,100,150,150);
        panel.add(photoLabel);
        
        JLabel religion=new JLabel("Religion");
        religion.setBounds(100,600,250,30);
        panel.add(religion);

        religionField = new JComboBox<>(new String[]{
        "Hindu",
        "Muslim",
        "Christian",
        "Sikh",
        "Buddhist",
        "Other"
        });

        JLabel Category=new JLabel("Category");
        Category.setBounds(100,650,250,30);
        panel.add(Category);

        categoryField = new JComboBox<>(new String[]{
                "General",
                "OBC",
                "SC",
                "ST",
                "Other"
        });
        JLabel Education=new JLabel("Education");
        Education.setBounds(100,700,250,30);
        panel.add(Education);

        educationField = new JComboBox<>(new String[]{
                "Non-Graduate",
                "Graduate",
                "Post-Graduate",
                "Doctorate",
                "Other"
        });

        JLabel Occupation=new JLabel("Occupation");
        Occupation.setBounds(100,750,250,30);
        panel.add(Occupation);

        occupationField = new JComboBox<>(new String[]{
                "Salaried",
                "Unsalaried",
                "Part Time",
                "Online"
        });

        JLabel Senior=new JLabel("Senior Citizen");
        Senior.setBounds(100,900,250,30);
        panel.add(Senior);

        seniorCitizenField = new JComboBox<>(new String[]{
                "Yes",
                "No"
        });


        JLabel Exist=new JLabel("Existing Account");
        Exist.setBounds(100,950,250,30);
        panel.add(Exist);
        existingAccountField = new JComboBox<>(new String[]{
                "Yes",
                "No"
        });
        JLabel Pan=new JLabel("Pan Number");
        Pan.setBounds(100,800,250,30);
        panel.add(Pan);

        panField = new JTextField();

        JLabel adhaar=new JLabel("adhaar");
       adhaar.setBounds(100,850,250,30);
        panel.add(adhaar);
        aadhaarField = new JTextField();


        religionField.setBounds(250,600,250,30);
        categoryField.setBounds(250,650,250,30);
        educationField.setBounds(250,700,250,30);
        occupationField.setBounds(250,750,250,30);

        panField.setBounds(250,800,250,30);
        aadhaarField.setBounds(250,850,250,30);

        seniorCitizenField.setBounds(250,900,250,30);
        existingAccountField.setBounds(250,950,250,30);
        panel.add(religionField);
        panel.add(categoryField);
        panel.add(educationField);
        panel.add(occupationField);

        panel.add(panField);
        panel.add(aadhaarField);

        panel.add(seniorCitizenField);
        panel.add(existingAccountField);


        uploadButton=
                new JButton("Upload Photo");

        uploadButton.setBounds(580,280,180,30);
        uploadButton.addActionListener(this);
        panel.add(uploadButton);

        saveButton=
                new JButton("Save");

        saveButton.setBounds(300,1000,150,40);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        loadData();
        

        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setContentPane(scrollPane);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void loadData() {
        try {
            DBConnection con = new DBConnection();

            String query = "SELECT * FROM SIGNUP s " +
        "JOIN ADDITIONAL_DETAILS a ON s.FORMNO = a.FORMNO " +
        "JOIN ACCOUNT_DETAILS ac ON s.FORMNO = ac.FORMNO " +
        "WHERE ac.FORMNO= '" + formno + "'";

            ResultSet rs = con.s.executeQuery(query);

            if (rs.next()) {
                nameField.setText(rs.getString("NAME"));
                fnameField.setText(rs.getString("FNAME"));
                emailField.setText(rs.getString("EMAIL"));
                addressField.setText(rs.getString("ADDRESS"));
                stateField.setText(rs.getString("STATE"));
                pincodeField.setText(rs.getString("PINCODE"));

                religionField.setSelectedItem(
                        rs.getString("RELIGION"));

                categoryField.setSelectedItem(
                        rs.getString("CATEGORY"));

                educationField.setSelectedItem(
                        rs.getString("EDUCATION"));

                occupationField.setSelectedItem(
                        rs.getString("OCCUPATION"));

                panField.setText(
                        rs.getString("PAN"));

                aadhaarField.setText(
                        rs.getString("AADHAAR"));

                seniorCitizenField.setSelectedItem(
                        rs.getString("SENIOR_CITIZEN"));

                existingAccountField.setSelectedItem(
                        rs.getString("EXISTING_ACCOUNT"));

                photoPath = rs.getString("PHOTO_PATH");

                ImageIcon icon = new ImageIcon(photoPath);
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(img));

                rs.close();
                con.s.close();
                con.con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @Override
        public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == uploadButton) {
                        JFileChooser chooser = new JFileChooser();
                        int res = chooser.showOpenDialog(this);
                        if (res == JFileChooser.APPROVE_OPTION) {
                                File f = chooser.getSelectedFile();
                                photoPath = f.getAbsolutePath();
                                ImageIcon icon = new ImageIcon(photoPath);
                                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                                photoLabel.setIcon(new ImageIcon(img));

                        }
                } else if (ae.getSource() == saveButton) {
    try {

        DBConnection con = new DBConnection();

        // SIGNUP TABLE
        String query1 =
        "UPDATE SIGNUP SET " +
        "NAME='" + nameField.getText() + "'," +
        "FNAME='" + fnameField.getText() + "'," +
        "EMAIL='" + emailField.getText() + "'," +
        "ADDRESS='" + addressField.getText() + "'," +
        "STATE='" + stateField.getText() + "'," +
        "PINCODE='" + pincodeField.getText() + "'," +
        "PHOTO_PATH='" + photoPath + "' " +
        "WHERE FORMNO='" + formno + "'";

        con.s.executeUpdate(query1);

        // ADDITIONAL_DETAILS TABLE

        String religion =
                (String) religionField.getSelectedItem();

        String category =
                (String) categoryField.getSelectedItem();

        String education =
                (String) educationField.getSelectedItem();

        String occupation =
                (String) occupationField.getSelectedItem();

        String senior =
                (String) seniorCitizenField.getSelectedItem();

        String existing =
                (String) existingAccountField.getSelectedItem();

        String query2 =
        "UPDATE ADDITIONAL_DETAILS SET " +
        "RELIGION='" + religion + "'," +
        "CATEGORY='" + category + "'," +
        "EDUCATION='" + education + "'," +
        "OCCUPATION='" + occupation + "'," +
        "PAN='" + panField.getText() + "'," +
        "AADHAAR='" + aadhaarField.getText() + "'," +
        "SENIOR_CITIZEN='" + senior + "'," +
        "EXISTING_ACCOUNT='" + existing + "' " +
        "WHERE FORMNO='" + formno + "'";

        con.s.executeUpdate(query2);

        // ACCOUNT_DETAILS TABLE
        String query3 =
        "UPDATE ACCOUNT_DETAILS SET " +
        "ACCOUNT_TYPE='" +
        accountTypeField.getText() + "' " +
        "WHERE FORMNO='" + formno + "'";

        con.s.executeUpdate(query3);
        con.s.close();
        con.con.close();

        JOptionPane.showMessageDialog(
                this,
                "Profile Updated Successfully");
       

    } catch (Exception e) {

        e.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                "Error while saving profile");
    }
                }}
        
        public static void main(String [] args)
        {
                new EditProfile("7178");
        }
}

