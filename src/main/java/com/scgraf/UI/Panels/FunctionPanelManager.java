package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.CaptionText;
import com.scgraf.UI.elements.text.FormattedText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class FunctionPanelManager extends BorderPane {
    CaptionText captionText;

    HBox functionPanelWrapper;

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
        setMargin(this, UIConfig.panelMargin);
        setBackground(new Background(new BackgroundFill(UIConfig.functionsViewBckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));

        captionText = new CaptionText("Select the function:");

        allFunctionsPanel = new AllFunctionsPanel(this);
        shortestPathPanel = new ShortestPathPanel(this);
        dividePanel = new DividePanel(this);
        consistencyPanel = new ConsistencyPanel(this);
        generatePanel = new GeneratePanel(this);

        functionPanelWrapper = new HBox();

        setCenter(functionPanelWrapper);

        switchFunction(Functionality.ALL);
    }

    public void switchFunction(Functionality functionality){
        functionPanelWrapper.getChildren().clear();
        setTop(null);
        if(functionality == Functionality.ALL) {
            functionPanelWrapper.getChildren().add(allFunctionsPanel);
            setTop(captionText);
        }
        if(functionality == Functionality.SHORTEST) functionPanelWrapper.getChildren().add(shortestPathPanel);
        if(functionality == Functionality.DIVIDE) functionPanelWrapper.getChildren().add(dividePanel);
        if(functionality == Functionality.CONSISTENCY) functionPanelWrapper.getChildren().add(consistencyPanel);
        if(functionality == Functionality.GENERATE) functionPanelWrapper.getChildren().add(generatePanel);
    }
}
