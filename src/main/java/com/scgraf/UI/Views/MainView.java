package com.scgraf.UI.Views;

import com.scgraf.UI.UIConfig;
import com.scgraf.algorithms.dijkstra.Divider;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.generator.GraphGenerator;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MainView extends BorderPane {
    public MainView(Stage stage){
        super();
        setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        Graph graph = GraphGenerator.GenerateNotDirected(new Size(10, 10));

        //graph = GraphGenerator.GenerateNotDirected(new Size(10, 10));
        Divider divider = new Divider(graph);
        try {
            divider.divideGraphUsingDijkstra(5);
        } catch (Exception | Graph.InvalidMeshConnection e) {
            e.printStackTrace();
        } catch (Divider.TooManyDividesException e) {
            System.err.println("Sorry cannot divide graph this many times!");
            e.getCause();
        }
        GraphView graphView = new GraphView(graph, stage);
        FunctionsView functionsView = new FunctionsView();

        setCenter(graphView);
        setBottom(functionsView);

//        getChildren().addAll(graphView, functionsView);

    }
}
