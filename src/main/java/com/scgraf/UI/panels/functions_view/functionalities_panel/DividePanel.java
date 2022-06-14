package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.FormattedDropdown;
import com.scgraf.UI.elements.text.FormattedTextField;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.algorithms.divider.Divider;
import com.scgraf.logger.Logger;
import com.scgraf.solver.Solver;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class DividePanel extends FunctionAbstractPanel {
    FormattedTextField divideNumberInput;
    FormattedDropdown<Divider.DividingType> dividingTypeInput;

    public DividePanel(FunctionPanelManager root) {
        super(root, "Divide", "Pass into how many sub graphs You want to divide:");
        HBox inputPane = new HBox();

        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);

        divideNumberInput = new FormattedTextField("Number of sub graphs");
        dividingTypeInput = new FormattedDropdown<>(Divider.DividingType.values());

        inputPane.getChildren().addAll(divideNumberInput, dividingTypeInput);

        buildFunctionView(inputPane);
    }

    @Override
    public void solve() {
        try {
            final int n = Integer.parseInt(divideNumberInput.getText());
            final Divider.DividingType dividingType = dividingTypeInput.getValue();
            Solver.getInstance().divide(n, dividingType);
        } catch (NumberFormatException e) {
            Logger.getInstance().errPopup("Provided wrong input data!");
        }
    }
}
