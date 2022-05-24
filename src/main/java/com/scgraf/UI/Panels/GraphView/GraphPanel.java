package com.scgraf.UI.Panels.GraphView;

import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GraphPanel extends GridPane {
    public GraphPanel(Graph graph) {
        super();
        setPrefHeight(UIConfig.stageHeight);
        setPrefWidth(UIConfig.stageWidth);

        setVgap(UIConfig.panelSmallSpacing);
        setHgap(UIConfig.panelSmallSpacing);

        setGridLinesVisible(true);

        updateGraph(graph);
    }

    public void updateGraph(Graph graph){
        getChildren().clear();
        drawGraph(graph);
    }

    private void drawGraph(Graph graph){
        final int numCols = graph.getSize().width() ;
        final int numRows = graph.getSize().height() ;
        final int nodeRadius = (UIConfig.stageWidth / numRows) / 6;

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            getRowConstraints().add(rowConst);
        }

        for(int i=0; i<graph.getSize().width(); i++)
            for(int y=0; y<graph.getSize().height(); y++){
                NodeElement node = new NodeElement(nodeRadius);
                add(node, y, i);
                setHalignment(node, HPos.CENTER);
                setValignment(node, VPos.CENTER);
            }

    }
}
