package com.cyber.foodmanagement.Controller;

import com.cyber.foodmanagement.Data.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddFoodController {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField specificationsInput;
    @FXML
    private TextField priceInput;
    @FXML
    private TextField quantityInput;
    @FXML
    private TextField imageUrlInput; // Input for image URL
    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    public void initialize() {
        categoryComboBox.getItems().addAll("Food", "Drink");
    }

    @FXML
    private void handleUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File file = fileChooser.showOpenDialog(((Stage) nameInput.getScene().getWindow()));

        if (file != null) {
            imageUrlInput.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleSaveButton() {
        String name = nameInput.getText();
        String specifications = specificationsInput.getText();
        String priceStr = priceInput.getText();
        String quantityStr = quantityInput.getText();
        String imageUrl = imageUrlInput.getText();
        String category = categoryComboBox.getValue();

        if (name.isEmpty() || specifications.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty() || imageUrl.isEmpty() || category == null) {
            showAlert(AlertType.ERROR, "Form Incomplete", "Please fill all fields.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO FOODS (name, description, price, quantity, imageUrl, category_id) VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, name);
            statement.setString(2, specifications);
            statement.setDouble(3, Double.parseDouble(priceStr));
            statement.setInt(4, Integer.parseInt(quantityStr));
            statement.setString(5, imageUrl);

            int categoryId = category.equals("Food") ? 1 : 2;
            statement.setInt(6, categoryId);

            statement.executeUpdate();
            showAlert(AlertType.INFORMATION, "Success", "Food added successfully.");

            Stage stage = (Stage) nameInput.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Failed to add food.");
        }
    }

    @FXML
    private void handleCancelButton() {
        nameInput.clear();
        specificationsInput.clear();
        priceInput.clear();
        quantityInput.clear();
        imageUrlInput.clear();
        categoryComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}