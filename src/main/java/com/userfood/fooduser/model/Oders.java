package com.userfood.fooduser.model;

import java.time.LocalDateTime;

public class Oders {
    private int orderId;
    private User user;
    private LocalDateTime orderDate;
    private  int totalAmount;
    private Status    status;
     public  Oders() {;}

    public Oders(int orderId, User user, LocalDateTime orderDate, int totalAmount, Status status) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
