module com.example.foods {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.foods to javafx.fxml;
    exports com.example.foods;
}