package com.scgraf.UI.views;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.panels.functions_view.FilePanel;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.utils.UIUtils;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class FunctionsView extends HBox {
    public FunctionsView() {
        super();
        setAlignment(Pos.CENTER);
        UIUtils.setStaticSize(this, UIConfig.stageWidth, UIConfig.functionsViewHeight);

        FunctionPanelManager functionChoosePanel = new FunctionPanelManager();
        FilePanel filePanel = new FilePanel();

        setMargin(functionChoosePanel, UIConfig.panelMargin);
        setMargin(filePanel, UIConfig.panelMargin);

        getChildren().addAll(functionChoosePanel, filePanel);
    }
}
