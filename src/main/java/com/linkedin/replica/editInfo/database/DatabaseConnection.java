package com.linkedin.replica.editInfo.database;

import com.arangodb.ArangoDB;
import utils.ConfigReader;

import java.io.IOException;

/**
 * A singleton class carrying a database instance
 */
public class DatabaseConnection {
    private ArangoDB arangoDriver;
    private ConfigReader config;

    private volatile static DatabaseConnection dbConnection;

    private DatabaseConnection() throws IOException {
        config = new ConfigReader("database_auth");

        initializeArangoDB();
    }

    private void initializeArangoDB() {
        arangoDriver = new ArangoDB.Builder()
                .user(config.getConfig("arangodb.user"))
                .password(config.getConfig("arangodb.password"))
                .build();
    }
    public static void init() throws IOException {
        dbConnection = new DatabaseConnection();
    }
    public static DatabaseConnection getInstance() throws IOException {
        return dbConnection;
    }
    /**
     * Get a singleton DB instance
     * @return The DB instance
     */
   public static DatabaseConnection getDBConnection() throws IOException {
        if(dbConnection == null) {
            synchronized (DatabaseConnection.class) {
                if (dbConnection == null)
                    dbConnection = new DatabaseConnection();
            }
        }
        return dbConnection;
    }
    public void closeConnections() {
        arangoDriver.shutdown();
    }

    public ArangoDB getArangoDriver() {
        return arangoDriver;
    }
}