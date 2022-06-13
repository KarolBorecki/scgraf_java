package com.scgraf.UI.views;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.panels.graph_view.GraphInfoPanel;
import com.scgraf.UI.panels.graph_view.GraphPanel;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.solver.Solver;
import com.scgraf.utils.UIUtils;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GraphView extends BorderPane {
    GraphInfoPanel infoPane;
    GraphPanel graphPanel;

    public GraphView(Stage stage) {
        super();
        UIUtils.setStaticSize(this, UIConfig.graphPanelWidth, 0);

        Graph startGraph = Solver.getInstance().getGraph();

        infoPane = new GraphInfoPanel(startGraph);
        graphPanel = new GraphPanel(startGraph, stage);
        graphPanel.addNodeChooseObserver(nodes -> Solver.getInstance().onNodeChoose(nodes));

        setCenter(graphPanel);
        setTop(infoPane);
    }
}
