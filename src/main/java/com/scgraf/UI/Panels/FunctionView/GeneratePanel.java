package com.scgraf.UI.Panels.FunctionView;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class GeneratePanel extends FunctionAbstractPanel {
    public GeneratePanel(FunctionPanelManager root){
        super(root, "Generate", "Pass the parameters of generated graph:");
        HBox inputPane = new HBox();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);
        FormattedTextField widthInput = new FormattedTextField("Width");
        FormattedTextField heightInput = new FormattedTextField("Height");
        FormattedTextField maxPathWeightInput = new FormattedTextField("Maximum path weight");
        inputPane.getChildren().addAll(widthInput, heightInput, maxPathWeightInput);
        buildFunctionView(inputPane);
    }
}
