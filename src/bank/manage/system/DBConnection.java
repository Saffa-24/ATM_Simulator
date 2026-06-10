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

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}