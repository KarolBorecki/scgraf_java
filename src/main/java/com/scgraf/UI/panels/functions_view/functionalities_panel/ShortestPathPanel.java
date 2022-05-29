package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.UI.panels.functions_view.functionalities_panel.FunctionAbstractPanel;
import com.scgraf.solver.Solver;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ShortestPathPanel extends FunctionAbstractPanel {
    public ShortestPathPanel(FunctionPanelManager root){
        super(root, "Find path", "The path will be find from start node to end node:");
        HBox inputPane = new HBox();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);
        FormattedTextField node1Input = new FormattedTextField("Start noded");
        FormattedTextField node2Input = new FormattedTextField("End node");
        inputPane.getChildren().addAll(node1Input, node2Input);
        buildFunctionView(inputPane);
    }

    @Override
    public void solve() {
        //TODO change
        Solver.getInstance().findShortest(Solver.getInstance().getGraph().getNode(0,0), Solver.getInstance().getGraph().getNode(0,0));
    }
}
