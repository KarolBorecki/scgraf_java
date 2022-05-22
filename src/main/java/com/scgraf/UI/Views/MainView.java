package com.scgraf.UI.Views;

import com.scgraf.UI.elements.FormattedButton;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainView extends BorderPane {

    public MainView() throws FileNotFoundException {
        super();

        GraphView graphView = new GraphView();
        FunctionsView functionsView = new FunctionsView();

        setTop(graphView);
        setBottom(functionsView);
    }
}
