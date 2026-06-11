package bank.manage.system;

import java.sql.*;

public class DBConnection {

    public Connection con;
    public Statement s;

    public DBConnection() {

        try {

            con = DriverManager.getConnection(
                    "jdbc:sqlite:database/users.db");

            s = con.createStatement();

            System.out.println("Database Connected");

            String createTable =
                    "CREATE TABLE IF NOT EXISTS SIGNUP (" +
                    "FORMNO VARCHAR(20) PRIMARY KEY," +
                    "NAME VARCHAR(100)," +
                    "FNAME VARCHAR(100)," +
                    "DOB VARCHAR(50)," +
                    "GENDER VARCHAR(20)," +
                    "EMAIL VARCHAR(100)," +
                    "MARITAL_STATUS VARCHAR(20)," +
                    "ADDRESS VARCHAR(200)," +
                    "STATE VARCHAR(50)," +
                    "PINCODE VARCHAR(20)," +
                    "PHOTO_PATH VARCHAR(500)" +
                    ")";
            
            s.executeUpdate(createTable);

        } catch(Exception e) {

            e.printStackTrace();
        }

    }
}