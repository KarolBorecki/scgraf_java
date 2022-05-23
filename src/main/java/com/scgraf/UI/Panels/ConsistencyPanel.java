package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ConsistencyPanel extends FunctionAbstractPanel {
    public ConsistencyPanel(FunctionPanelManager root){
        super(root, "CONSISTENCY", "CONSISTENCY INFO");
        VBox inputPane = new VBox();
        TextField node1Input = new TextField();
        TextField node2Input = new TextField();
        inputPane.getChildren().addAll(node1Input, node2Input);
        buildFunctionView(inputPane);
    }
}
