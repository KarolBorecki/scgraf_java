package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.RegularButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AllFunctionsPanel  extends HBox {
    public AllFunctionsPanel(FunctionPanelManager root){
        super();
        setSpacing(UIConfig.panelRegularSpacing);
        setAlignment(Pos.CENTER);

        RegularButton shortestBtn = new RegularButton("Find Shortest");
        RegularButton divideBtn = new RegularButton("Divide");
        RegularButton consistencyCheckBtn = new RegularButton("Check Consistency");
        RegularButton generateBtn = new RegularButton("Generate");

        shortestBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.SHORTEST));
        divideBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.DIVIDE));
        consistencyCheckBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.CONSISTENCY));
        generateBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.GENERATE));

        getChildren().addAll(shortestBtn, divideBtn, consistencyCheckBtn, generateBtn);

    }
}