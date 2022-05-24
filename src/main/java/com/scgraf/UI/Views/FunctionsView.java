package com.scgraf.UI.Views;

import com.scgraf.UI.Panels.FilePanel;
import com.scgraf.UI.Panels.FunctionPanelManager;
import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class FunctionsView extends HBox {
    public FunctionsView(){
        super();
        setAlignment(Pos.CENTER);
        setMinHeight(UIConfig.functionsViewMinHeight);
        setPrefWidth(UIConfig.stageWidth);
        setMinWidth(UIConfig.minStageWidth);
        //        setBorder(new Border(new BorderStroke(UIConfig.borderColor, UIConfig.borderColor, UIConfig.borderColor, UIConfig.borderColor,
//                BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
//                CornerRadii.EMPTY, new BorderWidths(UIConfig.borderHeight), Insets.EMPTY)));

        FunctionPanelManager functionChoosePanel = new FunctionPanelManager();
        FilePanel filePanel = new FilePanel();

        setMargin(functionChoosePanel, UIConfig.panelMargin);
        setMargin(filePanel, UIConfig.panelMargin);

        getChildren().addAll(functionChoosePanel, filePanel);
    }
}
