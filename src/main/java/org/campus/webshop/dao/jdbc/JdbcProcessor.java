package org.campus.webshop.dao.jdbc;

import java.sql.*;

public class JdbcProcessor {
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE IF NOT EXISTS product(id  SERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL, price double precision, creationDate  TIMESTAMP)";
    private static final String CREATE_TABLE_USERS =   "CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, email VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, sole VARCHAR(100), firstName VARCHAR(100), lastName VARCHAR(100))";

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop",
                "user",
                "pswd");
    }

    protected static void checkCreatingTable() throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_PRODUCT);) {

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_USERS);) {

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
