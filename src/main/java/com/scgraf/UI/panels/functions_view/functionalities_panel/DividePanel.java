package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.solver.Solver;
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

    @Override
    public void solve() {
        //TODO change
        Solver.getInstance().divide(2);
    }
}
