package com.scgraf.UI.Views;

import com.scgraf.UI.Panels.FilePanel;
import com.scgraf.UI.Panels.FunctionChoosePanel;
import com.scgraf.UI.UIConfig;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class FunctionsView extends HBox {
    public FunctionsView(){
        super();
        setAlignment(Pos.CENTER);
        setMinHeight(UIConfig.functionsViewMinHeight);

        FunctionChoosePanel functionChoosePanel = new FunctionChoosePanel();
        FilePanel filePanel = new FilePanel();

        getChildren().addAll(functionChoosePanel, filePanel);
    }
}
