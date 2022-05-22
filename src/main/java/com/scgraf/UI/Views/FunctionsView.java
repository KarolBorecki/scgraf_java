package com.scgraf.UI.Views;

import com.scgraf.UI.Panels.FilePanel;
import com.scgraf.UI.Panels.FunctionChoosePanel;
import javafx.scene.layout.HBox;

public class FunctionsView extends HBox {
    public FunctionsView(){
        super();

        FunctionChoosePanel functionChoosePanel = new FunctionChoosePanel();
        FilePanel filePanel = new FilePanel();

        getChildren().addAll(functionChoosePanel, filePanel);
    }
}
