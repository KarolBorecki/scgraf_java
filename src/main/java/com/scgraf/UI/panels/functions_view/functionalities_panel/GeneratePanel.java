package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.solver.Solver;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class GeneratePanel extends FunctionAbstractPanel implements SolverCaller {
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

    @Override
    public void solve() {
        Solver.getInstance().generate(10, 10, 10);
    }
}
