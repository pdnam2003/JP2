package com.cyber.foodmanagement.Data;
import com.cyber.foodmanagement.Data.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void main(String[] args) {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS NetCafeDB";

        String createFoodCategoriesTableSQL = """
            CREATE TABLE IF NOT EXISTS FOOD_CATEGORIES (
                category_id INT AUTO_INCREMENT PRIMARY KEY,
                category_name VARCHAR(255) NOT NULL
            )
        """;

        String createFoodsTableSQL = """
            CREATE TABLE IF NOT EXISTS FOODS (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                description TEXT,
                price DECIMAL(10,2) NOT NULL,
                quantity INT DEFAULT 0,
                imageUrl VARCHAR(255),
                category_id INT,
                FOREIGN KEY (category_id) REFERENCES FOOD_CATEGORIES(category_id)
            )
        """;

        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate(createDatabaseSQL);
            System.out.println("Database created successfully!");

            String useDatabaseSQL = "USE NetCafeDB";
            statement.executeUpdate(useDatabaseSQL);
            System.out.println("Using 'NetCafeDB' database!");

            statement.executeUpdate(createFoodCategoriesTableSQL);
            statement.executeUpdate(createFoodsTableSQL);

            System.out.println("Tables created successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
