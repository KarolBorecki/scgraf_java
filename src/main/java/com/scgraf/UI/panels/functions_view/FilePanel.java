package com.scgraf.UI.panels.functions_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.FormattedButton;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePanel extends BorderPane {
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

        optionsPane = new VBox();
        optionsPane.setSpacing(UIConfig.panelRegularSpacing);
        optionsPane.setAlignment(Pos.CENTER);

        SubfunctionButton saveBtn = new SubfunctionButton("Save");
        SubfunctionButton loadBtn = new SubfunctionButton("Load");

        loadBtn.setOnAction(event -> Solver.getInstance().LoadGraph());
        saveBtn.setOnAction(event -> Solver.getInstance().SaveGraph());

        optionsPane.getChildren().addAll(saveBtn, loadBtn);

        setTop(captionText);
        setCenter(optionsPane);
    }
}