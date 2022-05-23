package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.FormattedButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class FunctionPanel extends HBox {
    public FunctionPanel(){
        super(UIConfig.panelSpacing);
        setPadding(UIConfig.panelPadding);
        setPrefWidth(UIConfig.functionsChoosePanelPrefWidth);
        setMinWidth(UIConfig.functionsChoosePanelMinWidth);
        setAlignment(Pos.CENTER);
        //setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
