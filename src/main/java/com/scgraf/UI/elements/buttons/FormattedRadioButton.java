package com.scgraf.UI.elements.buttons;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class FormattedRadioButton extends HBox {
    public FormattedRadioButton(){
        super();

        setAlignment(Pos.CENTER);

        RadioButton btn = new RadioButton();

        FormattedText txt = new FormattedText("Linear generate");
        txt.setAlignment(TextAlignment.CENTER).setFontSize(12).setWrapping(100).build();
        setWidth(UIConfig.btnPrefWidth);

        getChildren().addAll(txt, btn);
    }
}
