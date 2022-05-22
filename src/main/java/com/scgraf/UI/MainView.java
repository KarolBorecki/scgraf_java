package com.scgraf.UI;

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

        FormattedButton btn1 = new FormattedButton("Button 1");
        FormattedButton btn2 = new FormattedButton("Button 2");

        getChildren().addAll(btn1,btn2);
    }
}
