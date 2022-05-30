package com.scgraf;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.views.MainView;
import com.scgraf.algorithms.Dijkstra;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.generator.GraphGenerator;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Application.stage = stage;
        scene = new Scene(new MainView(), UIConfig.stageWidth, UIConfig.stageHeight);
        stage.setResizable(false);

        stage.setTitle("scgraf");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        //launch();

        Graph graph = GraphGenerator.GenerateNotDirected(new Size(1000, 10), 3);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.Solve(graph.getNode(0, 0));
        System.out.println(dijkstra.getShortestPathString(graph.getNode(9, 848)) + "\n == " + dijkstra.getShortestPathLength(graph.getNode(9, 848)));
    }
}