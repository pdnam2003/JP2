package com.example.userfood.Controller;

import com.example.userfood.Entity.Category;
import com.example.userfood.Entity.FoodItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class UserFoodController {

    @FXML
    private ComboBox<String> categoryCombo;

    @FXML
    private TextField searchField;

    @FXML
    private FlowPane flowPane;

    private ObservableList<FoodItem> allItems;

    public void initialize() {

        allItems = FXCollections.observableArrayList(
                new FoodItem("Trà sữa", "Thức uống ngọt ngào", 27000, 10, Category.D, "com/example/userfood/Image/milktea.png"),
                new FoodItem("Soda chanh", "Thức uống giải khát", 27000, 15, Category.D, "com/example/userfood/Image/soda-chanh.jpg"),
                new FoodItem("Cơm Gà", "Món ăn chính", 35000, 20, Category.F, "com/example/userfood/Image/ComGa.jpeg"),
                new FoodItem("Mì gói", "Món ăn nhanh", 20000, 50,Category.F, "com/example/userfood/Image/Migoi.jpg"),
                new FoodItem("Sữa chua lắc", "Thức uống bổ dưỡng", 25000, 30,  Category.D, "com/example/userfood/Image/suachua.jpeg"),
                new FoodItem("Lipton soda", "Thức uống pha trộn", 18000, 25,  Category.D, "com/example/userfood/Image/lippton.jpeg"),
                new FoodItem("Mì xào bò", "Món ăn ngon miệng", 30000, 12, Category.F, "com/example/userfood/Image/mixaobo.jpeg"),
                new FoodItem("Cà phê đen", "Thức uống cà phê", 15000, 40,  Category.D, "com/example/userfood/Image/cafeden.jpeg")
        );

        categoryCombo.setItems(FXCollections.observableArrayList("Tất cả", "Đồ uống", "Đồ ăn"));
        categoryCombo.setValue("Tất cả");


        categoryCombo.setOnAction(event -> filterItems());
        searchField.textProperty().addListener((observableArrayList, oldValue, newValue) -> filterItems());
        displayItems(allItems);
    }

    private void filterItems() {
        String selectedCategory = categoryCombo.getValue();
        String searchText = searchField.getText().toLowerCase();

        ObservableList<FoodItem> filteredItems = FXCollections.observableArrayList();

        for (FoodItem item : allItems) {
            boolean matchesCategory = selectedCategory.equals("Tất cả") || item.getCategory().equals(selectedCategory);
            boolean matchesSearch = item.getName().toLowerCase().contains(searchText);

            if (matchesCategory && matchesSearch) {
                filteredItems.add(item);
            }
        }

        displayItems(filteredItems);
    }

    private void displayItems(ObservableList<FoodItem> items) {
        // Đảm bảo flowPane không null
        if (flowPane == null) {
            System.out.println("Error: flowPane is null.");
            return;
        }

        flowPane.getChildren().clear();

        for (FoodItem item : items) {
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            vBox.setAlignment(Pos.CENTER);

            try {
                // Kiểm tra null trước khi tải ảnh
                String imagePath = item.getImage();
                if (imagePath != null || !imagePath.isEmpty()) {
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/" + imagePath)));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    vBox.getChildren().add(imageView);
                    System.out.println("Image path: " + imagePath);
                    if (getClass().getResourceAsStream("/" + imagePath) == null) {
                        System.err.println("Resource not found: " + imagePath);
                    }

                } else {
                    throw new Exception("Image path is null or empty.");
                }

            } catch (Exception e) {
                // Hiển thị thông báo lỗi nếu không tải được hình ảnh
                Text errorText = new Text("Image not found");
                vBox.getChildren().add(errorText);
                System.err.println("Error loading image: " + e.getMessage());
            }

            flowPane.getChildren().add(vBox);
        }
    }

}
