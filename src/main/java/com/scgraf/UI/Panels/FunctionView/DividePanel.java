package com.scgraf.UI.Panels.FunctionView;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class DividePanel extends FunctionAbstractPanel {
    public DividePanel(FunctionPanelManager root) {
        super(root, "Divide", "Pass into how many sub graphs You want to divide:");
        VBox inputPane = new VBox();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);
        FormattedTextField divideNumberInput = new FormattedTextField("Number of sub graphs");
        inputPane.getChildren().addAll(divideNumberInput);
        buildFunctionView(inputPane);
    }
}
