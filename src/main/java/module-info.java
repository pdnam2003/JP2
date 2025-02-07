module com.example.foodmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.cyber.foodmanagement to javafx.fxml;
    exports com.cyber.foodmanagement;
    exports com.cyber.foodmanagement.Controller;
    opens com.cyber.foodmanagement.Controller to javafx.fxml;
    exports com.cyber.foodmanagement.model;
    opens com.cyber.foodmanagement.model to javafx.fxml;
}