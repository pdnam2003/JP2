package com.userfood.fooduser.model;

public enum Status {
    PENDING("PENDING"),
    COMPLETED("COMPLETED");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}