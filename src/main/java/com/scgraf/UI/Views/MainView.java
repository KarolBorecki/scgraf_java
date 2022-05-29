package com.scgraf.UI.Views;

import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.generator.GraphGenerator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MainView extends BorderPane {
    public MainView(Stage stage) throws FileNotFoundException {
        super();
        setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        Graph graph = GraphGenerator.GenerateNotDirected(new Size(8, 8));

        GraphView graphView = new GraphView(graph, stage);
        FunctionsView functionsView = new FunctionsView();

        setCenter(graphView);
        setBottom(functionsView);
    }
}
