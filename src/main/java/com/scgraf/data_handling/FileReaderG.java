package com.scgraf.data_handling;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;

import javax.security.auth.callback.TextInputCallback;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileReaderG {

    public static Graph readGraphFromFile(File file) throws IOException, FileFormatError, Graph.InvalidMeshConnection {

        int readLines = 0;
        String firstLineRegex= "\\d+\\s+\\d+";
        String nextLineRegex= "\\d+:\\s+[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine(); readLines++;

        Pattern pattern = Pattern.compile(firstLineRegex);
        Matcher matcher = pattern.matcher(line);

        if(!matcher.find())
            throw new FileFormatError(readLines, file, "Expected positive dimensions in the first line!\n");

        String [] lines = line.split(" ");

        int x = Integer.parseInt(lines[0]);
        int y = Integer.parseInt(lines[1]);

        Graph graph = new Graph();
        graph.setWidth(x).setHeight(y).build();

        for(int i= 0; i<x*y; i++){
            int connectionsFound = 0;
            line = reader.readLine().strip(); readLines++;

            pattern = Pattern.compile(nextLineRegex);
            matcher = pattern.matcher(line);

            int oldEnd= 0;

            while(matcher.find()){
                if(matcher.start() != 0) {
                    String textBetweenMatches = line.substring(oldEnd, matcher.start());
                    if(matcher.start() != oldEnd && !textBetweenMatches.isBlank())
                        throw new FileFormatError(readLines, file, "Incorrect format found in line at index: " + matcher.start());
                }

                connectionsFound++;

                lines = matcher.group().split(":");
                int finishNodeIndex = Integer.parseInt(lines[0].strip());
                double weight = Double.parseDouble(lines[1].strip());

                Node finishNode = graph.getNode(finishNodeIndex / graph.getSize().width(), finishNodeIndex % graph.getSize().width());
                Node currNode = graph.getNode(i / graph.getSize().width(), i % graph.getSize().width());

                currNode.setupPath(graph.getPathForConnection(currNode, finishNode), weight);

                oldEnd = matcher.end();
            }

            if(connectionsFound == 0 && !line.isBlank())
                throw new FileFormatError(readLines, file, "Didnt find any connections in this line, but its not empty!");
        }

        return graph;
    }

    public static Graph readGraphFromFile(String filePath) throws IOException, FileFormatError, Graph.InvalidMeshConnection {
        return readGraphFromFile(new File(filePath));
    }

    public static class FileFormatError extends Throwable {
        private String errorMsg= "Wrong file format at line ";

        public FileFormatError(int lineNumber, File file){
            errorMsg += lineNumber + " in file: \"" + file.getName() + "\"";
        }

        public FileFormatError(int lineNumber, File file, String cause){
            errorMsg += lineNumber + " in file: \"" + file.getName() + "\"\n" + cause + "\n";
        }

        public void printMessage(){
            System.err.println(this.errorMsg);
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }
}
