module com.example.bbs {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.bbs to javafx.fxml;
    exports com.example.bbs;
}