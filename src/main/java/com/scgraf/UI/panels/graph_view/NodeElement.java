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
    private Line rightPathLine;
    private Line bottomPathLine;

    public NodeElement(Node node, double size, double maxWeight, boolean drawCaption) {
        super();
        this.node = node;
        this.maxWeight = maxWeight;

        double center = size / 2;

        setWidth(size);
        setMinWidth(size);
        setMaxWidth(size);
        setHeight(size);
        setMinHeight(size);
        setMaxHeight(size);

        Pane paths = new Pane();
        for (int i = Path.Side.RIGHT.index; i <= Path.Side.BOTTOM.index; i++) {
            Path p = node.getPath(i);
            if (p != null) {
                double x = i == Path.Side.RIGHT.index ? center * UIConfig.graphNodePathSizeFactor : 0;
                double y = i == Path.Side.BOTTOM.index ? center * UIConfig.graphNodePathSizeFactor : 0;
                Line line = new Line(center, center, center + x, center + y);
                line.setStrokeWidth(Math.max(size / 30, UIConfig.minPathWidth));
                line.setStroke(getPathColor(p.getWeight(), maxWeight));
                paths.getChildren().add(line);

                if (i == Path.Side.RIGHT.index) rightPathLine = line;
                else if (i == Path.Side.BOTTOM.index) bottomPathLine = line;
            }
        }

        circle = new Circle(size / 4);
        circle.setFill(UIConfig.graphNodeColor);
        getChildren().addAll(paths, circle);

        if (drawCaption) {
            FormattedText caption = new FormattedText(node.getGraphID() + "");
            caption.setColor(UIConfig.functionsViewBckColor).setFontSize((int) (size / 4)).build();
            getChildren().add(caption);
        }
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
        circle.setFill(UIConfig.graphNodeColor);
        if (rightPathLine != null)
            rightPathLine.setStroke(getPathColor(node.getConnectionWeight(Path.Side.RIGHT), maxWeight));
        if (bottomPathLine != null)
            bottomPathLine.setStroke(getPathColor(node.getConnectionWeight(Path.Side.BOTTOM), maxWeight));
    }

    private static Color getPathColor(double weight, double maxWeight) {
        final double lightHUE = Color.GREEN.getHue();
        final double heavyHUE = Color.RED.getHue();
        double hue = lightHUE + (heavyHUE - lightHUE) * weight / maxWeight;
        return Color.hsb(hue, UIConfig.hsbPathSaturation, UIConfig.hsbPathBrightness); //TODO
    }

    public Node getNode(){
        return node;
    }
}
