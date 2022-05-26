package com.scgraf.algorithms.dijkstra;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.queue.Fifo;

public class BFS implements Algorithm{

    private Fifo<Node> queToCheck;
    private boolean isGraphConsistent;

    private final Graph graph;

    public BFS(Graph graph){
        this.queToCheck = new Fifo<>(graph.getNodesCount());
        this.graph = graph;
    }

    @Override
    public void Solve() {
        initializeQue();

        System.out.println(queToCheck);

        while(!queToCheck.IsEmpty()){
            Node currentNode = queToCheck.pop();
            for(Path.Side s : Path.Side.values()){
                if(currentNode.isConnected(s)) {
                    Node checkedNode = graph.getNeighbourNode(currentNode, s);
                    if (checkedNode != null && !queToCheck.doesWholeQueContain(checkedNode))
                        queToCheck.push(checkedNode);
                }
            }
            System.out.println("Node " + currentNode.getGraphID() + " checked!");
        }

        isGraphConsistent = queToCheck.howManyElements() == graph.getNodesCount();
    }

    public boolean isGraphConsistent(){
        return isGraphConsistent;
    }

    private void initializeQue(){
        Node startNode = graph.getNode(0,0);
        queToCheck.push(startNode);
        for(Path.Side s : Path.Side.values()){
            if(startNode.isConnected(s)) {
                Node neighbourNode = graph.getNeighbourNode(startNode, s);
                if (neighbourNode != null)
                    queToCheck.push(neighbourNode);
            }
        }
        queToCheck.pop();
    }
}
