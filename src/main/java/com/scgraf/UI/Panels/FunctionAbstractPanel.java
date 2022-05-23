package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.CancelButton;
import com.scgraf.UI.elements.FormattedButton;
import com.scgraf.UI.elements.RegularButton;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public abstract class FunctionAbstractPanel extends BorderPane {
    public FunctionAbstractPanel(FunctionPanelManager root, String execBtnCaption){
        super();
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.functionsPanelPrefWidth);
        setMinWidth(UIConfig.functionsPanelMinWidth);

        RegularButton execBtn = new RegularButton(execBtnCaption);
        CancelButton cancelBtn = new CancelButton("Cancel");

        setCenter(execBtn);
        setLeft(cancelBtn);
    }
}
