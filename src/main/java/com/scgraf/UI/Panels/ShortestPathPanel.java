package com.scgraf.UI.Panels;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ShortestPathPanel extends FunctionAbstractPanel {
    public ShortestPathPanel(FunctionPanelManager root){
        super(root, "Shortest", "SHORTEST INFO");
        HBox inputPane = new HBox();
        TextField node1Input = new TextField();
        TextField node2Input = new TextField();
        inputPane.getChildren().addAll(node1Input, node2Input);
        buildFunctionView(inputPane);
    }
}
