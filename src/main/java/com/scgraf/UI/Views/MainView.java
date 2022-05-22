package com.scgraf.UI.Views;

import com.scgraf.UI.elements.FormattedButton;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainView extends VBox {

    public MainView() throws FileNotFoundException {
        super();

        FunctionsView functionsView = new FunctionsView();

        getChildren().addAll(functionsView);
    }
}
