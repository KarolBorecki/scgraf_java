package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.solver.Solver;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ConsistencyPanel extends FunctionAbstractPanel {
    public ConsistencyPanel(FunctionPanelManager root) {
        super(root, "Check consistency", "The consistency will be checked:");
        VBox inputPane = new VBox();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);
        buildFunctionView(inputPane);
    }

    @Override
    public void solve() {
        Solver.getInstance().checkConsistency();
    }
}
