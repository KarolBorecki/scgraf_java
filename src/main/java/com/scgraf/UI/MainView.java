package com.scgraf.UI;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainView extends VBox {

    public MainView(){
        super();

        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");

        getChildren().addAll(btn1,btn2);
    }
}
