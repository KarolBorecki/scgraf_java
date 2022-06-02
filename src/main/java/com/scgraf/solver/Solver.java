package com.scgraf.solver;

import com.scgraf.UI.elements.buttons.FormattedButton;
import com.scgraf.algorithms.BFS;
import com.scgraf.algorithms.Dijkstra;
import com.scgraf.algorithms.DijkstraDivider;
import com.scgraf.data_handling.FileReaderG;
import com.scgraf.data_handling.FileWriterG;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.generator.GraphGenerator;
import com.scgraf.logger.Logger;
import com.scgraf.utils.Observer;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Solver {
    private Graph graph;

    public List<Observer<Graph>> onGraphChangeNotify;

    public static Solver instance;

    public Solver(Graph graph){
        this.graph = graph;
    }

    public Solver(){
        this(GraphGenerator.GenerateExample());
        onGraphChangeNotify = new ArrayList<>();
    }

    public static Solver getInstance(Graph graph) {
        if (instance == null)
            instance = new Solver(graph);

        return instance;
    }

    public static Solver getInstance() {
        if (instance == null)
            instance = new Solver();
        return instance;
    }

    public void findShortest(Node startNode, Node endNode){
        Logger.getInstance().log(Logger.StatusLog.CALCULATING);
        System.out.println("FINDING SHORTEST: " + startNode + " " + endNode);
        Logger.getInstance().log(Logger.StatusLog.OK);
    }

    public void divide(int n){
        Task<Graph> dividingTask = new Task<Graph>() {
            @Override
            public Graph call(){
                try {
                    DijkstraDivider.divideGraphThisManyTimes(graph, n);
                    return graph;
                } catch (DijkstraDivider.TooManyDividesException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Dijkstra.DijkstraNotSolvedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };


        Thread dividingThread = new Thread(dividingTask);
        dividingThread.start();
    }

    public void checkConsistency(){
        boolean isConsistent = BFS.Solve(graph);
        Logger.getInstance().popup(isConsistent ? "Consistent." : "Not consistent.");
    }

    public void generate(int width, int height, double maxWeight){
        Task<Graph> generatingTask = new Task<>() {
            @Override
            public Graph call() {
                return GraphGenerator.Generate(new Size(width, height), maxWeight);
            }
        };
        generatingTask.setOnRunning( event -> OnSolverTaskStart());
        generatingTask.setOnSucceeded( event -> OnSolverTaskEnd(generatingTask));

        Thread generatorThread = new Thread(generatingTask);
        generatorThread.start();
    }

    public void LoadGraph(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Graph File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(new Popup());
        if (file != null) {
            try {
                Graph graph = FileReaderG.readGraphFromFile(file);
                Solver.getInstance().setGraph(graph);
            } catch (IOException | FileReaderG.FileFormatError | Graph.InvalidMeshConnection e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("File Read Error");
                if(e instanceof FileReaderG.FileFormatError)
                    errorAlert.setContentText(((FileReaderG.FileFormatError) e).getErrorMsg());
                else
                    errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }

        Task<Graph> generatingTask = new Task<>() {
            @Override
            public Graph call() {
                return null; //TODO
            }
        };
        generatingTask.setOnRunning( event -> OnSolverTaskStart());
        generatingTask.setOnSucceeded( event -> OnSolverTaskEnd(generatingTask));

        Thread generatorThread = new Thread(generatingTask);
        generatorThread.start();
    }

    public void SaveGraph(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Graph File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showSaveDialog(new Popup());
        if (file != null) {
            FileWriterG.writeGraphToFile(Solver.getInstance().getGraph(), file);
        }

        Task<Graph> generatingTask = new Task<>() {
            @Override
            public Graph call() {
                return null; //TODO
            }
        };
        generatingTask.setOnRunning( event -> OnSolverTaskStart());
        generatingTask.setOnSucceeded( event -> OnSolverTaskEnd(generatingTask));

        Thread generatorThread = new Thread(generatingTask);
        generatorThread.start();
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        for (Observer<Graph> c: onGraphChangeNotify)
            c.call(graph);
    }

    public Graph getGraph(){
        return graph;
    }

    private void OnSolverTaskStart(){
        FormattedButton.DisableAll();
        Logger.getInstance().log(Logger.StatusLog.CALCULATING);

    }

    private void OnSolverTaskEnd(Task<Graph> task){
        FormattedButton.EnableAll();
        setGraph(task.getValue());
        Logger.getInstance().log(Logger.StatusLog.OK);
    }

    public void addGraphChangeObserver(Observer<Graph> obs){
        onGraphChangeNotify.add(obs);
    }

    public void removeGraphChangeObserver(Observer<Graph> obs){
        onGraphChangeNotify.remove(obs);
    }
}
