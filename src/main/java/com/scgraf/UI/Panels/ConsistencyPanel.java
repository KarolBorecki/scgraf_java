package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ConsistencyPanel extends FunctionAbstractPanel {
    public ConsistencyPanel(FunctionPanelManager root){
        super(root, "Check consistency", "The consistency will be checked:");
        VBox inputPane = new VBox();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);
        buildFunctionView(inputPane);
    }
}
