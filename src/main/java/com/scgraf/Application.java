package com.scgraf;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.Views.MainView;
import com.scgraf.data_handling.FileReaderG;
import com.scgraf.data_structures.graph.Graph;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Application extends javafx.application.Application {
    class ResizableCanvas extends Canvas {

        public ResizableCanvas() {
            // Redraw canvas when size changes.
            widthProperty().addListener(evt -> draw());
            heightProperty().addListener(evt -> draw());
        }

        private void draw() {
            double width = getWidth();
            double height = getHeight();

            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, width, height);

            gc.setStroke(Color.RED);
            gc.strokeLine(0, 0, width, height);
            gc.strokeLine(0, height, width, 0);
        }

        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double height) {
            return getWidth();
        }

        @Override
        public double prefHeight(double width) {
            return getHeight();
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new MainView(), UIConfig.stageWidth, UIConfig.stageHeight);
        stage.setMinHeight(UIConfig.minStageHeight);
        stage.setMinWidth(UIConfig.minStageWidth);

        stage.setTitle("scgraf");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        try {
            FileReaderG fileReaderG = new FileReaderG(new File("example"));
            Graph g = fileReaderG.readGraphToFile();
            System.out.println(g);
        } catch (IOException | FileReaderG.FileFormatError e) {
            e.printStackTrace();
        }

    }
}