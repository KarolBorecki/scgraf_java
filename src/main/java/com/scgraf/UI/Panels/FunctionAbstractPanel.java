package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.scene.layout.BorderPane;

public abstract class FunctionAbstractPanel extends BorderPane {
    public FunctionAbstractPanel(FunctionPanelManager root, String execBtnCaption){
        super();
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.functionsPanelPrefWidth);
        setMinWidth(UIConfig.functionsPanelMinWidth);

        FormattedButton execBtn = new FormattedButton(execBtnCaption);

        setCenter(execBtn);
    }
}
