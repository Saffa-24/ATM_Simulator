package bank.manage.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;

public class EditProfile extends JFrame implements ActionListener {

    JTextField nameField,fnameField,emailField,
            addressField,stateField,pincodeField;

    JButton uploadButton,saveButton;

    JLabel photoLabel;

    String formno;
    String photoPath="";

    public EditProfile(String formno) {

        this.formno=formno;

        setLayout(null);
        setSize(900,700);

        JLabel heading=
                new JLabel("EDIT PROFILE");

        heading.setBounds(350,30,250,30);
        heading.setFont(
                new Font("Arial",
                        Font.BOLD,20));

        add(heading);

        JLabel nameLabel=
                new JLabel("Name");

        nameLabel.setBounds(100,100,150,30);
        add(nameLabel);

        nameField=new JTextField();
        nameField.setBounds(250,100,250,30);
        add(nameField);

        JLabel fnameLabel=
                new JLabel("Father Name");

        fnameLabel.setBounds(100,150,150,30);
        add(fnameLabel);

        fnameField=new JTextField();
        fnameField.setBounds(250,150,250,30);
        add(fnameField);

        JLabel emailLabel=
                new JLabel("Email");

        emailLabel.setBounds(100,200,150,30);
        add(emailLabel);

        emailField=new JTextField();
        emailField.setBounds(250,200,250,30);
        add(emailField);

        JLabel addressLabel=
                new JLabel("Address");

        addressLabel.setBounds(100,250,150,30);
        add(addressLabel);

        addressField=new JTextField();
        addressField.setBounds(250,250,250,30);
        add(addressField);

        JLabel stateLabel=
                new JLabel("State");

        stateLabel.setBounds(100,300,150,30);
        add(stateLabel);

        stateField=new JTextField();
        stateField.setBounds(250,300,250,30);
        add(stateField);

        JLabel pinLabel=
                new JLabel("Pincode");

        pinLabel.setBounds(100,350,150,30);
        add(pinLabel);

        pincodeField=new JTextField();
        pincodeField.setBounds(250,350,250,30);
        add(pincodeField);

        photoLabel=new JLabel();
        photoLabel.setBounds(600,100,150,150);
        add(photoLabel);

        uploadButton=
                new JButton("Upload Photo");

        uploadButton.setBounds(580,280,180,30);
        uploadButton.addActionListener(this);
        add(uploadButton);

        saveButton=
                new JButton("Save");

        saveButton.setBounds(300,500,150,40);
        saveButton.addActionListener(this);
        add(saveButton);

        loadData();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void loadData() {
        try {
            DBConnection con = new DBConnection();

            String query = "SELECT * FROM SIGNUP WHERE FORMNO='" + formno + "'";

            ResultSet rs = con.s.executeQuery(query);

            if (rs.next()) {
                nameField.setText(rs.getString("NAME"));
                fnameField.setText(rs.getString("FNAME"));
                emailField.setText(rs.getString("EMAIL"));
                addressField.setText(rs.getString("ADDRESS"));
                stateField.setText(rs.getString("STATE"));
                pincodeField.setText(rs.getString("PINCODE"));

                photoPath = rs.getString("PHOTO_PATH");

                ImageIcon icon = new ImageIcon(photoPath);
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(img));
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
                                String query = "UPDATE SIGNUP SET NAME='" + nameField.getText() + "', FNAME='" + fnameField.getText() + "', EMAIL='" + emailField.getText() + "', ADDRESS='" + addressField.getText() + "', STATE='" + stateField.getText() + "', PINCODE='" + pincodeField.getText() + "', PHOTO_PATH='" + photoPath + "' WHERE FORMNO='" + formno + "'";
                                con.s.executeUpdate(query);
                                JOptionPane.showMessageDialog(this, "Profile updated successfully");
                        } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Error while saving profile");
                        }
                }
        }
}

