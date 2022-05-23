package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import javafx.scene.layout.*;

public class FunctionPanel extends HBox {
    AllFunctionsPanel allFunctionsPanel;
    ShortestPathPanel shortestPathPanel;

    public enum Functionality {
        ALL,
        SHORTEST
    }

    public FunctionPanel(){
        super();
        setPrefWidth(UIConfig.functionsPanelPrefWidth);
        setMinWidth(UIConfig.functionsPanelMinWidth);
        setPadding(UIConfig.panelPadding);

        allFunctionsPanel = new AllFunctionsPanel(this);
        shortestPathPanel = new ShortestPathPanel(this);

        getChildren().add(allFunctionsPanel);
    }

    public void switchFunction(Functionality functionality){
        getChildren().clear();
        if(functionality == Functionality.ALL) getChildren().add(allFunctionsPanel);
        if(functionality == Functionality.SHORTEST) getChildren().add(shortestPathPanel);
    }
}
