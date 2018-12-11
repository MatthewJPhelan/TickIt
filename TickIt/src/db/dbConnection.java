package db;

import java.sql.*;

public class dbConnection {

    Connection connection = null;

    public Connection dbConnector() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/rome",
                    "rome", "9njiaprt0d");
            System.out.println("Connection successfull");
            return connection;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}