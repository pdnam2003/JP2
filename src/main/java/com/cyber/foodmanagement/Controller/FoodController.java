package com.cyber.foodmanagement.Controller;

import com.cyber.foodmanagement.model.Category;
import com.cyber.foodmanagement.model.Food;
import com.cyber.foodmanagement.Data.DatabaseConnection;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class FoodController {

    @FXML
    private TableView<Food> tableView;

    @FXML
    private TableColumn<Food, Integer> idColumn;

    @FXML
    private TableColumn<Food, String> nameColumn;

    @FXML
    private TableColumn<Food, String> descriptionColumn;

    @FXML
    private TableColumn<Food, Double> priceColumn;

    @FXML
    private TableColumn<Food, Integer> quantityColumn;

    @FXML
    private TableColumn<Food, Image> imageUrlColumn;

    @FXML
    private TableColumn<Food, String> categoryNameColumn;

    @FXML
    private TableColumn<Food, Void> actionColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton, addButton;

    private ObservableList<Food> foodList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configureTable();
        loadFoodFromDatabase();
        searchButton.setOnAction(event -> searchFood());
        addButton.setOnAction(event -> openNewWindowAddFood());
    }

    private void configureTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Hiển thị ảnh thay vì đường dẫn URL
        imageUrlColumn.setCellValueFactory(cellData -> cellData.getValue().imageProperty());
        imageUrlColumn.setCellFactory(column -> new TableCell<Food, Image>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);
                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    setGraphic(imageView);
                }
            }
        });

        categoryNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategory().getNameCategory())
        );

        actionColumn.setCellFactory(column -> new TableCell<Food, Void>() {
            private final Button deleteButton = new Button("Xóa");

            {
                deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                deleteButton.setOnAction(event -> {
                    Food food = getTableView().getItems().get(getIndex());
                    handleDeleteAction(food);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });
    }

    private void loadFoodFromDatabase() {
        foodList.clear();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT FOODS.*, FOOD_CATEGORIES.category_name " +
                             "FROM FOODS " +
                             "INNER JOIN FOOD_CATEGORIES ON FOODS.category_id = FOOD_CATEGORIES.category_id");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                Food food = new Food(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("imageUrl"),
                        category
                );

                // Xử lý tải hình ảnh từ URL
                String imageUrl = rs.getString("imageUrl");
                Image image = null;
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    try {
                        // Nếu imageUrl có vẻ là đường dẫn file (ví dụ bắt đầu với "C:"), chuyển sang URI hợp lệ
                        java.io.File file = new java.io.File(imageUrl);
                        if (file.exists()) {
                            image = new Image(file.toURI().toString(), 50, 50, true, true);
                        } else if (imageUrl.startsWith("http://") || imageUrl.startsWith("https://") || imageUrl.startsWith("file:")) {
                            image = new Image(imageUrl, 50, 50, true, true);
                        } else {
                            // Thử tải ảnh từ classpath (đường dẫn tương đối)
                            java.net.URL resource = getClass().getResource(imageUrl);
                            if (resource != null) {
                                image = new Image(resource.toExternalForm(), 50, 50, true, true);
                            } else {
                                System.err.println("Resource not found for image: " + imageUrl);
                                image = loadDefaultImage();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        image = loadDefaultImage();
                    }
                } else {
                    image = loadDefaultImage();
                }
                food.setImage(image);
                foodList.add(food);
            }
            tableView.setItems(foodList);
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchFood() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            loadFoodFromDatabase();
            return;
        }

        ObservableList<Food> filteredList = FXCollections.observableArrayList();
        for (Food food : foodList) {
            if (food.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(food);
            }
        }
        tableView.setItems(filteredList);
    }

    @FXML
    private void openNewWindowAddFood() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cyber/foodmanagement/view/add-new-food.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Thêm Món Ăn");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadFoodFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditAction(Food food) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cyber/foodmanagement/view/EditFood.fxml"));
            Parent root = loader.load();

            EditFoodController controller = loader.getController();
            controller.setFoodData(food);
            Stage stage = new Stage();
            stage.setTitle("Chỉnh Sửa Món Ăn");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadFoodFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteAction(Food food) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Bạn có chắc muốn xóa món ăn này?");
        alert.setContentText("Hành động này không thể hoàn tác!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM FOODS WHERE id = ?")) {

                statement.setInt(1, food.getId());
                statement.executeUpdate();

                foodList.remove(food);
                tableView.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Phương thức loadDefaultImage() cố gắng tải ảnh mặc định từ resources.
     * Nếu không tìm thấy, nó sử dụng ảnh placeholder từ URL.
     */
    private Image loadDefaultImage() {
        try {
            java.net.URL defaultResource = getClass().getResource("/default_image.png");
            if (defaultResource != null) {
                return new Image(defaultResource.toExternalForm(), 50, 50, true, true);
            } else {
                System.err.println("Default image not found in resources. Using placeholder.");
                return new Image("https://via.placeholder.com/50", 50, 50, true, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Image("https://via.placeholder.com/50", 50, 50, true, true);
        }
    }
}
