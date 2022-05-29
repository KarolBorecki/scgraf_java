package com.scgraf.UI.Views;

import com.scgraf.UI.Panels.GraphView.GraphInfoPanel;
import com.scgraf.UI.Panels.GraphView.GraphPanel;
import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.solver.Solver;
import com.scgraf.utils.Observer;
import com.scgraf.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.Callable;

public class GraphView extends BorderPane implements Observer<Graph> {
    private final Graph graph;

    private final GraphPanel graphPanel;
    private final GraphInfoPanel infoPane;

    public GraphView(Stage stage){
        super();
        UIUtils.setStaticSize(this, UIConfig.graphPanelWidth, 0);

        graph = Solver.getInstance().getGraph();
        Solver.getInstance().addGraphChangeObserver(this);

        infoPane = new GraphInfoPanel(graph);
        graphPanel = new GraphPanel(graph, stage);

        setCenter(graphPanel);
        setTop(infoPane);
    }

    @Override
    public void call(Graph graph){
        updateGraph(graph);
    }

    private void updateGraph(Graph graph){
        infoPane.updateGraph(graph);
        graphPanel.updateGraph(graph);
    }
}
