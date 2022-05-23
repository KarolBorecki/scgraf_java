package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.scene.layout.BorderPane;

public class ShortestPathPanel extends BorderPane {
    private FunctionPanel root;

    public ShortestPathPanel(FunctionPanel root){
        super();
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.functionsPanelPrefWidth);
        setMinWidth(UIConfig.functionsPanelMinWidth);

        this.root = root;

        FormattedButton execBtn = new FormattedButton("Exec");

        setCenter(execBtn);
    }
}
