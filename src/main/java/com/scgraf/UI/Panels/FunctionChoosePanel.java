package com.scgraf.UI.Panels;

import com.scgraf.UI.elements.FormattedButton;
import javafx.scene.layout.HBox;

public class FunctionChoosePanel extends HBox {
    public FunctionChoosePanel(){
        super();

        FormattedButton shortestBtn = new FormattedButton("Find Shortest");
        FormattedButton divideBtn = new FormattedButton("Divide");
        FormattedButton consistencyCheckBtn = new FormattedButton("Check Consistency");
        FormattedButton GenerateBtn = new FormattedButton("Generate");

        getChildren().addAll(shortestBtn, divideBtn, consistencyCheckBtn, GenerateBtn);
    }
}
