package com.scgraf.UI.Panels;

import com.scgraf.UI.elements.FormattedButton;
import javafx.scene.layout.VBox;

public class FilePanel extends VBox {
    public FilePanel(){
        super();

        FormattedButton loadBtn = new FormattedButton("Load");
        FormattedButton saveBtn = new FormattedButton("Save");

        getChildren().addAll(loadBtn, saveBtn);
    }
}
