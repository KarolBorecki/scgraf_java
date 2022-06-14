package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.tuples.SiblingPair;
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
    private final SiblingPair<NodeElement> drawChosenNodes;

    public List<Observer<SiblingPair<Node>>> onNodeChooseNotify;

    public GraphPanel(Graph graph, Stage stage) {
        super();
        primaryStage = stage;
        setSize(UIConfig.graphPanelWidth, UIConfig.graphPanelHeight);

        drawChosenNodes = new SiblingPair<>();
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

        cellSize = Math.min(cellSizeWidth, cellSizeHeight);
        setSize(cellSize * numCols, cellSize * numRows);

        drawNodes = new NodeElement[numRows][numCols];

        boolean drawCaptions = graph.getNodesCount() <= UIConfig.maxGraphNodesCountToDrawCaptions;
        for (int row = 0; row < numRows; row++)
            for (int column = 0; column < numCols; column++) {
                drawNodes[row][column] = new NodeElement(graph.getNode(row, column), cellSize, graph.getMaxPathWeight(), drawCaptions);
                setLeftAnchor(drawNodes[row][column], cellSize * column);
                setTopAnchor(drawNodes[row][column], cellSize * row);

                getChildren().add(drawNodes[row][column]);
            }
    }

    public void drawPath(Node[] pathNodes) {
        drawPath = new NodeElement[pathNodes.length];

        for (int i = 0; i < pathNodes.length - 1; i += 2) {
            NodeElement actualNode = drawNodes[graph.getNodeRow(pathNodes[i])][graph.getNodeColumn(pathNodes[i])];
            NodeElement nextNode = drawNodes[graph.getNodeRow(pathNodes[i + 1])][graph.getNodeColumn(pathNodes[i + 1])];

            actualNode.highlight();
            nextNode.highlight();

            try {
                Path.Side side = graph.getPathSideBetween(pathNodes[i], pathNodes[i + 1]);
                if (side == Path.Side.RIGHT) actualNode.highlightRight();
                else if (side == Path.Side.BOTTOM) actualNode.highlightBottom();
                else if (side == Path.Side.TOP) nextNode.highlightBottom();
                else if (side == Path.Side.LEFT) nextNode.highlightRight();
            } catch (Graph.InvalidMeshConnection e) {
                System.err.println("Draw Path problem: " + e.getMessage());
            }

            drawPath[i] = actualNode;
            drawPath[i + 1] = nextNode;
        }
    }

    public void cleanPath(Node[] path) {
        if (drawPath == null) return;

        for (NodeElement nodeElement : drawPath)
            if (nodeElement != null) nodeElement.stopHighlight();

        highlightChosenNodes();
    }

    private void highlightChosenNodes() {
        if (drawChosenNodes.isFirstNotNull()) drawChosenNodes.getFirst().highlight();
        if (drawChosenNodes.isSecondNotNull()) drawChosenNodes.getSecond().highlight();
    }

    public void cleanChosenNodes() {
        if (drawChosenNodes.isFirstNotNull()) drawChosenNodes.getFirst().stopHighlight();
        if (drawChosenNodes.isSecondNotNull()) drawChosenNodes.getSecond().stopHighlight();
    }

    private void onNodeChoose(MouseEvent event) {
        Solver.getInstance().cleanPath();
        cleanChosenNodes();

        final int column = (int) (Math.ceil(event.getX() / cellSize) - 1);
        final int row = (int) (Math.ceil(event.getY() / cellSize) - 1);
        NodeElement chosen = drawNodes[row][column];

        drawChosenNodes.setNext(chosen);

        SiblingPair<Node> chosenNodes = new SiblingPair<>();
        if (drawChosenNodes.isFirstNotNull()) chosenNodes.setFirst(drawChosenNodes.getFirst().getDrawnNode());
        if (drawChosenNodes.isSecondNotNull()) chosenNodes.setSecond(drawChosenNodes.getSecond().getDrawnNode());

        for (Observer<SiblingPair<Node>> obs : onNodeChooseNotify)
            obs.call(chosenNodes);

        highlightChosenNodes();
    }

    private void setSize(double width, double height) {
        UIUtils.setStaticSize(this, width, height);
        setWidth(width);
        setHeight(height);
    }

    public void addNodeChooseObserver(Observer<SiblingPair<Node>> obs) {
        onNodeChooseNotify.add(obs);
    }

    public void removeNodeChooseObserver(Observer<SiblingPair<Node>> obs) {
        onNodeChooseNotify.remove(obs);
    }
}
