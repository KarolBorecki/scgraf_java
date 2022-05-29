package com.scgraf.UI.Views;

import com.scgraf.UI.Panels.GraphView.GraphInfoPanel;
import com.scgraf.UI.Panels.GraphView.GraphPanel;
import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.utils.UIUtils;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphView extends BorderPane {
    public GraphView(Graph graph, Stage stage){
        super();
        UIUtils.setStaticSize(this, UIConfig.graphPanelWidth, 0);

        GraphInfoPanel infoPane = new GraphInfoPanel(graph);
        GraphPanel graphPanel = new GraphPanel(graph, stage);

        setCenter(graphPanel);
        setTop(infoPane);
    }
}
