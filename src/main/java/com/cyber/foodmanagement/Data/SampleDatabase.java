package com.cyber.foodmanagement.Data;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleDatabase {
    public static void main(String[] args) {

        String insertFoodCategoriesSQL = """
            INSERT INTO FOOD_CATEGORIES (category_name)
            VALUES ('Food'),
                   ('Drinks')
        """;

        String insertFoodsSQL = """
            INSERT INTO FOODS (food_name, description, price, quantity, category_id)
            VALUES ('Burger', 'Delicious beef burger', 5.00, 20, 1),
                   ('Soda', 'Refreshing soft drink', 2.50, 30, 2)
        """;


        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("USE NetCafeDB");
            statement.executeUpdate(insertFoodCategoriesSQL);
            statement.executeUpdate(insertFoodsSQL);


            System.out.println("Sample data inserted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting data", e);
        }
    }
}