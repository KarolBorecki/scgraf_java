package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class FunctionChoosePanel extends FunctionPanel {
    public FunctionChoosePanel(){
        super();
        FormattedButton shortestBtn = new FormattedButton("Find Shortest");
        FormattedButton divideBtn = new FormattedButton("Divide");
        FormattedButton consistencyCheckBtn = new FormattedButton("Check Consistency");
        FormattedButton GenerateBtn = new FormattedButton("Generate");

        getChildren().addAll(shortestBtn, divideBtn, consistencyCheckBtn, GenerateBtn);

    }
}
