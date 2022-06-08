package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.solver.Solver;
import com.scgraf.utils.UIUtils;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GraphPanel extends AnchorPane {
    Stage primaryStage;

    private Graph graph;

    private NodeElement[][] nodeElements;
    private Size graphSize;
    private double cellSize;

    public GraphPanel(Graph graph, Stage stage) {
        super();
        primaryStage = stage;
        setSize(UIConfig.graphPanelWidth, UIConfig.graphPanelHeight);

        Solver.getInstance().addGraphChangeObserver(this::updateGraph);
        Solver.getInstance().addPathDrawObserver(this::drawPath);

        updateGraph(graph);
    }

    public void updateGraph(Graph graph){
        getChildren().clear();
        this.graph = graph;
        drawGraph(graph, null);
    }

    private void drawGraph(Graph graph, Node[] path){
        setSize(UIConfig.graphPanelWidth, UIConfig.graphPanelHeight);

        graphSize = graph.getSize();
        final int numCols = graph.getSize().width();
        final int numRows = graph.getSize().height();

        final double cellSizeWidth = ((getWidth()) / numCols);
        final double cellSizeHeight = ((getHeight()) / numRows);

        if(cellSizeWidth > cellSizeHeight){
            cellSize = cellSizeHeight;
            setSize(getHeight(), getHeight());
        }else{
            cellSize = cellSizeWidth;
            setSize(getWidth(), getWidth());
        }

        nodeElements = new NodeElement[numRows][numCols];

        final double lightPathLimit = graph.getMaxConnectionWeight() / 3;
        final double mediumPathLimit = graph.getMaxConnectionWeight() *  2 / 3;

        boolean drawCaptions = graph.getNodesCount() <= UIConfig.maxGraphNodesCountToDrawCaptions;
        for(int y=0; y<numRows; y++)
            for(int x=0; x<numCols; x++){
                nodeElements[y][x] = new NodeElement(graph.getNode(y, x), cellSize, graph.getMaxConnectionWeight(), drawCaptions);
                setLeftAnchor(nodeElements[y][x], cellSize * x);
                setTopAnchor(nodeElements[y][x], cellSize * y);

                getChildren().add(nodeElements[y][x]);
            }
        setSize(cellSize * numCols, cellSize * numRows);
    }

    public void drawPath(Node[] nodes){
        System.out.println("Drawinf path");
        for(int i=0; i<nodes.length-1; i++){
            NodeElement actualNode = nodeElements[graph.getNodeX(nodes[i])][graph.getNodeY(nodes[i])];
            NodeElement nextNode = nodeElements[graph.getNodeX(nodes[i+1])][graph.getNodeY(nodes[i+1])];
            actualNode.highlight();
            nextNode.highlight();
            try {
                Path.Side side = graph.getPathSideForConnection(nodes[i], nodes[i+1]);
                if(side == Path.Side.RIGHT) actualNode.highlightRight();
                else if(side == Path.Side.BOTTOM) actualNode.highlightBottom();
                else if(side == Path.Side.TOP) nextNode.highlightBottom();
                else if(side == Path.Side.LEFT) nextNode.highlightRight();
            } catch (Graph.InvalidMeshConnection e) {
                e.printStackTrace();
            }
        }
    }

    private void setSize(double width, double height){
        UIUtils.setStaticSize(this, width, height);
        setWidth(width);
        setHeight(height);
    }
}
