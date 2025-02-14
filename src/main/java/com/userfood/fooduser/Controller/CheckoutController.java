package com.userfood.fooduser.Controller;

import com.userfood.fooduser.Database.DatabaseConnection;
import com.userfood.fooduser.model.OrderDetails;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckoutController {

    @FXML
    private Button checkoutButton;

    @FXML
    private TableView<OrderDetails> orderTable;

    @FXML
    private void initialize() {
        checkoutButton.setOnAction(event -> handleCheckout());
    }
    @FXML
    private void handleCheckout() {
        if (orderTable.getItems().isEmpty()) {
            System.out.println("Không có sản phẩm để thanh toán!");
            return;
        }
        int orderId = getLatestOrderId();
            Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO ORDER_DETAILS (order_id, food_id, quantity, price, total_price, discount) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (OrderDetails item : orderTable.getItems()) {
                if (item.getFood() == null || item.getQuantity() <= 0 || item.getPrice() < 0 || item.getTotal_price() < 0 || item.getDiscount() < 0) {
                    throw new IllegalArgumentException("Dữ liệu không hợp lệ trong OrderDetails: " + item);
                }
                statement.setInt(1, orderId);
                statement.setInt(2, item.getFood().getId());
                statement.setInt(3, item.getQuantity());
                statement.setDouble(4, item.getPrice());
                statement.setDouble(5, item.getTotal_price());
                statement.setDouble(6, item.getDiscount());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit(); // Commit transaction
            System.out.println("Thanh toán thành công! Dữ liệu đã được lưu vào ORDER_DETAILS.");
            orderTable.getItems().clear();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi lưu dữ liệu vào cơ sở dữ liệu: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Lỗi dữ liệu: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private int getLatestOrderId() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT MAX(order_id) AS latest_order_id FROM ORDER_DETAILS";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int latestOrderId = resultSet.getInt("latest_order_id");
                return latestOrderId + 1;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}