package com.scgraf.UI.Views;

import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;

import java.io.FileNotFoundException;

public class MainView extends BorderPane {

    public MainView() throws FileNotFoundException {
        super();
        setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        GraphView graphView = new GraphView();
        FunctionsView functionsView = new FunctionsView();

        setTop(graphView);
        setBottom(functionsView);
    }
}
