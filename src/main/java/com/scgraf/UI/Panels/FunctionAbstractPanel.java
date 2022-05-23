package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.CancelButton;
import com.scgraf.UI.elements.buttons.RegularButton;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class FunctionAbstractPanel extends VBox {
    VBox btnPanel;
    Text infoText;

    public FunctionAbstractPanel(FunctionPanelManager root, String execBtnCaption, String infoText){
        super();
        setSpacing(UIConfig.panelRegularSpacing);
        setAlignment(Pos.CENTER);
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.functionsPanelPrefWidth);
        setMinWidth(UIConfig.functionsPanelMinWidth);

        this.infoText = new Text(10, 50, infoText);

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
