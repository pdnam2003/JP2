module com.userffood.fooduser {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.userfood.fooduser to javafx.fxml;
    exports com.userfood.fooduser;
    exports com.userfood.fooduser.Controller;
    opens com.userfood.fooduser.Controller to javafx.fxml;
}