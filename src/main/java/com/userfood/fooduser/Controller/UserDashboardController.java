package com.userfood.fooduser.Controller;

import com.userfood.fooduser.model.User;

public class UserDashboardController {
    private User user;

    public void setUser(User user) {
        this.user = user;
        updateUI();
    }

    private void updateUI() {
        System.out.println("User logged in: " + user.getName());
    }
}

