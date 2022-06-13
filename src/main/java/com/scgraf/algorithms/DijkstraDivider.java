package com.scgraf.algorithms;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;

import java.util.Random;

public class DijkstraDivider {

    public static void singleDivision(Graph dividedGraph, int divisionPoint) throws Dijkstra.DijkstraNotSolvedException, Dijkstra.DijkstraCannotFindPathException, Exception {
        Node firstNode = dividedGraph.getNode(divisionPoint, 0);
        Node endNode = dividedGraph.getNode(divisionPoint, dividedGraph.getSize().width() - 1);

        Node[] nodesToVisit = null;
        Dijkstra.Solve(dividedGraph, firstNode);
        nodesToVisit = Dijkstra.getShortestPathArray(endNode);

        Path.Side[] connectionsFromPrevToCurr = new Path.Side[nodesToVisit.length - 1];
        try {
            connectionsFromPrevToCurr = initializeConnections(dividedGraph, nodesToVisit);
        } catch (Graph.InvalidMeshConnection e) {
            e.printStackTrace();
        }

        int i = 0;
        int directionOfCutNodes = getCuttingDirection(dividedGraph, divisionPoint, connectionsFromPrevToCurr[i]);

        Path.Side connectionToCut = null, secondConnectionToCut = null;
        //startNode
        try {
            connectionToCut = connectionsFromPrevToCurr[i].getSideTurnedBy(directionOfCutNodes);
            secondConnectionToCut = connectionsFromPrevToCurr[i].getOppositeSide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dividedGraph.deletePath(nodesToVisit[i], connectionToCut);
        dividedGraph.deletePath(nodesToVisit[i], secondConnectionToCut);

        for (i = 1; i < nodesToVisit.length - 1 && nodesToVisit[i] != null; i++) {
            if (isNodeAnEdge(connectionsFromPrevToCurr[i - 1], connectionsFromPrevToCurr[i])) {
                connectionToCut = connectionsFromPrevToCurr[i - 1].getSideTurnedBy(directionOfCutNodes);
                if (connectionToCut != connectionsFromPrevToCurr[i])
                    dividedGraph.deletePath(nodesToVisit[i], connectionToCut);

                secondConnectionToCut = connectionsFromPrevToCurr[i].getSideTurnedBy(directionOfCutNodes);
                if (secondConnectionToCut != connectionsFromPrevToCurr[i - 1].getOppositeSide())
                    dividedGraph.deletePath(nodesToVisit[i], secondConnectionToCut);
            } else {
                connectionToCut = connectionsFromPrevToCurr[i - 1].getSideTurnedBy(directionOfCutNodes);
                dividedGraph.deletePath(nodesToVisit[i], connectionToCut);
            }
        }
        //EndNode
        if (nodesToVisit[i] == null)
            return;

        try {
            connectionToCut = connectionsFromPrevToCurr[i - 1].getSideTurnedBy(directionOfCutNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dividedGraph.deletePath(nodesToVisit[i], connectionToCut);

        secondConnectionToCut = connectionsFromPrevToCurr[i - 2];
        dividedGraph.deletePath(nodesToVisit[i], secondConnectionToCut);
    }

    public static void divideGraphThisManyTimes(Graph dividedGraph, int divisionNumber) throws WrongDivisionsNumber, Exception, Dijkstra.DijkstraNotSolvedException {
        if (divisionNumber > dividedGraph.getSize().height() || divisionNumber < 2)
            throw new WrongDivisionsNumber(divisionNumber);
        int divisionPoint = getStartingHeight(dividedGraph);

        if (divisionPoint == 0)
            for (int i = 0; i < divisionNumber && divisionPoint < dividedGraph.getSize().height() - 1; i++) {
                try {
                    singleDivision(dividedGraph, divisionPoint++);
                } catch (Dijkstra.DijkstraCannotFindPathException e) {
                    i--;
                }
            }
        else
            for (int i = 0; i < divisionNumber && divisionPoint >= 0; i++) {
                try {
                    singleDivision(dividedGraph, divisionPoint--);
                } catch (Dijkstra.DijkstraCannotFindPathException e) {
                    i--;
                }
            }
    }

    private static Path.Side[] initializeConnections(Graph dividedGraph, Node[] tabNode) throws Graph.InvalidMeshConnection {
        Path.Side[] tabPath = new Path.Side[tabNode.length - 1];
        for (int i = 1; i < tabNode.length && tabNode[i] != null; i++) {
            tabPath[i - 1] = dividedGraph.getPathSideBetween(tabNode[i - 1], tabNode[i]);
        }
        return tabPath;
    }

    private static int getStartingHeight(Graph dividedGraph) {
        Random random = new Random();
        int y = random.nextInt(0, 2);
        return y == 0 ? 0 : dividedGraph.getSize().height() - 1;
    }

    private static int getCuttingDirection(Graph dividedGraph, int y, Path.Side connection) {
        int directionOfCutNodes;
        if (y != 0 && y != dividedGraph.getSize().height() - 1) {
            return directionOfCutNodes = -1;
        } else if (y == 0) {
            if (connection.equals(Path.Side.RIGHT))
                return directionOfCutNodes = 1;
            else
                return directionOfCutNodes = -1;
        } else if (y == dividedGraph.getSize().height() - 1) {
            if (connection.equals(Path.Side.RIGHT))
                return directionOfCutNodes = -1;
            else
                return directionOfCutNodes = 1;
        }
        return 0;
    }

    private static boolean isNodeAnEdge(Path.Side PathToNode, Path.Side PathFromNode) {
        return PathToNode != PathFromNode;
    }

    public static class WrongDivisionsNumber extends Throwable {
        private String errMsg = "Wrong division number: ";

        public WrongDivisionsNumber(int n){
            super();
            errMsg += n;
        }

        @Override
        public String getMessage() {
            return errMsg;
        }
    }
}
