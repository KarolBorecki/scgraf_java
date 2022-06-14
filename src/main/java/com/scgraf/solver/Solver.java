package com.scgraf.solver;

import com.scgraf.Application;
import com.scgraf.UI.elements.buttons.ButtonsDisabler;
import com.scgraf.algorithms.*;
import com.scgraf.algorithms.divider.DijkstraDivider;
import com.scgraf.algorithms.divider.Divider;
import com.scgraf.algorithms.divider.SimpleDivider;
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
    public List<Observer<Node[]>> onNodeChooseNotify;

    public static Solver instance;

    public Solver(Graph graph) {
        this.graph = graph;
    }

    public Solver() {
        this(GraphGenerator.GenerateExample());
        onGraphChangeNotify = new ArrayList<>();
        onPathDrawNotify = new ArrayList<>();
        onPathCleanNotify = new ArrayList<>();
        onNodeChooseNotify = new ArrayList<>();
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

    public void findShortest(int startNodeID, int endNodeID) {
        cleanPath();
        startBackgroundSolverTask(() -> {
            try {
                Node startNode = graph.getNode(startNodeID);
                Node endNode = graph.getNode(endNodeID);
                if (startNode == null || endNode == null)
                    throw new Graph.NodeNotFoundException();
                Dijkstra.Solve(graph, startNode);
                Platform.runLater(() -> Logger.getInstance().popup("Shortest Path between Node " + startNodeID + " and Node " + endNodeID + " = " + String.format("%.2f", Dijkstra.getShortestPathLength(endNode))));
                return Dijkstra.getShortestPathArray(endNode);
            } catch (Graph.NodeNotFoundException | Dijkstra.DijkstraNotSolvedException | Dijkstra.DijkstraCannotFindPathException e) {
                Platform.runLater(() -> Logger.getInstance().errPopup(e.getMessage()));
            }
            return null;
        });
    }

    public void divide(int n, Divider.DividingType dividingType) {
        startBackgroundSolverTask(() -> {
            try {
                Divider.Divide(graph, n, dividingType);
            } catch (SimpleDivider.TooManyDividesException | DijkstraDivider.WrongDivisionsNumber | Dijkstra.DijkstraNotSolvedException | Exception e) {
                Platform.runLater(() -> Logger.getInstance().errPopup(e.getMessage()));
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

    public void generate(int width, int height, double maxWeight, GraphGenerator.GeneratingType type) {
        startBackgroundSolverTask(() -> GraphGenerator.Generate(new Size(width, height), maxWeight, type));
    }

    public void saveGraph() {
        ButtonsDisabler.DisableAll();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Graph to File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("ALL", "*.*")
        );
        File file = fileChooser.showSaveDialog(Application.stage);
        if (file != null)
            startBackgroundSolverTask(() -> {
                FileWriterG.writeGraphToFile(graph, file);
                return null;
            });
        ButtonsDisabler.EnableAll();
    }

    public void loadGraph() {
        ButtonsDisabler.DisableAll();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Graph from File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("ALL", "*.*")
        );
        File file = fileChooser.showOpenDialog(Application.stage);
        if (file != null)
            startBackgroundSolverTask(() -> {
                try {
                    return FileReaderG.readGraphFromFile(file);
                } catch (IOException | FileReaderG.FileFormatError | Graph.InvalidMeshConnection e) {
                    Platform.runLater(() -> Logger.getInstance().errPopup("File read error: " + e.getMessage()));
                }
                return null;
            });
        ButtonsDisabler.EnableAll();
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
        ButtonsDisabler.DisableAll();
        Logger.getInstance().log(Logger.StatusLog.CALCULATING);
    }

    private <T> void OnSolverTaskEnd(Task<T> task) {
        ButtonsDisabler.EnableAll();
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

    public void cleanPath() {
        for (Observer<Node[]> c : onPathCleanNotify)
            c.call(null);
    }

    public Graph getGraph() {
        return graph;
    }

    public void onNodeChoose(Node[] nodes) {
        for (Observer<Node[]> c : onNodeChooseNotify)
            c.call(nodes);
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

    public void addNodeChooseObserver(Observer<Node[]> obs) {
        onNodeChooseNotify.add(obs);
    }

    public void removeNodeChooseObserver(Observer<Node[]> obs) {
        onNodeChooseNotify.remove(obs);
    }
}
