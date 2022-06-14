package com.scgraf.data_handling;

import com.scgraf.Application;
import com.scgraf.data_structures.graph.Graph;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class DataManager {
    private static final FileChooser fileChooser = new FileChooser();

    static {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt"),
                new FileChooser.ExtensionFilter("all", "*.*")
        );
    }

    public static File openLoadDialog(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Graph from File");
        return fileChooser.showOpenDialog(Application.stage);
    }

    public static File openSaveDialog(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Graph to File");
        return fileChooser.showSaveDialog(Application.stage);
    }

    public static Graph graphFromFile(File file) throws IOException, FileReaderG.FileFormatError, Graph.InvalidMeshConnection {
        return FileReaderG.readGraphFromFile(file);
    }

    public static void saveGraphToFile(Graph graph, File file){
        FileWriterG.writeGraphToFile(graph, file);
    }
}
