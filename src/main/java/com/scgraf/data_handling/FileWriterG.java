package com.scgraf.data_handling;

import com.scgraf.algorithms.Dijkstra;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileWriterG {
    private static PrintWriter printWriter;

    public static void writeGraphToFile(Graph g, File file) {
        try {
            printWriter = new PrintWriter(new FileWriter(file));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        printWriter.printf("%d %d\n", g.getSize().width(), g.getSize().height());
        for (Node n : g) {
            printWriter.printf("\t");
            for (Path.Side connection : Path.Side.values()) {
                if (n.isConnected(connection)) {
                    String inp = g.getNeighbourNode(n, connection).getGraphID() + ": " + n.getConnectionWeight(connection);
                    printWriter.printf("%s ", inp);
                }
            }
            printWriter.printf("\n");
        }
        printWriter.close();
    }

    public static void writeGraphToFile(Graph g, String fileName){
        File newFile = new File(fileName);
        writeGraphToFile(g, newFile);
    }

    public void writeDijkstraResultToFile(Dijkstra d, Node finishNode, File file) throws Dijkstra.DijkstraNotSolvedException {
        try {
            printWriter = new PrintWriter(new FileWriter(file));
            printWriter.printf(d.getDijkstraResult(finishNode));
            printWriter.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        printWriter.println(d.getDijkstraResult(finishNode));

        printWriter.close();
    }

}