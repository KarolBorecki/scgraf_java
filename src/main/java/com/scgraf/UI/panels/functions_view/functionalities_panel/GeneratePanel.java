package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.FormattedDropdown;
import com.scgraf.UI.elements.buttons.FormattedRadioButton;
import com.scgraf.UI.elements.text.FormattedTextField;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.generator.GraphGenerator;
import com.scgraf.logger.Logger;
import com.scgraf.solver.Solver;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

public class GeneratePanel extends FunctionAbstractPanel implements SolverCaller {
    FormattedTextField widthInput;
    FormattedTextField heightInput;
    FormattedTextField maxPathWeightInput;
    FormattedDropdown<GraphGenerator.GeneratingType> generatingType;

    public GeneratePanel(FunctionPanelManager root) {
        super(root, "Generate", "Pass the parameters of generated graph:");
        HBox inputPane = new HBox();

        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);

        widthInput = new FormattedTextField("Width");
        heightInput = new FormattedTextField("Height");
        maxPathWeightInput = new FormattedTextField("Weight");
        generatingType = new FormattedDropdown<>(GraphGenerator.GeneratingType.values());

        inputPane.getChildren().addAll(widthInput, heightInput, maxPathWeightInput, generatingType);
        buildFunctionView(inputPane);
    }

    @Override
    public void solve() {
        try {
            final int width = Integer.parseInt(widthInput.getText());
            final int height = Integer.parseInt(heightInput.getText());
            final double maxWeight = Double.parseDouble(maxPathWeightInput.getText());
            final GraphGenerator.GeneratingType type = generatingType.getValue();
            Solver.getInstance().generate(width, height, maxWeight, type);
        }catch (NumberFormatException e) {
            Logger.getInstance().errPopup("Provided wrong input data!");
        }

    }
}
