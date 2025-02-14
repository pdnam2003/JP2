package com.userfood.fooduser.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import com.userfood.fooduser.Database.DatabaseConnection;

import java.sql.*;
import java.util.Objects;

public class UserInterfaceController {

    @FXML
    private VBox cartVBox;
    @FXML
    private GridPane foodGrid;
    @FXML
    private GridPane drinksGrid;
    @FXML
    private Label totalLabel;

    private final ObservableList<String> cartItems = FXCollections.observableArrayList();
    private double total = 0;

    public void initialize() {
        loadDrinksFromDatabase();
        loadFoodsFromDatabase();
        updateCartDisplay();
    }


    private void loadDrinksFromDatabase() {
        String query = "SELECT food_name, price, image_url FROM FOODS WHERE category_id = 1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            int drinkIndex = 0;
            while (rs.next()) {
                String name = rs.getString("food_name");
                double price = rs.getDouble("price");
                if (price > 0) {
                    String imageUrl = rs.getString("image_url");
                    addProductToGrid(name, price, imageUrl, drinkIndex, drinksGrid);
                    drinkIndex++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void loadFoodsFromDatabase() {
        String query = "SELECT food_name, price, image_url FROM FOODS WHERE category_id = 2";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            int foodIndex = 0;
            while (rs.next()) {
                String name = rs.getString("food_name");
                double price = rs.getDouble("price");
                if (price > 0) { // Kiểm tra giá dương
                    String imageUrl = rs.getString("image_url");
                    addProductToGrid(name, price, imageUrl, foodIndex, foodGrid);
                    foodIndex++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void addProductToGrid(String name, double price, String imageUrl, int index, GridPane gridPane) {
        int column = index % 3;
        int row = index / 3;

        HBox productBox = new HBox(15);
        productBox.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-border-radius: 8px; -fx-padding: 10px; -fx-background-color: white;");

        ImageView imageView;
        if (imageUrl != null && !imageUrl.isEmpty()) {
            imageView = new ImageView(new Image(imageUrl));
        } else {
            imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/userfood/fooduser/asset/logo.jpg"))));
        }
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        VBox vbox = new VBox(8);
        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label priceLabel = new Label("Price: " + price + " $");
        priceLabel.setStyle("-fx-font-size: 14px;");

        Button addButton = new Button("🛒 Add");
        addButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px;");
        addButton.setOnAction(e -> addToCart(name, price));

        vbox.getChildren().addAll(nameLabel, priceLabel, addButton);
        productBox.getChildren().addAll(imageView, vbox);
        gridPane.add(productBox, column, row);
    }

    private void addToCart(String item, double price) {
        if (price > 0) {
            String cartItem = item + " - " + price + " $";
            cartItems.add(cartItem);
            total += price;
            totalLabel.setText("Total: " + total + " $");
            updateCartDisplay();
        }
    }

    private void updateCartDisplay() {
        cartVBox.getChildren().clear();
        for (String item : cartItems) {
            HBox hbox = new HBox(10);
            Label itemLabel = new Label(item);
            Button removeButton = new Button("❌");
            removeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 14px;");


            removeButton.setOnAction(e -> {

                String priceString = item.split(" - ")[1].trim().replace("$", "").trim();
                double price = Double.parseDouble(priceString);
                if (price > 0) {
                    cartItems.remove(item);
                    total -= price;
                    totalLabel.setText("Total: " + total + " $");
                    updateCartDisplay();
                }
            });


            hbox.getChildren().addAll(itemLabel, removeButton);
            cartVBox.getChildren().add(hbox);
        }
    }
}