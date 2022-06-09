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
import javafx.concurrent.Task;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Solver {
    private Graph graph;

    public List<Observer<Graph>> onGraphChangeNotify;
    public List<Observer<Node[]>> onPathDrawNotify;
    public List<Observer<Node[]>> onPathCleanNotify;

    public static Solver instance;

    public Solver(Graph graph) {
        this.graph = graph;
    }

    public Solver() {
        this(GraphGenerator.GenerateExample());
        onGraphChangeNotify = new ArrayList<>();
        onPathDrawNotify = new ArrayList<>();
        onPathCleanNotify = new ArrayList<>();
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

    public void findShortest(Node startNode, Node endNode) {
        startBackgroundSolverTask(() -> {
            try {
                return Dijkstra.getShortestPathArray(graph, startNode, endNode);
            } catch (Dijkstra.DijkstraNotSolvedException | Dijkstra.DijkstraCannotFindPathException e) {
                Platform.runLater(()->Logger.getInstance().errPopup(e.getMessage()));
            }
            return null;
        });
    }

    public void divide(int n) {
        startBackgroundSolverTask(() -> {
            try {
                DijkstraDivider.divideGraphThisManyTimes(graph, n);
            } catch (DijkstraDivider.WrongDivisionsNumber | Dijkstra.DijkstraNotSolvedException | Exception e) {
                Platform.runLater(()->Logger.getInstance().errPopup(e.getMessage()));
            }
            return graph;
        });
    }

    public void checkConsistency() {
        startBackgroundSolverTask(() -> {
            boolean isConsistent = BFS.Solve(graph);
            return isConsistent ? "The graph is consistent." : "The graph is not consistent.";
        });
    }

    public void generate(int width, int height, double maxWeight) {
        startBackgroundSolverTask(() -> GraphGenerator.Generate(new Size(width, height), maxWeight));
    }

    public void SaveGraph() {
        //TODO add extension filter
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Graph File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showSaveDialog(new Popup());
        if (file != null)
            startBackgroundSolverTask(()->{
                FileWriterG.writeGraphToFile(graph, file);
                return null;
            });
    }

    public void LoadGraph() {
        //TODO add extension filter
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Graph File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(new Popup());
        if (file != null)
        startBackgroundSolverTask(()->{
            try {
                return FileReaderG.readGraphFromFile(file);
            } catch (IOException | FileReaderG.FileFormatError | Graph.InvalidMeshConnection e) {
                Logger.getInstance().errPopup("File read error.");
            }
            return null;
        });
    }

    private <T> void startBackgroundSolverTask(Callable<T> method) {
        Task<T> task = new Task<>() {
            @Override
            public T call() {
                try {
                    return method.call();
                } catch (Exception e) {
                    Logger.getInstance().errPopup("Method failed: " + e.getMessage());
                }
                return null;
            }
        };
        task.setOnRunning(event -> OnSolverTaskStart());
        task.setOnSucceeded(event -> OnSolverTaskEnd(task));
        task.setOnFailed(event -> OnSolverTaskFailed(task));

        Thread thread = new Thread(task);
        thread.start();
    }

    private void OnSolverTaskStart() {
        FormattedButton.DisableAll();
        Logger.getInstance().log(Logger.StatusLog.CALCULATING);
    }

    private <T> void OnSolverTaskEnd(Task<T> task) {
        FormattedButton.EnableAll();
        Logger.getInstance().log(Logger.StatusLog.OK);

        if (task.getValue() instanceof Graph)
            setGraph((Graph) task.getValue());
        else if (task.getValue() instanceof Node[])
            setPath((Node[]) task.getValue());
        else if (task.getValue() instanceof String)
            Logger.getInstance().popup((String) task.getValue());
    }

    private <T> void OnSolverTaskFailed(Task<T> task) {
        Logger.getInstance().errPopup("Task failed: " + task.getException().getMessage());
        Logger.getInstance().log(Logger.StatusLog.ERROR);
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        for (Observer<Graph> c : onGraphChangeNotify)
            c.call(graph);
    }

    public void setPath(Node[] path) {
        for (Observer<Node[]> c : onPathDrawNotify)
            c.call(path);
    }

    public void cleanPath(){
        for (Observer<Node[]> c : onPathCleanNotify)
            c.call(null);
    }

    public Graph getGraph() {
        return graph;
    }

    public void addGraphChangeObserver(Observer<Graph> obs) {
        onGraphChangeNotify.add(obs);
    }

    public void removeGraphChangeObserver(Observer<Graph> obs) {
        onGraphChangeNotify.remove(obs);
    }

    public void addPathDrawObserver(Observer<Node[]> obs) {
        onPathDrawNotify.add(obs);
    }

    public void removePathDrawObserver(Observer<Node[]> obs) {
        onPathDrawNotify.remove(obs);
    }

    public void addPathCleanObserver(Observer<Node[]> obs) {
        onPathCleanNotify.add(obs);
    }

    public void removePathCleanObserver(Observer<Node[]> obs) {
        onPathCleanNotify.remove(obs);
    }
}
