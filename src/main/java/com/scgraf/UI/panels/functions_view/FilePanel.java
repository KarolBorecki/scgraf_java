package com.scgraf.UI.panels.functions_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.SubfunctionButton;
import com.scgraf.UI.elements.text.CaptionText;
import com.scgraf.data_handling.FileReaderG;
import com.scgraf.data_handling.FileWriterG;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.solver.Solver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePanel extends BorderPane {
    final FileChooser fileChooser = new FileChooser();
    CaptionText captionText;
    VBox optionsPane;

    public FilePanel() {
        super();
        setPrefWidth(UIConfig.filePanelWidth);
        setPadding(UIConfig.panelPadding);
        setMargin(this, UIConfig.panelMargin);

        setBackground(new Background(new BackgroundFill(UIConfig.functionsViewBckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));
        //setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        captionText = new CaptionText("Save/Load:");

        SubfunctionButton saveBtn = new SubfunctionButton("Save");
        SubfunctionButton loadBtn = new SubfunctionButton("Load");

        optionsPane = new VBox();
        optionsPane.setSpacing(UIConfig.panelRegularSpacing);
        optionsPane.setAlignment(Pos.CENTER);

        optionsPane.getChildren().addAll(saveBtn, loadBtn);

        setTop(captionText);
        setCenter(optionsPane);

        loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {
                configureFileChooser(fileChooser);
                File file = fileChooser.showOpenDialog(new Popup());
                if (file != null) {
                    try {
                        Graph graph = FileReaderG.readGraphFromFile(file);
                        Solver.getInstance().setGraph(graph);
                    } catch (IOException | FileReaderG.FileFormatError | Graph.InvalidMeshConnection e) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("File Read Error");
                        if(e instanceof FileReaderG.FileFormatError)
                            errorAlert.setContentText(((FileReaderG.FileFormatError) e).getErrorMsg());
                        else
                            errorAlert.setContentText(e.getMessage());
                        errorAlert.showAndWait();
                    }
                }
            }
        });

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                configureFileChooser(fileChooser);
                File file = fileChooser.showSaveDialog(new Popup());
                if (file != null) {
                    System.out.println(Solver.getInstance().getGraph());
                    FileWriterG.writeGraphToFile(Solver.getInstance().getGraph(), file);
                }
            }
        });
    }

    private static void configureFileChooser(FileChooser fileChooser){
        fileChooser.setTitle("Choose Graph File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    }
}