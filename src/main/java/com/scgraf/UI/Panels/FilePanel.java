package com.scgraf.UI.Panels;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.SubfunctionButton;
import com.scgraf.UI.elements.text.CaptionText;
import com.scgraf.UI.elements.text.FormattedText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class FilePanel extends BorderPane {
    CaptionText captionText;

    VBox optionsPane;

    public FilePanel(){
        super();
        setPrefWidth(UIConfig.filePanelPrefWidth);
        setMinWidth(UIConfig.filePanelMinWidth);
        setPadding(UIConfig.panelPadding);

        setMargin(this, UIConfig.panelMargin);
        setBackground(new Background(new BackgroundFill(UIConfig.functionsViewBckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));
        //setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        captionText = new CaptionText("Save/Load:");

        SubfunctionButton saveBtn = new SubfunctionButton("Save");
        SubfunctionButton loadBtn = new SubfunctionButton("Load");

        optionsPane = new VBox();
        optionsPane.setSpacing(UIConfig.panelRegularSpacing);
        optionsPane.setAlignment(Pos.CENTER);

        optionsPane.getChildren().addAll(saveBtn, loadBtn);

        setTop(captionText);
        setCenter(optionsPane);
    }
}
