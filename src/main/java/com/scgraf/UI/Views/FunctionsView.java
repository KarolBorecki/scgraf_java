package com.scgraf.UI.Views;

import com.scgraf.UI.Panels.FilePanel;
import com.scgraf.UI.Panels.FunctionChoosePanel;
import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class FunctionsView extends HBox {
    public FunctionsView(){
        super();
        setAlignment(Pos.CENTER);
        setMinHeight(UIConfig.functionsViewMinHeight);
        setBorder(new Border(new BorderStroke(UIConfig.borderColor, UIConfig.borderColor, UIConfig.borderColor, UIConfig.borderColor,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(UIConfig.borderHeight), Insets.EMPTY)));

        FunctionChoosePanel functionChoosePanel = new FunctionChoosePanel();
        FilePanel filePanel = new FilePanel();

        getChildren().addAll(functionChoosePanel, filePanel);
    }
}
