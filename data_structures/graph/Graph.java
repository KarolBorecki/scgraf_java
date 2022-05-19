package data_structures.graph;

import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

import data_structures.tuples.Size;

public class Graph {
    private Node[][] nodes;

    private final Size size = new Size();

    private double maxConnectionWeight;

    private final Random randomGenerator = new Random();

    public Graph setWidth(int width){
        size.setWidth(width);
        return this;
    }

    public Graph setHeight(int height){
        size.setHeight(height);
        return this;
    }

    public void build(){
        nodes = new Node[size.height()][size.width()];

        for(int x = 0; x<size.height(); x++)
            for(int y = 0; y<size.width(); y++)
                nodes[x][y] = new Node();
    }

    public void buildExample(){
        this.build();
        for(int i= 0; i<this.getSize().height(); i++){
            for(int j = 0; j<this.getSize().width(); j++){
                if(j != 0){
                    nodes[i][j].setupConnection(Node.ConnectionSide.LEFT, new Connection(randomGenerator.nextDouble()));
                }
                if(j != this.size.width() - 1){
                    nodes[i][j].setupConnection(Node.ConnectionSide.RIGHT, new Connection(randomGenerator.nextDouble()));
                }
                if(i != 0){
                    nodes[i][j].setupConnection(Node.ConnectionSide.TOP, new Connection(randomGenerator.nextDouble()));
                }
                if(i != this.size.height() - 1){
                    nodes[i][j].setupConnection(Node.ConnectionSide.BOTTOM, new Connection(randomGenerator.nextDouble()));
                }
            }
        }
    }

    public void buildExample(double base){
        this.build();
        for(int i= 0; i<this.getSize().height(); i++){
            for(int j = 0; j<this.getSize().width(); j++){
                /*if(i == 0 && j == 0){
                    nodes[i][j].setupConnection(Node.ConnectionSide.BOTTOM, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.RIGHT, new Connection(base));
                }//TOP LEFT
                else if(i==0 && j== this.getSize().width() - 1){
                    nodes[i][j].setupConnection(Node.ConnectionSide.BOTTOM, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.LEFT, new Connection(base));
                }//TOP RIGHT
                else if(i == this.getSize().height()-1 && j == 0){
                    nodes[i][j].setupConnection(Node.ConnectionSide.TOP, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.RIGHT, new Connection(base));
                }//BOTTOM LEFT
                else if((i == this.getSize().height() - 1 && j == this.getSize().width() - 1)){
                    nodes[i][j].setupConnection(Node.ConnectionSide.TOP, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.LEFT, new Connection(base));

                }//BOTTOM RIGHT
                else if(i == 0){
                    nodes[i][j].setupConnection(Node.ConnectionSide.BOTTOM, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.RIGHT, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.LEFT, new Connection(base));
                }//TOP WITHOUT CORNERS
                else if(i == this.getSize().height() - 1){
                    nodes[i][j].setupConnection(Node.ConnectionSide.TOP, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.RIGHT, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.LEFT, new Connection(base));
                }//BOTTOM WITHOUT CORNERS
                else if(j == 0){
                    nodes[i][j].setupConnection(Node.ConnectionSide.TOP, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.BOTTOM, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.RIGHT, new Connection(base));
                }//LEFT WITHOUT CORNERS
                else if(j == this.getSize().width() - 1){
                    nodes[i][j].setupConnection(Node.ConnectionSide.TOP, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.BOTTOM, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.LEFT, new Connection(base));
                }//RIGHT WITHOUT CORNERS
                else{
                    nodes[i][j].setupConnection(Node.ConnectionSide.TOP, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.BOTTOM, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.RIGHT, new Connection(base));
                    nodes[i][j].setupConnection(Node.ConnectionSide.LEFT, new Connection(base));

                }//MIDDLE*/
                if(j != 0){
                    nodes[i][j].setupConnection(Node.ConnectionSide.LEFT, new Connection(base));
                }
                if(j != this.size.width() - 1){
                    nodes[i][j].setupConnection(Node.ConnectionSide.RIGHT, new Connection(base));
                }
                if(i != 0){
                    nodes[i][j].setupConnection(Node.ConnectionSide.TOP, new Connection(base));
                }
                if(i != this.size.height() - 1){
                    nodes[i][j].setupConnection(Node.ConnectionSide.BOTTOM, new Connection(base));
                }
                base *= base;
            }
        }
    }

    public double getMaxConnectionWeight(){
        return maxConnectionWeight;
    }

    public int getIdOfConnectedVertex(Node nodeFrom, Node.ConnectionSide side){
        int currentId = nodeFrom.getId();

        if(side.index == Node.ConnectionSide.LEFT.index)
            return currentId - 1;
        else if(side.index == Node.ConnectionSide.RIGHT.index)
            return currentId + 1;
        else if(side.index == Node.ConnectionSide.TOP.index)
            return currentId - this.getSize().width();
        else if(side.index == Node.ConnectionSide.BOTTOM.index)
            return currentId + this.getSize().width();

        //TODO Should be throwing error if connection is invalid(? unless we handle it already in connections ?)
        return -1;
    }

    public Node getNode(int row, int column){
        return nodes[row][column];
    }

    public Node getNode(int id){
        return nodes[id / this.getSize().width()][id % this.getSize().width()];
    }

    public int getAmmountOfNodes(){
        return this.getSize().width() * this.getSize().height();
    }

    public Size getSize(){
        return size;
    }

    public void setMaxConnectionWeight(double newMaxConnectionWeight){
        //TODO maybe we should update this value depended by analysing the graph connections?
        maxConnectionWeight = newMaxConnectionWeight;
    }

    @Override
    public String toString(){
        return "Graph:" +
                "\n   Width: " + size.width() +
                "\n   Height: " + size.height() +
                "\n   Nodes: " + Arrays.deepToString(nodes);
    }

    public String toStringformatted(){
        String s = "------ROW : 0 --------\n";
        for(int i = 0; i < this.size.height(); i++){
            for(int j = 0; j < this.size.width(); j++){
                s += "Node " +  nodes[i][j].id + " with connections {\n";
                for(Node.ConnectionSide conn : Node.ConnectionSide.values()){
                    if(!nodes[i][j].isConnectionNull(conn)){
                        s += "\t to Node " + getIdOfConnectedVertex(nodes[i][j], conn) + " with weight =  "+ nodes[i][j].getConnectionWeight(conn) + "\n";
                    }
                }
                s += "}\n";
            }
        s += "------ROW : " + (i+1) + " --------\n";
        }
        s+= "WHOLE GRAPH!";
        return s;
    }

}
