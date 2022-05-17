package data_structures.graph;

import java.util.Arrays;

public class Node {
    private final int id;
    private final Connection[] connections = new Connection[4];;

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
        this.id = Graph.getNextNodeId();
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
    public String toString(){
        return "Node " + id + "{" +  Arrays.toString(connections) + "}";
    }
}
