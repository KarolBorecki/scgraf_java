package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import com.scgraf.UI.elements.SubfunctionButton;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class FilePanel extends VBox {
    public FilePanel(){
        super(UIConfig.panelSpacing);
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.filePanelPrefWidth);
        setMinWidth(UIConfig.filePanelMinWidth);
        setAlignment(Pos.CENTER);
        //setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        SubfunctionButton loadBtn = new SubfunctionButton("Load");
        SubfunctionButton saveBtn = new SubfunctionButton("Save");

        getChildren().addAll(loadBtn, saveBtn);
    }
}
