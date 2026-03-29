package com.hydra.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

/**
 * Database Utility - For database operations in tests
 */
public class DatabaseUtils {
    private static final Logger logger = LogManager.getLogger(DatabaseUtils.class);
    private static Connection connection;

    /**
     * Establish database connection
     */
    public static void connectToDatabase(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // For MySQL
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Database connection established successfully");
        } catch (Exception e) {
            logger.error("Failed to connect to database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute SELECT query
     */
    public static ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            logger.info("Query executed successfully: " + query);
            return resultSet;
        } catch (Exception e) {
            logger.error("Failed to execute query: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute UPDATE/INSERT/DELETE query
     */
    public static int executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            logger.info("Update query executed successfully: " + query);
            return result;
        } catch (Exception e) {
            logger.error("Failed to execute update query: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Get single value from database
     */
    public static String getSingleValue(String query) {
        try {
            ResultSet resultSet = executeQuery(query);
            if (resultSet.next()) {
                String value = resultSet.getString(1);
                logger.info("Retrieved value: " + value);
                return value;
            }
            return null;
        } catch (Exception e) {
            logger.error("Failed to get single value: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get row count
     */
    public static int getRowCount(String tableName, String whereClause) {
        try {
            String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + whereClause;
            ResultSet resultSet = executeQuery(query);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.info("Row count: " + count);
                return count;
            }
            return 0;
        } catch (Exception e) {
            logger.error("Failed to get row count: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Close database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Database connection closed successfully");
            }
        } catch (Exception e) {
            logger.error("Failed to close database connection: " + e.getMessage());
        }
    }
}
