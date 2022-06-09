package com.scgraf;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.views.MainView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Application.stage = stage;
        Platform.setImplicitExit(false);
        scene = new Scene(new MainView(), UIConfig.stageWidth, UIConfig.stageHeight);
        stage.setResizable(false);

        stage.setTitle("scgraf");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}