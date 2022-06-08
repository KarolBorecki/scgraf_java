package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class NodeElement extends StackPane {
    Circle circle;
    private Line rightPathLine;
    private Line bottomPathLine;

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
                if(i == Path.Side.RIGHT.index){
                    rightPathLine = new Line(center, center , center + x, center + y);
                    if(p.getWeight() < lightLimit) rightPathLine.setStroke(UIConfig.graphLightPathColor);
                    else if(p.getWeight() < mediumLimit) rightPathLine.setStroke(UIConfig.graphMediumPathColor);
                    else rightPathLine.setStroke(UIConfig.graphHeavyPathColor);
                    rightPathLine.setStrokeWidth(Math.max(size / 30, UIConfig.minPathWidth));
                    paths.getChildren().add(rightPathLine);
                }else if(i == Path.Side.BOTTOM.index){
                    bottomPathLine = new Line(center, center , center + x, center + y);
                    if(p.getWeight() < lightLimit) bottomPathLine.setStroke(UIConfig.graphLightPathColor);
                    else if(p.getWeight() < mediumLimit) bottomPathLine.setStroke(UIConfig.graphMediumPathColor);
                    else bottomPathLine.setStroke(UIConfig.graphHeavyPathColor);
                    bottomPathLine.setStrokeWidth(Math.max(size / 30, UIConfig.minPathWidth));
                    paths.getChildren().add(bottomPathLine);
                }
            }
        }

        circle = new Circle(size / 4);
        circle.setFill(UIConfig.graphNodeColor);
        getChildren().addAll(paths, circle);

        if (drawCaption){
            FormattedText caption = new FormattedText(node.getGraphID() + "");
            caption.setColor(UIConfig.functionsViewBckColor).setFontSize((int) (size / 4)).build();
            getChildren().add(caption);
        }
    }

    public void highlightRight(){
        if(rightPathLine != null) rightPathLine.setStroke(Color.VIOLET);
    }

    public void highlightBottom(){
        if(bottomPathLine != null) bottomPathLine.setStroke(Color.VIOLET);
    }

    public void highlight(){
        circle.setFill(Color.VIOLET);
    }
}
