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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Solver {
    private Graph graph;

    public List<Observer<Graph>> onGraphChangeNotify;
    public List<Observer<Node[]>> onPathDrawNotify;

    public static Solver instance;

    public Solver(Graph graph){
        this.graph = graph;
    }

    public Solver(){
        this(GraphGenerator.GenerateExample());
        onGraphChangeNotify = new ArrayList<>();
        onPathDrawNotify = new ArrayList<>();
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
        startBackgroundSolverTask(()->{
            try {
                return Dijkstra.getShortestPathArray(graph, startNode, endNode);
            } catch (Dijkstra.DijkstraNotSolvedException e) {
                e.printStackTrace();
            } catch (Dijkstra.DijkstraCannotFindPathException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public void divide(int n){
        startBackgroundSolverTask(()->{
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
        });
    }

    public void checkConsistency(){
        startBackgroundSolverTask(() -> {
            boolean isConsistent = BFS.Solve(graph);
            return isConsistent ? "The graph is consistent." : "The graph is not consistent.";
        });
    }

    public void generate(int width, int height, double maxWeight){
        startBackgroundSolverTask(() -> GraphGenerator.Generate(new Size(width, height), maxWeight));
    }

    public void LoadGraph(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Graph File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(new Popup());
        if (file != null)
            try {
                setGraph(FileReaderG.readGraphFromFile(file));
            } catch (IOException | FileReaderG.FileFormatError | Graph.InvalidMeshConnection e) {
                Logger.getInstance().errPopup("File read error.");
            }
        else Logger.getInstance().errPopup("Could not open the file.");
    }

    public void SaveGraph(){
        FormattedButton.DisableAll();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Graph File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showSaveDialog(new Popup());
        if (file != null)
            FileWriterG.writeGraphToFile(graph, file);
        else Logger.getInstance().errPopup("Could not open the file.");
    }

    private <T> void startBackgroundSolverTask(Callable<T> method){
        Task<T> task = new Task<>() {
            @Override
            public T call() {
                try {
                    return method.call();
                } catch (Exception e) {
                    Logger.getInstance().errPopup("Cannot start the task!");
                }
                return null;
            }
        };
        task.setOnRunning( event -> OnSolverTaskStart());
        task.setOnSucceeded( event -> OnSolverTaskEnd(task));
        task.setOnFailed( event -> OnSolverTaskFailed(task));

        Thread thread = new Thread(task);
        thread.start();
    }

    private void OnSolverTaskStart(){
        FormattedButton.DisableAll();
        Logger.getInstance().log(Logger.StatusLog.CALCULATING);
    }

    private <T> void OnSolverTaskEnd(Task<T> task){
        if(task.getValue() instanceof Graph)
            setGraph((Graph) task.getValue());
        else if(task.getValue() instanceof Node[])
            setPath((Node[]) task.getValue());
        else if(task.getValue() instanceof String)
            Logger.getInstance().popup((String) task.getValue());

        FormattedButton.EnableAll();
        Logger.getInstance().log(Logger.StatusLog.OK);
    }

    private <T> void OnSolverTaskFailed(Task<T> task){
        Logger.getInstance().errPopup("The task failed.");
        Logger.getInstance().log(Logger.StatusLog.ERROR);
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        for (Observer<Graph> c: onGraphChangeNotify)
            c.call(graph);
    }

    public void setPath(Node[] path){
        for (Observer<Node[]> c: onPathDrawNotify)
            c.call(path);
    }

    public Graph getGraph(){
        return graph;
    }

    public void addGraphChangeObserver(Observer<Graph> obs){
        onGraphChangeNotify.add(obs);
    }

    public void removeGraphChangeObserver(Observer<Graph> obs){
        onGraphChangeNotify.remove(obs);
    }

    public void addPathDrawObserver(Observer<Node[]> obs){
        onPathDrawNotify.add(obs);
    }

    public void removePathDrawObserver(Observer<Node[]> obs){
        onPathDrawNotify.remove(obs);
    }
}
