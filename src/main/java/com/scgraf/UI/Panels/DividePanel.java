package com.scgraf.UI.Panels;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DividePanel extends FunctionAbstractPanel {
    public DividePanel(FunctionPanelManager root){
        super(root, "Dividd", "DIVIDE INFO");
        VBox inputPane = new VBox();
        TextField node1Input = new TextField();
        TextField node2Input = new TextField();
        inputPane.getChildren().addAll(node1Input, node2Input);
        buildFunctionView(inputPane);
    }
}
