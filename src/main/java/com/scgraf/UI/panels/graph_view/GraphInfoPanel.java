package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Graph;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class GraphInfoPanel extends BorderPane {
    FormattedText graphInfo;
    FormattedText loggerInfo;

    public GraphInfoPanel(Graph graph) {
        super();
        setPrefWidth(UIConfig.graphPanelWidth);
        setPadding(UIConfig.topPanelPadding);

        graphInfo = new FormattedText();
        loggerInfo = new FormattedText();
        loggerInfo.setCaption("OK").setColor(UIConfig.okColor).setAlignment(TextAlignment.RIGHT).build();

        setLeft(graphInfo);
        setRight(loggerInfo);

        updateGraph(graph);
    }

    public void updateGraph(Graph graph){
        graphInfo.setText("Width: " + graph.getSize().width() +" Height: " + graph.getSize().height() +" Weight: " + graph.getMaxConnectionWeight());
    }

}