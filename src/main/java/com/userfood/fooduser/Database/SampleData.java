package com.userfood.fooduser.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class SampleData {
    public static void main(String[] args) {
        String insertUsersSQL = """
            INSERT INTO USERS (username, password, balance)
            VALUES ('john_doe', 'password123', 100.00),
                   ('jane_smith', 'password456', 150.50)
        """;

        String insertAdminsSQL = """
            INSERT INTO ADMINS (adminname, password)
            VALUES ('admin1', 'adminpassword1'),
                   ('admin2', 'adminpassword2')
        """;

        String insertRoomTypesSQL = """
            INSERT INTO ROOM_TYPES (type_name, description)
            VALUES ('VIP', 'VIP room with premium facilities'),
                   ('Standard', 'Standard room with basic facilities')
        """;

        String insertRoomsSQL = """
            INSERT INTO ROOMS (room_name, room_type_id, capacity)
            VALUES ('Room A', 1, 4),
                   ('Room B', 2, 6)
        """;

        String insertComputersSQL = """
            INSERT INTO COMPUTERS (computer_name, status, specifications, ip_address, room_id)
            VALUES ('Computer 1', 'ONLINE', 'Intel i7, 16GB RAM, 512GB SSD', '192.168.1.10', 1),
                   ('Computer 2', 'OFFLINE', 'Intel i5, 8GB RAM, 256GB SSD', '192.168.1.11', 2)
        """;

        String insertFoodCategoriesSQL = """
            INSERT INTO FOOD_CATEGORIES (category_name)
            VALUES ('Food'),
                   ('Drinks')
        """;

        String insertFoodsSQL = """
    INSERT INTO FOODS (food_name, description, price, quantity, category_id)
    VALUES ('Burger', 'Delicious beef burger', 5.00, 20, 1),
           ('Pizza', 'Cheesy pepperoni pizza', 8.00, 15, 1),
           ('Pasta', 'Creamy Alfredo pasta', 7.50, 10, 1),
           ('Fries', 'Crispy French fries', 2.50, 30, 1),
           ('Salad', 'Fresh garden salad', 4.00, 25, 1),
           ('Soda', 'Refreshing soft drink', 2.50, 30, 2),
           ('Water', 'Bottled mineral water', 1.00, 50, 2),
           ('Juice', 'Fresh fruit juice', 3.00, 20, 2),
           ('Coffee', 'Hot brewed coffee', 2.00, 40, 2),
           ('Tea', 'Refreshing iced tea', 1.50, 35, 2),
           ('Milkshake', 'Creamy chocolate milkshake', 4.00, 15, 2),
           ('Smoothie', 'Healthy fruit smoothie', 3.50, 18, 2),
           ('Beer', 'Chilled beer', 5.00, 12, 2),
           ('Cocktail', 'Mixed fruit cocktail', 6.00, 10, 2),
           ('Hot Chocolate', 'Rich hot chocolate', 2.50, 20, 2),
           ('Energy Drink', 'Boosting energy drink', 3.00, 25, 2),
           ('Lemonade', 'Freshly squeezed lemonade', 2.00, 50, 2),
           ('Iced Coffee', 'Chilled iced coffee', 3.50, 15, 2),
           ('Chips', 'Crunchy potato chips', 1.50, 40, 1),
           ('Nuggets', 'Crispy chicken nuggets', 4.50, 22, 1)
""";

        String insertOrdersSQL = """
            INSERT INTO ORDERS (user_id, total_amount, status)
            VALUES (1, 7.50, 'PENDING'),
                   (2, 15.00, 'COMPLETED')
        """;

        String insertOrderDetailsSQL = """
            INSERT INTO ORDER_DETAILS (order_id, food_id, quantity, price, total_price, discount)
            VALUES (1, 1, 1, 5.00, 5.00, 0),
                   (1, 2, 1, 2.50, 2.50, 0),
                   (2, 1, 2, 5.00, 10.00, 1)
        """;

        String insertTopUpHistorySQL = """
            INSERT INTO TOP_UP_HISTORY (user_id, amount, payment_method)
            VALUES (1, 50.00, 'cash'),
                   (2, 100.00, 'card')
        """;

        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("USE NetCafeDB");
            statement.executeUpdate(insertUsersSQL);
            statement.executeUpdate(insertAdminsSQL);
            statement.executeUpdate(insertRoomTypesSQL);
            statement.executeUpdate(insertRoomsSQL);
            statement.executeUpdate(insertComputersSQL);
            statement.executeUpdate(insertFoodCategoriesSQL);
            statement.executeUpdate(insertFoodsSQL);
            statement.executeUpdate(insertOrdersSQL);
            statement.executeUpdate(insertOrderDetailsSQL);
            statement.executeUpdate(insertTopUpHistorySQL);
            System.out.println("Sample data inserted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting data", e);
        }
    }
}
