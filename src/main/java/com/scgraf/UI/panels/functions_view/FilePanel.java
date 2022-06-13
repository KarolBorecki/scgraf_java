package com.scgraf.UI.panels.functions_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.SubfunctionButton;
import com.scgraf.UI.elements.text.CaptionText;
import com.scgraf.solver.Solver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class FilePanel extends BorderPane {
    CaptionText captionText;
    VBox optionsPane;

    public FilePanel() {
        super();
        setPrefWidth(UIConfig.filePanelWidth);
        setPadding(UIConfig.panelPadding);
        setMargin(this, UIConfig.panelMargin);

        setBackground(new Background(new BackgroundFill(UIConfig.functionsViewBckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));

        captionText = new CaptionText("Save/Load:");

        optionsPane = new VBox();
        optionsPane.setSpacing(UIConfig.panelRegularSpacing);
        optionsPane.setAlignment(Pos.CENTER);

        SubfunctionButton saveBtn = new SubfunctionButton("Save");
        SubfunctionButton loadBtn = new SubfunctionButton("Load");

        loadBtn.setOnAction(event -> Solver.getInstance().loadGraph());
        saveBtn.setOnAction(event -> Solver.getInstance().saveGraph());

        optionsPane.getChildren().addAll(saveBtn, loadBtn);

        setTop(captionText);
        setCenter(optionsPane);
    }
}