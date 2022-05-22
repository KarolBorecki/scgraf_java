package data_handling;

import algorithms.dijkstra.Dijkstra;
import data_structures.graph.Graph;
import data_structures.graph.Node;
import data_structures.graph.Path;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;

public class FileWriterG{
    private PrintWriter printWriter;
    private File handledFile;

    private boolean isOpen;

    public FileWriterG(File handledFile){
        try{
            this.printWriter = new PrintWriter(new FileWriter(handledFile));
        }
        catch(java.io.IOException e){
            e.printStackTrace();
        }

        this.isOpen = true;
    }

    public FileWriterG(String handledFileName){
        File handledFile = new File(handledFileName);
        try{
            this.printWriter = new PrintWriter(new FileWriter(handledFile));
        }
        catch(java.io.IOException e){
            e.printStackTrace();
        }

        this.isOpen = true;
    }

    public void writeGraphToFile(Graph g){
        if(!isOpen){
            this.handledFile = new File(handledFile.getAbsolutePath());
            try {
                this.printWriter = new PrintWriter(new FileWriter(this.handledFile));
            }catch(java.io.IOException e){
                e.printStackTrace();
            }
            isOpen = true;
        }
        printWriter.printf("%d %d\n", g.getSize().width(), g.getSize().height());
        for(Node n : g){
            printWriter.printf("\t");
            for(Path.Side connection : Path.Side.values()){
                if(n.isConnected(connection)){
                    String inp = g.getNeighbourNode(n, connection).getGraphID() + ": " + n.getConnectionWeight(connection);
                    printWriter.printf("%s ", inp);
                }
            }
            printWriter.printf("\n");
        }
        this.closeFileWriter();
    }

    public void writeDijkstraResultToFile(Dijkstra d, Node finishNode){
        if(!isOpen){
            this.handledFile = new File(handledFile.getAbsolutePath());
            try {
                this.printWriter = new PrintWriter(new FileWriter(this.handledFile));
            }catch(java.io.IOException e){
                e.printStackTrace();
            }
            isOpen = true;
        }
        this.printWriter.println(d.getDijkstraResult(finishNode));
        this.closeFileWriter();
    }

    private void closeFileWriter(){
        this.printWriter.close();
        isOpen = false;
    }

}