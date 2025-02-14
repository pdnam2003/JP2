package com.userfood.fooduser.service;

import com.userfood.fooduser.dao.OrderDetailsDAO;
import com.userfood.fooduser.Database.DatabaseConnection;
import com.userfood.fooduser.model.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;

public class PaymentService {
    public void processPayment(OrderDetails orderDetails) {
        if (orderDetails == null || orderDetails.getQuantity() <= 0 || orderDetails.getPrice() < 0) {
            throw new IllegalArgumentException("Order details are invalid.");
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO(connection);
            orderDetailsDAO.saveOrderDetails(orderDetails);
            // Có thể thêm logic để xử lý thanh toán, ví dụ như cập nhật trạng thái đơn hàng
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}