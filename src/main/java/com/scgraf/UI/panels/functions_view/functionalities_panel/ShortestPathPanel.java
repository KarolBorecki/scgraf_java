package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.tuples.IdenticalPair;
import com.scgraf.logger.Logger;
import com.scgraf.solver.Solver;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ShortestPathPanel extends FunctionAbstractPanel {
    FormattedTextField startNodeIDIInput;
    FormattedTextField endNodeIDInput;

    public ShortestPathPanel(FunctionPanelManager root) {
        super(root, "Find path", "The path will be find from start node to end node:");
        HBox inputPane = new HBox();

        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);

        startNodeIDIInput = new FormattedTextField("Start node");
        endNodeIDInput = new FormattedTextField("End node");

        inputPane.getChildren().addAll(startNodeIDIInput, endNodeIDInput);

        Solver.getInstance().addNodeChooseObserver(this::updateValues);

        buildFunctionView(inputPane);
    }

    @Override
    public void solve() {
        try {
            final int startNodeID = Integer.parseInt(startNodeIDIInput.getText());
            final int endNodeID = Integer.parseInt(endNodeIDInput.getText());
            Solver.getInstance().findShortest(startNodeID, endNodeID);
        } catch (NumberFormatException e) {
            Logger.getInstance().errPopup("Provided wrong input data!");
        }
    }

    @Override
    public void updateValues(Object val) {
        IdenticalPair<Node> nodes;
        if(val instanceof IdenticalPair<?>) {
            nodes = (IdenticalPair<Node>) val;
            if (nodes.isFirstNotNull())
                startNodeIDIInput.setText("" + nodes.getFirst().getGraphID());
            if (nodes.isSecondNotNull())
                endNodeIDInput.setText("" + nodes.getSecond().getGraphID());
        }
    }
}
