package com.userfood.fooduser.model;

public class OrderDetails {
    private int OrderDetailsId;
    private Oders Orders;
   private Food food;
    private  int quantity;
    private  double price;
    private  double total_price;
    private double discount;

    public OrderDetails() {;}
    public OrderDetails(int orderDetailsId, Oders orders, Food food, int quantity, double price, double total_price, double discount) {
        OrderDetailsId = orderDetailsId;
        Orders = orders;
        this.food = food;
        this.quantity = quantity;
        this.price = price;
        this.total_price = total_price;
        this.discount = discount;
    }

    public int getOrderDetailsId() {
        return OrderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        OrderDetailsId = orderDetailsId;
    }

    public Oders getOrders() {
        return Orders;
    }

    public void setOrders(Oders orders) {
        Orders = orders;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


}
