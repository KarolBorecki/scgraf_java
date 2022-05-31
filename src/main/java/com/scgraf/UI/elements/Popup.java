package com.scgraf.UI.elements;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.FormattedButton;
import com.scgraf.UI.elements.buttons.RegularButton;
import com.scgraf.UI.elements.buttons.SubfunctionButton;
import com.scgraf.UI.elements.text.FormattedText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Popup extends Stage {

    public Popup(String titleTxt, String infoTxt, String okBtnTxt) {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, UIConfig.popupWidth, UIConfig.popupHeight);
        setScene(scene);
        setTitle(titleTxt);
        scene.setFill(UIConfig.backgroundColor);

        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(UIConfig.panelRegularSpacing);

        FormattedText infoText = new FormattedText(infoTxt);
        FormattedButton okBtn = new SubfunctionButton(okBtnTxt);
        okBtn.setOnAction(event -> close());

        content.getChildren().addAll(infoText, okBtn);

        borderPane.setCenter(content);

        borderPane.setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
