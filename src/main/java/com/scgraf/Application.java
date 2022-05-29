package com.scgraf;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.views.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(new MainView(stage), UIConfig.stageWidth, UIConfig.stageHeight);
        stage.setResizable(false);

        stage.setTitle("scgraf");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}