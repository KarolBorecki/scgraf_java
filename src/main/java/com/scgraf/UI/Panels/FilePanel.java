package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FilePanel extends VBox {
    public FilePanel(){
        super(UIConfig.panelSpacing);
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.filePanelPrefWidth);
        setMinWidth(UIConfig.filePanelMinWidth);
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        FormattedButton loadBtn = new FormattedButton("Load");
        FormattedButton saveBtn = new FormattedButton("Save");

        getChildren().addAll(loadBtn, saveBtn);
    }
}
