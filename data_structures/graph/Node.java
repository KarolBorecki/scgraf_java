package data_structures.graph;

import java.util.Arrays;

public class Node implements Comparable<Node>{
    protected final int id;
    private final Connection[] connections = new Connection[4];;

    private static int nodeIdCounter;

    public enum ConnectionSide{
        TOP(0),
        RIGHT(1),
        BOTTOM(2),
        LEFT(3);

        final int index;

        ConnectionSide(int index) {
            this.index = index;
        }
    }

    public Node(){
        this.id = nodeIdCounter++;
    }

    public Node setupConnection(ConnectionSide side, Connection connection){
        connections[side.index] = connection;
        return this;
    }

    public void build(){
        ;;
    }

    public double getConnectionWeight(ConnectionSide side){
        return connections[side.index].getWeight();
    }

    public int getId(){
        return id;
    }

    @Override
    public int compareTo(Node o) {
        return this.id - o.id;
    }

    @Override
    public String toString(){
        return "Node " + id + "{" +  Arrays.toString(connections) + "}";
    }
}
