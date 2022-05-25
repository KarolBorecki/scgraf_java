package com.scgraf.algorithms.dijkstra;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;

public class Divider {
    private final Graph dividedGraph;
    private final int maxParallelDivisions;
    private final int maxPerpendicularDivisions;

    public Divider(Graph dividedGraph){
        this.dividedGraph = dividedGraph;
        this.maxParallelDivisions= dividedGraph.getSize().height() - 1;
        this.maxPerpendicularDivisions= dividedGraph.getSize().width() - 1;
    }

    public void divideGraph(int divideThisManyTimes){
        if(divideThisManyTimes < 1 || divideThisManyTimes > (maxParallelDivisions + maxPerpendicularDivisions))
            return; //TODO Should throw an error

        int divisionsPerformed;

        for(divisionsPerformed= 0; divisionsPerformed<Math.min(maxPerpendicularDivisions, maxParallelDivisions); divisionsPerformed++) {
            if (divisionsPerformed % 2 == 0)
                singleDivisionParallel(divisionsPerformed / 2);
            else
                singleDivisionPerpendicular(divisionsPerformed / 2);
        }

        if(Math.min(maxPerpendicularDivisions, maxParallelDivisions) == maxParallelDivisions)
            for(; divisionsPerformed < divideThisManyTimes; divisionsPerformed++)
                singleDivisionPerpendicular(divisionsPerformed);
        else
            for(; divisionsPerformed < divideThisManyTimes; divisionsPerformed++)
                singleDivisionParallel(divisionsPerformed);

    }

    private void singleDivisionParallel(int parallel_division_point){
        if(parallel_division_point < 0 || parallel_division_point >dividedGraph.getSize().height()-1)
            return; //TODO Should throw an error

        for(int i= 0; i<dividedGraph.getSize().width(); i++){
            Node currNode= dividedGraph.getNode(parallel_division_point, i);
            dividedGraph.deletePathBothWays(currNode, Path.Side.BOTTOM);
        }

    }

    private void singleDivisionPerpendicular(int perpendicular_division_point){
        if(perpendicular_division_point < 0 || perpendicular_division_point >dividedGraph.getSize().height()-1)
            return; //TODO Should throw an error

        for(int i= 0; i<dividedGraph.getSize().height(); i++){
            Node currNode= dividedGraph.getNode(i, perpendicular_division_point);
            dividedGraph.deletePathBothWays(currNode, Path.Side.RIGHT);
        }
    }
}
