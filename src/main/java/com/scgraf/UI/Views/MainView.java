package com.scgraf.UI.Views;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
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
