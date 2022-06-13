package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.CancelButton;
import com.scgraf.UI.elements.buttons.SubfunctionButton;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.logger.Logger;
import com.scgraf.solver.Solver;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class FunctionAbstractPanel extends VBox implements IFunctionPanel, SolverCaller {
    protected FunctionPanelManager panelManager;

    protected VBox btnPanel;
    protected HBox infoText;

    protected SubfunctionButton execBtn;
    protected CancelButton cancelBtn;

    public FunctionAbstractPanel(FunctionPanelManager panelManager, String execBtnCaption, String infoText) {
        super();
        this.panelManager = panelManager;

        setSpacing(UIConfig.panelRegularSpacing);
        setAlignment(Pos.CENTER);
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.functionsPanelWidth);

        this.infoText = new HBox();
        this.infoText.getChildren().addAll(new FormattedText(infoText));

        btnPanel = new VBox();
        btnPanel.setSpacing(UIConfig.panelSmallSpacing);
        btnPanel.setAlignment(Pos.CENTER);

        execBtn = new SubfunctionButton(execBtnCaption);
        execBtn.setOnAction(actionEvent -> solve());

        cancelBtn = new CancelButton("Cancel");
        cancelBtn.setOnAction(event -> cancel());

        btnPanel.getChildren().addAll(execBtn, cancelBtn);
    }

    public void buildFunctionView(Pane inputPane) {
        getChildren().addAll(infoText, inputPane, btnPanel);
    }

    public void cancel() {
        panelManager.switchFunction(FunctionPanelManager.Functionality.ALL);
        Logger.getInstance().log(Logger.StatusLog.OK);
        Solver.getInstance().cleanPath();
    }

    public void updateValues(Object[] val) {
    }
}
