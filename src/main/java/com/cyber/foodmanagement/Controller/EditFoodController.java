package com.cyber.foodmanagement.Controller;

import com.cyber.foodmanagement.model.Category;
import com.cyber.foodmanagement.model.Food;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditFoodController {

    @FXML
    private TextField nameField, descriptionField, priceField, quantityField;
    @FXML
    private ComboBox<String> categoryComboBox;

    private Food selectedFood;

    public void setFoodData(Food food) {
        this.selectedFood = food;
        nameField.setText(food.getName());
        descriptionField.setText(food.getDescription());
        priceField.setText(String.valueOf(food.getPrice()));
        quantityField.setText(String.valueOf(food.getQuantity()));
        categoryComboBox.getItems().addAll("Food", "Drink");
        categoryComboBox.setValue(food.getCategory().getNameCategory()); // Set the selected category
    }

    @FXML
    private void handleSave() {
        selectedFood.setName(nameField.getText());
        selectedFood.setDescription(descriptionField.getText());
        selectedFood.setPrice(Double.parseDouble(priceField.getText()));
        selectedFood.setQuantity(Integer.parseInt(quantityField.getText()));
        String selectedCategory = categoryComboBox.getValue();
        Category category = getCategoryByName(selectedCategory);
        selectedFood.setCategory(category);
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private Category getCategoryByName(String name) {
        return new Category(-1, name);
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}