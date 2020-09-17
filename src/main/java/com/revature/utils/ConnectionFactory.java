package com.revature.utils;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    // Setting up an "eager" singleton.
    // Private makes only this class be able to instantiate it.
    private static ConnectionFactory dataConnect = new ConnectionFactory();

    private Properties properties = new Properties();

    // Default Constructor
    private ConnectionFactory() {
        super();
        try {
//            properties.load(new FileReader("src/main/resources/application.properties"));
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // We make this method public, so if we need the ConnectionFactory,
    // it will only give the one that is already instantiated.
    public static ConnectionFactory getInstance() {
        return dataConnect;
    }

    /**
     * Establishes a connection with the database.
     *
     * @return Connection conn
     */
    public Connection getConnection() {

        // Instantiates a Connection object to null to ensure
        // the app is not already connected.
        Connection conn = null;

        try {

            // This gets language specific driver; we are using PostGreSQL
            Class.forName("org.postgresql.Driver");

            // This is DriverManager actually establishing the connection with
            // the username and password we've set for our database. This is root
            // user access

            // .properties file won't recognize url tag, and thinks the whole thing is null......
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://revature-training.cveu74hasekl.us-east-1.rds.amazonaws.com:5432/postgres",
                    "postgres",
                    "Ultimate1!"
            );

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (conn == null) {
            throw new RuntimeException("Failed to establish connection THIS IS THE ERRROR HERERERERERE.");
        }
        return conn;
    }

    // This is part of our singleton setup. To ensure clones of dataConnect
    // do not get created.
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}