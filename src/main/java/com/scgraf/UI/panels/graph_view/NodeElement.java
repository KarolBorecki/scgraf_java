package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class NodeElement extends StackPane {
    public NodeElement(Node node, double size, double lightLimit, double mediumLimit) {
        super();
        double center = size/2;

        setWidth(size);
        setMinWidth(size);
        setMaxWidth(size);
        setHeight(size);
        setMinHeight(size);
        setMaxHeight(size);

//        setBorder(new Border(new BorderStroke(UIConfig.okColor,
//        BorderStrokeStyle.SOLID,
//        CornerRadii.EMPTY, new BorderWidths(UIConfig.borderHeight), Insets.EMPTY)));
//        setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));

        Pane paths = new Pane();
        for (Path.Side side : Path.Side.values()) {
            Path p = node.getPath(side);
            if (p != null) {
                double x = 0, y = 0;
                switch (side) {
                    case TOP -> y = -center * UIConfig.graphNodePathSizeFactor;
                    case RIGHT -> x = center * UIConfig.graphNodePathSizeFactor;
                    case BOTTOM -> y = center * UIConfig.graphNodePathSizeFactor;
                    case LEFT -> x = -center * UIConfig.graphNodePathSizeFactor;
                }
                Line line = new Line(center, center , center + x, center + y);
                if(p.getWeight() < lightLimit)
                    line.setStroke(UIConfig.graphLightPathColor);
                else if(p.getWeight() < mediumLimit)
                    line.setStroke(UIConfig.graphMediumPathColor);
                else line.setStroke(UIConfig.graphHeavyPathColor);
                
                line.setStrokeWidth(size/30 > 1 ? size/30 : 1);
                paths.getChildren().add(line);
            }
        }

        Circle circle = new Circle(size/4);
        circle.setFill(UIConfig.graphNodeColor);

        FormattedText caption = new FormattedText(node.getGraphID()+"");
        caption.setColor(UIConfig.functionsViewBckColor).setFontSize((int)(size/4)).build();

        getChildren().addAll(paths, circle, caption);
    }
}
