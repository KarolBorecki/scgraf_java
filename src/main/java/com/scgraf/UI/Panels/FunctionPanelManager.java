package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import javafx.scene.layout.*;

public class FunctionPanelManager extends HBox {
    AllFunctionsPanel allFunctionsPanel;

    ShortestPathPanel shortestPathPanel;
    DividePanel dividePanel;
    ConsistencyPanel consistencyPanel;
    GeneratePanel generatePanel;

    public enum Functionality {
        ALL,
        SHORTEST,
        DIVIDE,
        CONSISTENCY,
        GENERATE
    }

    public FunctionPanelManager(){
        super();
        setPrefWidth(UIConfig.functionsPanelPrefWidth);
        setMinWidth(UIConfig.functionsPanelMinWidth);
        setPadding(UIConfig.panelPadding);

        allFunctionsPanel = new AllFunctionsPanel(this);
        shortestPathPanel = new ShortestPathPanel(this);
        dividePanel = new DividePanel(this);
        consistencyPanel = new ConsistencyPanel(this);
        generatePanel = new GeneratePanel(this);

        getChildren().add(allFunctionsPanel);
    }

    public void switchFunction(Functionality functionality){
        getChildren().clear();
        if(functionality == Functionality.ALL) getChildren().add(allFunctionsPanel);
        if(functionality == Functionality.SHORTEST) getChildren().add(shortestPathPanel);
        if(functionality == Functionality.DIVIDE) getChildren().add(dividePanel);
        if(functionality == Functionality.CONSISTENCY) getChildren().add(consistencyPanel);
        if(functionality == Functionality.GENERATE) getChildren().add(generatePanel);
    }
}
