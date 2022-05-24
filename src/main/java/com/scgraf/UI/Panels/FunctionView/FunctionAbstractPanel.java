package com.scgraf.UI.Panels.FunctionView;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.CancelButton;
import com.scgraf.UI.elements.buttons.RegularButton;
import com.scgraf.UI.elements.text.FormattedText;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class FunctionAbstractPanel extends VBox {
    VBox btnPanel;
    HBox infoText;

    public FunctionAbstractPanel(FunctionPanelManager root, String execBtnCaption, String infoText){
        super();
        setSpacing(UIConfig.panelRegularSpacing);
        setAlignment(Pos.CENTER);
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.functionsPanelPrefWidth);
        setMinWidth(UIConfig.functionsPanelMinWidth);

        this.infoText = new HBox();
        this.infoText.getChildren().addAll(new FormattedText(infoText));

        btnPanel = new VBox();
        btnPanel.setSpacing(UIConfig.panelSmallSpacing);
        btnPanel.setAlignment(Pos.CENTER);
        RegularButton execBtn = new RegularButton(execBtnCaption);
        CancelButton cancelBtn = new CancelButton("Cancel");
        cancelBtn.setOnAction(actionEvent -> root.switchFunction(FunctionPanelManager.Functionality.ALL));
        btnPanel.getChildren().addAll(execBtn, cancelBtn);
    }

    public void buildFunctionView(Pane inputPane){
        getChildren().addAll(infoText, inputPane, btnPanel);
    }
}
