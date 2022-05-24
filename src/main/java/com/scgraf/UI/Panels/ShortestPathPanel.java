package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ShortestPathPanel extends FunctionAbstractPanel {
    public ShortestPathPanel(FunctionPanelManager root){
        super(root, "Find path", "The path will be find from start node to end node:");
        HBox inputPane = new HBox();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);
        FormattedTextField node1Input = new FormattedTextField("Start noded");
        FormattedTextField node2Input = new FormattedTextField("End node");
        inputPane.getChildren().addAll(node1Input, node2Input);
        buildFunctionView(inputPane);
    }
}
