package data_structures.graph;

public class Node implements Comparable<Node>{
    protected final int ID;
    protected final int graphID;

    private final Path[] paths = new Path[4];

    private static int nodeIDCounter = 0;

    public Node(Graph parentGraph){

        this.ID = nodeIDCounter++;
        this.graphID = parentGraph.getNextGraphID();
    }

    public void setupPath(Path.Side side, double pathWeight){
        setupPath(side, new Path(pathWeight));
    }

    public void setupPath(Path.Side side, Path path){
        paths[side.index] = path;
    }

    public boolean isConnected(Path.Side side){
        return paths[side.index] != null;
    }

    public int getID(){return ID;}

    public int getGraphID(){return graphID;}

    public double getConnectionWeight(Path.Side side){
        return paths[side.index].getWeight();
    }

    @Override
    public int compareTo(Node o) {
        return this.ID - o.ID;
    }

    @Override
    public String toString(){
        StringBuilder resultString = new StringBuilder("   [" + graphID + "]" + "Node " + ID + ":\n");
        for(Path.Side side : Path.Side.values())
            if(paths[side.index] != null)
                resultString.append("      ").append(side).append(": ").append(paths[side.index].getWeight()).append("\n");
        return  resultString.toString();
    }
}
