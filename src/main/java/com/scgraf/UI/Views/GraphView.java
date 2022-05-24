package com.scgraf.UI.Views;

import com.scgraf.UI.Panels.GraphView.GraphInfoPanel;
import com.scgraf.UI.Panels.GraphView.GraphPanel;
import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GraphView extends VBox {
    public GraphView(Graph graph){
        super();
        setPrefWidth(UIConfig.stageWidth);
        setPadding(UIConfig.panelPadding);
        setSpacing(UIConfig.panelRegularSpacing);

        GraphInfoPanel infoPane = new GraphInfoPanel(graph);
        GraphPanel graphPanel = new GraphPanel(graph);

        getChildren().addAll(infoPane, graphPanel);
    }
}
