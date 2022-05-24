package com.scgraf.UI.Panels.GraphView;

import com.scgraf.UI.UIConfig;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NodeElement extends BorderPane {
    public NodeElement(int radius){
        super();

        Circle circle = new Circle(radius);
        circle.setFill(UIConfig.graphNodeColor);

        setCenter(circle);
    }
}
