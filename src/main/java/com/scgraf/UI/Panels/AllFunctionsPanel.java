package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AllFunctionsPanel  extends HBox {
    private FunctionPanel root;

    public AllFunctionsPanel(FunctionPanel root){
        super();
        setSpacing(UIConfig.panelSpacing);
        setAlignment(Pos.CENTER);

        this.root = root;

        FormattedButton shortestBtn = new FormattedButton("Find Shortest");
        FormattedButton divideBtn = new FormattedButton("Divide");
        FormattedButton consistencyCheckBtn = new FormattedButton("Check Consistency");
        FormattedButton GenerateBtn = new FormattedButton("Generate");

        shortestBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanel.Functionality.SHORTEST));
        //shortestBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanel.Functionality.SHORTEST));

        getChildren().addAll(shortestBtn, divideBtn, consistencyCheckBtn, GenerateBtn);

    }
}