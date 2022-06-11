package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.solver.Solver;
import com.scgraf.utils.UIUtils;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraphPanel extends AnchorPane {
    Stage primaryStage;

    private Graph graph;
    private NodeElement[][] drawNodes;
    private NodeElement[] drawPath;

    public GraphPanel(Graph graph, Stage stage) {
        super();
        primaryStage = stage;
        setSize(UIConfig.graphPanelWidth, UIConfig.graphPanelHeight);

        Solver.getInstance().addGraphChangeObserver(this::updateGraph);
        Solver.getInstance().addPathDrawObserver(this::drawPath);
        Solver.getInstance().addPathCleanObserver(this::cleanPath);

        updateGraph(graph);
    }

    public void updateGraph(Graph graph) {
        getChildren().clear();
        this.graph = graph;
        drawGraph(graph);
    }

    private void drawGraph(Graph graph) {
        setSize(UIConfig.graphPanelWidth, UIConfig.graphPanelHeight);

        final int numCols = graph.getSize().width();
        final int numRows = graph.getSize().height();

        final double cellSizeWidth = ((getWidth()) / numCols);
        final double cellSizeHeight = ((getHeight()) / numRows);

        double cellSize;
        if (cellSizeWidth > cellSizeHeight) {
            cellSize = cellSizeHeight;
            setSize(getHeight(), getHeight());
        } else {
            cellSize = cellSizeWidth;
            setSize(getWidth(), getWidth());
        }

        drawNodes = new NodeElement[numRows][numCols];

        boolean drawCaptions = graph.getNodesCount() <= UIConfig.maxGraphNodesCountToDrawCaptions;
        for (int y = 0; y < numRows; y++)
            for (int x = 0; x < numCols; x++) {
                drawNodes[y][x] = new NodeElement(graph.getNode(y, x), cellSize, graph.getMaxPathWeight(), drawCaptions);
                setLeftAnchor(drawNodes[y][x], cellSize * x);
                setTopAnchor(drawNodes[y][x], cellSize * y);

                getChildren().add(drawNodes[y][x]);
            }
        setSize(cellSize * numCols, cellSize * numRows);
    }

    public void drawPath(Node[] pathNodes) {
        drawPath = new NodeElement[pathNodes.length];
        for (int i = 0; i < pathNodes.length - 1; i++) {
            NodeElement actualNode = drawNodes[graph.getNodeX(pathNodes[i])][graph.getNodeY(pathNodes[i])];
            NodeElement nextNode = drawNodes[graph.getNodeX(pathNodes[i + 1])][graph.getNodeY(pathNodes[i + 1])];
            actualNode.highlight();
            nextNode.highlight();
            try {
                Path.Side side = graph.getPathSideBetween(pathNodes[i], pathNodes[i + 1]);
                if (side == Path.Side.RIGHT) actualNode.highlightRight();
                else if (side == Path.Side.BOTTOM) actualNode.highlightBottom();
                else if (side == Path.Side.TOP) nextNode.highlightBottom();
                else if (side == Path.Side.LEFT) nextNode.highlightRight();
            } catch (Graph.InvalidMeshConnection e) {
                e.printStackTrace();
            }
            drawPath[i] = actualNode;
            drawPath[i+1] = nextNode;
        }
    }

    public void cleanPath(Node[] path){
        if(drawPath == null) return;
System.out.println("CLEANING PATH");
        for (NodeElement nodeElement : drawPath)
            nodeElement.stopHighlight();
    }

    private void setSize(double width, double height) {
        UIUtils.setStaticSize(this, width, height);
        setWidth(width);
        setHeight(height);
    }
}
