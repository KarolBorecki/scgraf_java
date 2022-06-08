package com.scgraf.UI.views;

import com.scgraf.UI.panels.graph_view.GraphInfoPanel;
import com.scgraf.UI.panels.graph_view.GraphPanel;
import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.solver.Solver;
import com.scgraf.utils.Observer;
import com.scgraf.utils.UIUtils;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GraphView extends BorderPane {
    public GraphView(Stage stage){
        super();
        UIUtils.setStaticSize(this, UIConfig.graphPanelWidth, 0);

        Graph startGraph = Solver.getInstance().getGraph();

        GraphInfoPanel infoPane = new GraphInfoPanel(startGraph);
        GraphPanel graphPanel = new GraphPanel(startGraph, stage);

        setCenter(graphPanel);
        setTop(infoPane);
    }
}
