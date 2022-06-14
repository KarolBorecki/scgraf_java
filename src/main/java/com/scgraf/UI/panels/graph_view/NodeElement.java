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
    private final Node node;
    private final double maxWeight;

    private final Circle circle;
    private final Pane paths;

    private Line rightPathLine;
    private Line bottomPathLine;

    public NodeElement(Node node, double size, double maxWeight, boolean drawCaption) {
        super();
        this.node = node;
        this.maxWeight = maxWeight;

        double center = size / 2;

        setMinWidth(size);
        setMaxWidth(size);
        setMinHeight(size);
        setMaxHeight(size);

        paths = new Pane();
        Path path;
        if((path = node.getPath(Path.Side.RIGHT)) != null)
            rightPathLine = addPath(center, center * UIConfig.graphNodePathLengthFactor, 0, size * UIConfig.graphNodePathStrokeFactor, path.getWeight());
        if((path = node.getPath(Path.Side.BOTTOM)) != null)
            bottomPathLine = addPath(center, 0, center * UIConfig.graphNodePathLengthFactor, size * UIConfig.graphNodePathStrokeFactor, path.getWeight());

        circle = new Circle(size * UIConfig.graphNodeSizeFactor);
        circle.setFill(UIConfig.graphNodeColor);

        getChildren().addAll(paths, circle);

        if (drawCaption) {
            FormattedText caption = new FormattedText(node.getGraphID() + "");
            caption.setColor(UIConfig.functionsViewBckColor).setFontSize((int) (size / 4)).build();
            getChildren().add(caption);
        }
    }

    private Line addPath(double center, double x, double y, double stroke, double weight){
        Line line = new Line(center, center, center + x, center + y);
        line.setStrokeWidth(Math.max(stroke, UIConfig.minPathWidth));
        line.setStroke(getPathColor(weight, maxWeight));
        paths.getChildren().add(line);
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
        circle.setFill(UIConfig.graphShortestPathColor);
    }

    public void stopHighlight() {
        if(node == null) return;
        circle.setFill(UIConfig.graphNodeColor);
        if (rightPathLine != null)
            rightPathLine.setStroke(getPathColor(node.getConnectionWeight(Path.Side.RIGHT), maxWeight));
        if (bottomPathLine != null)
            bottomPathLine.setStroke(getPathColor(node.getConnectionWeight(Path.Side.BOTTOM), maxWeight));
    }

    public Node getNode() {
        return node;
    }
}
