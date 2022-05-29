package com.scgraf.UI.views;

import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MainView extends BorderPane {
    public MainView(Stage stage) throws FileNotFoundException {
        super();
        setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        GraphView graphView = new GraphView(stage);
        FunctionsView functionsView = new FunctionsView();

        setCenter(graphView);
        setBottom(functionsView);
    }
}
