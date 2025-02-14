package com.userfood.fooduser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UserInterfaceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserInterfaceApplication.class.getResource("/com/userfood/fooduser/view/user-interface.fxml"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(fxmlLoader.load(), primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        Image logo = new Image(Objects.requireNonNull(getClass().getResource("/com/userfood/fooduser/asset/logo.jpg")).toExternalForm());
        stage.getIcons().add(logo);
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setTitle("Cyber Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}