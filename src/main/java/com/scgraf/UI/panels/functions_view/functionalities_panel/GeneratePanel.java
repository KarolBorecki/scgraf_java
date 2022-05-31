package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.logger.Logger;
import com.scgraf.solver.Solver;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class GeneratePanel extends FunctionAbstractPanel implements SolverCaller {
    FormattedTextField widthInput;
    FormattedTextField heightInput;
    FormattedTextField maxPathWeightInput;

    public GeneratePanel(FunctionPanelManager root) {
        super(root, "Generate", "Pass the parameters of generated graph:");
        HBox inputPane = new HBox();

        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);

        widthInput = new FormattedTextField("Width");
        heightInput = new FormattedTextField("Height");
        maxPathWeightInput = new FormattedTextField("Maximum path weight");

        inputPane.getChildren().addAll(widthInput, heightInput, maxPathWeightInput);
        buildFunctionView(inputPane);
    }

    @Override @FXML //TODO REFACTOR OMFG
    public void solve() {
        //TODO Handle wrong data
        final int width = Integer.parseInt(widthInput.getText());
        final int height = Integer.parseInt(heightInput.getText());
        final double maxWeight = Double.parseDouble(maxPathWeightInput.getText());

        Solver.getInstance().generate(width, height, maxWeight);
    }
}
