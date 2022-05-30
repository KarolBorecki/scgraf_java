package com.scgraf.solver;

import com.scgraf.algorithms.Dijkstra;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.generator.GraphGenerator;
import com.scgraf.logger.Logger;
import com.scgraf.utils.Observer;
import javafx.application.Platform;

import java.security.Timestamp;
import java.util.*;
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
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.Solve(startNode);
        System.out.println(dijkstra.getShortestPathString(endNode));
        Logger.getInstance().log(Logger.StatusLog.OK);
    }

    public void divide(int n){
        //TODO implent
        System.out.println("DIVIDING: " + n);
    }

    public void checkConsistency(){
        //TODO implent
        System.out.println("CHECKING CONSISTENCY...");
    }

    public void generate(int width, int height, double maxWeight){
        graph = GraphGenerator.Generate(new Size(width, height), maxWeight);
        //setGraph(graph);
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        for (Observer<Graph> c: onGraphChangeNotify)
            c.call(graph);
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
}
