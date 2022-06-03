package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class NodeElement extends StackPane {
    public NodeElement(Node node, double size, double lightLimit, double mediumLimit, boolean drawCaption) {
        super();
        double center = size / 2;

        setWidth(size);
        setMinWidth(size);
        setMaxWidth(size);
        setHeight(size);
        setMinHeight(size);
        setMaxHeight(size);

        Pane paths = new Pane();
        for (int i=Path.Side.RIGHT.index; i<=Path.Side.BOTTOM.index; i++) {
            Path p = node.getPath(i);
            if (p != null) {
                double x = i==Path.Side.RIGHT.index ? center * UIConfig.graphNodePathSizeFactor : 0;
                double y = i==Path.Side.BOTTOM.index ? center * UIConfig.graphNodePathSizeFactor : 0;

                Line line = new Line(center, center , center + x, center + y);
                if(p.getWeight() < lightLimit) line.setStroke(UIConfig.graphLightPathColor);
                else if(p.getWeight() < mediumLimit) line.setStroke(UIConfig.graphMediumPathColor);
                else line.setStroke(UIConfig.graphHeavyPathColor);
                line.setStrokeWidth(Math.max(size / 30, UIConfig.minPathWidth));
                paths.getChildren().add(line);
            }
        }

        Circle circle = new Circle(size / 4);
        circle.setFill(UIConfig.graphNodeColor);
        getChildren().addAll(paths, circle);

        if (drawCaption){
            FormattedText caption = new FormattedText(node.getGraphID() + "");
            caption.setColor(UIConfig.functionsViewBckColor).setFontSize((int) (size / 4)).build();
            getChildren().add(caption);
        }
    }
}
