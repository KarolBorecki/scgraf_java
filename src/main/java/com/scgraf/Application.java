package com.scgraf;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.Views.MainView;
import com.scgraf.algorithms.dijkstra.BFS;
import com.scgraf.algorithms.dijkstra.Divider;
import com.scgraf.data_handling.FileReaderG;
import com.scgraf.data_handling.FileWriterG;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.queue.Fifo;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.generator.GraphGenerator;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new MainView(stage), UIConfig.stageWidth, UIConfig.stageHeight);
        stage.setResizable(false);

        stage.setTitle("scgraf");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Do you want to launch an app?\n[y/n]: ");
        //launch();
        Graph graph;
        graph = GraphGenerator.GenerateNotDirected(new Size(3, 3));
        System.out.println(graph);

        Divider divider = new Divider(graph);
        divider.divideGraphUsingDijkstra(1);

        System.out.println(graph);

        /*BFS bfs = new BFS(graph);
        bfs.Solve();
        System.out.println(bfs.isGraphConsistent());*/
    }
}