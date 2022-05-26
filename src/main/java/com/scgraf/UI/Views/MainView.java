package com.scgraf.UI.Views;

import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.generator.GraphGenerator;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.FileNotFoundException;

public class MainView extends VBox {

    public MainView() throws FileNotFoundException {
        super();
        setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        Graph graph = GraphGenerator.GenerateNotDirected(new Size(10, 10));
        Line line = new Line(0, 0, 40, 40);
        line.setStroke(Color.RED);
        line.setStrokeWidth(5);


        GraphView graphView = new GraphView(graph);
        FunctionsView functionsView = new FunctionsView();

        getChildren().addAll(graphView, functionsView);
    }
}
