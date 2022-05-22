package com.scgraf.data_handling;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileReaderG {

    private File handledFile;

    public FileReaderG(File file) throws java.io.FileNotFoundException{
        this.handledFile = file;
    }

    public FileReaderG(String filePath){
        this.handledFile = new File(filePath);
    }

    public Graph readGraphToFile(File file) throws IOException, FileFormatError{

        int readLines = 0;

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine(); readLines++;

        Pattern pattern = Pattern.compile("\\d\\s+\\d");
        Matcher matcher = pattern.matcher(line);

        if(!matcher.find()){
            throw new FileFormatError(readLines);
        }

        String [] lines = line.split(" ");

        int x = Integer.parseInt(lines[0]);
        int y = Integer.parseInt(lines[1]);
        Graph graph = new Graph();
        graph.setWidth(x).setHeight(y).build();

        for(int i= 0; i<x*y; i++){
            line = reader.readLine().stripIndent(); readLines++;
            pattern = Pattern.compile("\\d:\\s+[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
            matcher = pattern.matcher(line);
            while(matcher.find()){
                lines = matcher.group().split(":");
                int finishNodeIndex = Integer.parseInt(lines[0].strip());
                double weight = Double.parseDouble(lines[1].strip());

                Node finishNode = graph.getNode(finishNodeIndex / graph.getSize().width(), finishNodeIndex % graph.getSize().width());
                Node currNode = graph.getNode(i / graph.getSize().width(), i % graph.getSize().width());
                currNode.setupPath(graph.getPathForConnection(currNode, finishNode), weight);
            }
        }

        return graph;
    }

    public Graph readGraphToFile() throws IOException, FileFormatError{
        return readGraphToFile(this.handledFile);
    }

    public class FileFormatError extends Throwable {
        private String errorMsg= "Wrong file format at line ";
        private int lineNumber;

        public FileFormatError(int lineNumber){
            errorMsg += lineNumber;
        }
    }
}
