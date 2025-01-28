module com.example.userfood {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.userfood to javafx.fxml;
    exports com.example.userfood;
    exports com.example.userfood.Controller;
    opens com.example.userfood.Controller to javafx.fxml;
    exports com.example.userfood.Entity;
    opens com.example.userfood.Entity to javafx.fxml;
}