package com.userfood.fooduser.dao;

import com.userfood.fooduser.model.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAO {
    private Connection connection;

    public OrderDetailsDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveOrderDetails(OrderDetails orderDetails) throws SQLException {
        if (orderDetails == null) {
            throw new IllegalArgumentException("OrderDetails cannot be null");
        }
        if (orderDetails.getOrders() == null || orderDetails.getFood() == null) {
            throw new IllegalArgumentException("Orders or Food in OrderDetails cannot be null");
        }
        if (orderDetails.getQuantity() <= 0 || orderDetails.getPrice() < 0 || orderDetails.getTotal_price() < 0 || orderDetails.getDiscount() < 0) {
            throw new IllegalArgumentException("Invalid values in OrderDetails");
        }

        String sql = "INSERT INTO order_details (OrderDetailsId, Orders, food, quantity, price, total_price, discount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderDetails.getOrderDetailsId());
            pstmt.setInt(2, orderDetails.getOrders().getOrderId());
            pstmt.setInt(3, orderDetails.getFood().getId());
            pstmt.setInt(4, orderDetails.getQuantity());
            pstmt.setDouble(5, orderDetails.getPrice());
            pstmt.setDouble(6, orderDetails.getTotal_price());
            pstmt.setDouble(7, orderDetails.getDiscount());
            pstmt.executeUpdate();
        }
    }
}