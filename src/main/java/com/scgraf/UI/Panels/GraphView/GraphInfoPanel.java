package com.scgraf.UI.Panels.GraphView;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Graph;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

public class GraphInfoPanel extends BorderPane {
    FormattedText graphInfo;
    FormattedText loggerInfo;

    public GraphInfoPanel(Graph graph) {
        super();
        setPrefWidth(UIConfig.stageWidth);

        graphInfo = new FormattedText();
        loggerInfo = new FormattedText("OK", TextAlignment.RIGHT);
        loggerInfo.setText("OK", UIConfig.okColor);

        setLeft(graphInfo);
        setRight(loggerInfo);

        updateGraph(graph);
    }

    public void updateGraph(Graph graph){
        graphInfo.setText("Width: " + graph.getSize().width() +" Height: " + graph.getSize().height() +" Weight: " + graph.getMaxConnectionWeight());
    }

}
