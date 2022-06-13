package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.solver.Solver;
import com.scgraf.utils.Observer;
import com.scgraf.utils.UIUtils;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends AnchorPane {
    Stage primaryStage;

    private Graph graph;
    double cellSize;
    private NodeElement[][] drawNodes;
    private NodeElement[] drawPath;
    private final NodeElement[] drawChosenNodes;
    private int currChoosingNode = 0;

    public List<Observer<Node[]>> onNodeChooseNotify;

    public GraphPanel(Graph graph, Stage stage) {
        super();
        primaryStage = stage;
        setSize(UIConfig.graphPanelWidth, UIConfig.graphPanelHeight);

        drawChosenNodes = new NodeElement[2];
        onNodeChooseNotify = new ArrayList<>();

        Solver.getInstance().addGraphChangeObserver(this::updateGraph);
        Solver.getInstance().addPathDrawObserver(this::drawPath);
        Solver.getInstance().addPathCleanObserver(this::cleanPath);

        setOnMousePressed(this::onNodeChoose);

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
            drawPath[i + 1] = nextNode;
        }
    }

    public void cleanPath(Node[] path) {
        for (NodeElement n : drawChosenNodes)
            if(n != null) n.stopHighlight();
        if (drawPath == null) return;
        for (NodeElement nodeElement : drawPath)
            if(nodeElement != null) nodeElement.stopHighlight();
    }

    private void onNodeChoose(MouseEvent event){
        Solver.getInstance().cleanPath();

        final int x = (int)(Math.ceil(event.getX() / cellSize)-1);
        final int y = (int)(Math.ceil(event.getY() / cellSize)-1);
        NodeElement chosen = drawNodes[y][x];

        drawChosenNodes[(currChoosingNode++)%2] = chosen;

        Node[] chosenNodes = new Node[2];
        if(drawChosenNodes[0] != null) chosenNodes[0] = drawChosenNodes[0].getNode();
        if(drawChosenNodes[1] != null) chosenNodes[1] = drawChosenNodes[1].getNode();

        for(Observer<Node[]> obs : onNodeChooseNotify)
            obs.call(chosenNodes);

        if(drawChosenNodes[0] != null) drawChosenNodes[0].highlight();
        if(drawChosenNodes[1] != null) drawChosenNodes[1].highlight();
    }

    private void setSize(double width, double height) {
        UIUtils.setStaticSize(this, width, height);
        setWidth(width);
        setHeight(height);
    }

    public void addNodeChooseObserver(Observer<Node[]> obs) {
        onNodeChooseNotify.add(obs);
    }

    public void removeNodeChooseObserver(Observer<Node[]> obs) {
        onNodeChooseNotify.remove(obs);
    }
}
