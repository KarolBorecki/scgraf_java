package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GeneratePanel extends FunctionAbstractPanel {
    public GeneratePanel(FunctionPanelManager root){
        super(root, "Generate", "GENERATE INFO");
        VBox inputPane = new VBox();
        TextField node1Input = new TextField();
        TextField node2Input = new TextField();
        inputPane.getChildren().addAll(node1Input, node2Input);
        buildFunctionView(inputPane);
    }
}
