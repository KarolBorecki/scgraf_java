package com.scgraf.UI.Panels.GraphView;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.Center;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;

public class NodeElement extends StackPane {
    public NodeElement(int radius, Node node, int width) {
        super();
        Group paths = new Group();

        Circle circle = new Circle(0,0, radius);
        circle.setFill(UIConfig.graphNodeColor);

        Center circleCenter = new Center(circle);

        FormattedText caption = new FormattedText();
        caption.setCaption(node.getGraphID() + "").setAlignment(TextAlignment.CENTER).setFontSize(radius).setColor(UIConfig.functionsViewBckColor).build();

        for (Path.Side side : Path.Side.values()) {
            Path p = node.getPath(side);
            if (p != null) {
                int x = 0, y = 0;
                switch (side) {
                    case TOP -> y = (int)(-radius * UIConfig.graphNodePathSizeFactor);
                    case RIGHT -> x = (int)(radius * UIConfig.graphNodePathSizeFactor);
                    case BOTTOM -> y = (int)(radius * UIConfig.graphNodePathSizeFactor);
                    case LEFT -> x = (int)(-radius * UIConfig.graphNodePathSizeFactor);
                }
                Line line = new Line();
                line.setStartX(0);
                line.setStartY(0);
                line.setEndX(x);
                line.setEndY(y);
                line.setStroke(Color.RED);
                line.setStrokeWidth(radius/5);
                paths.getChildren().add(line);
            }
        }

        getChildren().addAll(paths, circle, caption);
    }
}
