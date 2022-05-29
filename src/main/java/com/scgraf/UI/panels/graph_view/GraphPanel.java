package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.utils.UIUtils;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GraphPanel extends AnchorPane {
    Stage primaryStage;

    public GraphPanel(Graph graph, Stage stage) {
        super();
        primaryStage = stage;
        setSize(UIConfig.graphPanelWidth, UIConfig.graphPanelHeight);

        //setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        updateGraph(graph);
    }

    public void updateGraph(Graph graph){
        getChildren().clear();
        drawGraph(graph);
    }

    private void drawGraph(Graph graph){
        final int numCols = graph.getSize().width();
        final int numRows = graph.getSize().height();

        final double cellSizeWidth = ((getWidth()) / numCols);
        final double cellSizeHeight = ((getHeight()) / numRows);

        final double lightPathLimit = graph.getMaxConnectionWeight() / 3;
        final double mediumPathLimit = graph.getMaxConnectionWeight() *  2 / 3;

        final double cellSize;
        if(cellSizeWidth > cellSizeHeight){
            cellSize = cellSizeHeight;
            setSize(getHeight(), getHeight());
        }else{
            cellSize = cellSizeWidth;
            setSize(getWidth(), getWidth());
        }

        for(int y=0; y<numRows; y++)
            for(int x=0; x<numCols; x++){
                NodeElement node = new NodeElement(graph.getNode(y, x), cellSize, lightPathLimit, mediumPathLimit);
                setLeftAnchor(node, (double)( + cellSize * x));
                setTopAnchor(node, (double)( + cellSize * y));
                getChildren().add(node);
            }
        setSize(cellSize * numCols, cellSize * numRows);
    }

    private void setSize(double width, double height){
        UIUtils.setStaticSize(this, width, height);
        setWidth(width);
        setHeight(height);
    }
}
