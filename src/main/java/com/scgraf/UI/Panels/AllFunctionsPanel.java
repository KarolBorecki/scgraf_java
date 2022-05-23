package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AllFunctionsPanel  extends HBox {
    public AllFunctionsPanel(FunctionPanelManager root){
        super();
        setSpacing(UIConfig.panelSpacing);
        setAlignment(Pos.CENTER);

        FormattedButton shortestBtn = new FormattedButton("Find Shortest");
        FormattedButton divideBtn = new FormattedButton("Divide");
        FormattedButton consistencyCheckBtn = new FormattedButton("Check Consistency");
        FormattedButton generateBtn = new FormattedButton("Generate");

        shortestBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.SHORTEST));
        divideBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.DIVIDE));
        consistencyCheckBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.CONSISTENCY));
        generateBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.GENERATE));

        getChildren().addAll(shortestBtn, divideBtn, consistencyCheckBtn, generateBtn);

    }
}