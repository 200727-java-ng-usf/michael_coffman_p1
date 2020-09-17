package com.revature.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
        try {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream propsInput = loader.getResourceAsStream("application.properties");

            if (propsInput == null) {
                properties.setProperty("url", System.getProperty("url"));
                properties.setProperty("username", System.getProperty("username"));
                properties.setProperty("password", System.getProperty("password"));
            } else {
                properties.load(propsInput);
            }

        } catch (IOException e) {
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

        Connection conn = null;

        try {


            Class.forName("org.postgresql.Driver");


            conn = DriverManager.getConnection(
                    "jdbc:postgresql://revature-training.cveu74hasekl.us-east-1.rds.amazonaws.com:5432/postgres",
                    "postgres",
                    "Ultimate1!");
            );

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (conn == null) {
            try {
                conn = DriverManager.getConnection(
                        System.getenv("url"),
                        System.getenv("username"),
                        System.getenv("password")
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
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