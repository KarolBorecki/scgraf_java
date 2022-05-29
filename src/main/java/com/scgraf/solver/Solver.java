package com.scgraf.solver;

import com.scgraf.UI.Views.GraphView;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.generator.GraphGenerator;
import com.scgraf.utils.Observer;

import java.util.List;
import java.util.concurrent.Callable;

public class Solver {
    private Graph graph;

    public List<Observer<Graph>> onGraphChangeNotify;

    public static Solver instance;

    public Solver(Graph graph){
        this.graph = graph;
    }

    public Solver(){
        this(GraphGenerator.GenerateExample());
    }

    public static Solver getInstance(Graph graph) {
        if (instance == null) {
            instance = new Solver(graph);
        }
        return instance;
    }

    public static Solver getInstance() {
        if (instance == null)
            instance = new Solver();
        return instance;
    }

    public void findShortest(Node startNode, Node endNode){
        //TODO implent
        System.out.println("FINDING SHORTEST: " + startNode + " " + endNode);
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
        //TODO implent
        System.out.println("GENERATING: " + width + " " + height + " " + maxWeight);
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
