package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class NodeElement extends StackPane {
    private final Node drawnNode;
    private final double maxWeight;

    private final Circle nodeCircle;
    private final Pane pathsPane;

    private Line rightPathLine;
    private Line bottomPathLine;

    public NodeElement(Node drawnNode, double size, double maxWeight, boolean drawCaption) {
        super();
        this.drawnNode = drawnNode;
        this.maxWeight = maxWeight;

        double center = size / 2;

        setMinWidth(size);
        setMaxWidth(size);
        setMinHeight(size);
        setMaxHeight(size);

        pathsPane = new Pane();
        Path path;
        if ((path = drawnNode.getPath(Path.Side.RIGHT)) != null)
            rightPathLine = addPath(center, center * UIConfig.graphNodePathLengthFactor, 0, size * UIConfig.graphNodePathStrokeFactor, path.getWeight());
        if ((path = drawnNode.getPath(Path.Side.BOTTOM)) != null)
            bottomPathLine = addPath(center, 0, center * UIConfig.graphNodePathLengthFactor, size * UIConfig.graphNodePathStrokeFactor, path.getWeight());

        nodeCircle = new Circle(size * UIConfig.graphNodeSizeFactor);
        nodeCircle.setFill(UIConfig.graphNodeColor);

        getChildren().addAll(pathsPane, nodeCircle);

        if (drawCaption) {
            FormattedText caption = new FormattedText(drawnNode.getGraphID() + "");
            caption.setColor(UIConfig.functionsViewBckColor).setFontSize((int) (size / 4)).build();
            getChildren().add(caption);
        }
    }

    private Line addPath(double center, double x, double y, double stroke, double weight) {
        Line line = new Line(center, center, center + x, center + y);

        line.setStrokeWidth(Math.max(stroke, UIConfig.minPathWidth));
        line.setStroke(getPathColor(weight, maxWeight));

        pathsPane.getChildren().add(line);

        return line;
    }

    private static Color getPathColor(double weight, double maxWeight) {
        final double lightHUE = Color.GREEN.getHue();
        final double heavyHUE = Color.RED.getHue();
        double hue = lightHUE + (heavyHUE - lightHUE) * weight / maxWeight;
        return Color.hsb(hue, UIConfig.hsbPathSaturation, UIConfig.hsbPathBrightness);
    }

    public void highlightRight() {
        if (rightPathLine != null) rightPathLine.setStroke(UIConfig.graphShortestPathColor);
    }

    public void highlightBottom() {
        if (bottomPathLine != null) bottomPathLine.setStroke(UIConfig.graphShortestPathColor);
    }

    public void highlight() {
        nodeCircle.setFill(UIConfig.graphShortestPathColor);
    }

    public void stopHighlight() {
        if (drawnNode == null) return;
        nodeCircle.setFill(UIConfig.graphNodeColor);
        if (rightPathLine != null)
            rightPathLine.setStroke(getPathColor(drawnNode.getConnectionWeight(Path.Side.RIGHT), maxWeight));
        if (bottomPathLine != null)
            bottomPathLine.setStroke(getPathColor(drawnNode.getConnectionWeight(Path.Side.BOTTOM), maxWeight));
    }

    public Node getDrawnNode() {
        return drawnNode;
    }
}
