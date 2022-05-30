package com.scgraf.UI.views;

import com.scgraf.Application;
import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;

public class MainView extends BorderPane {
    public MainView() {
        super();
        setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        GraphView graphView = new GraphView(Application.stage);
        FunctionsView functionsView = new FunctionsView();

        setCenter(graphView);
        setBottom(functionsView);

    }
}
