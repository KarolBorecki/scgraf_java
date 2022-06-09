package com.scgraf.UI.elements.text;

import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class FormattedTextField extends TextField {
    public FormattedTextField(String info) {
        super();
        setPromptText(info);
        setBackground(new Background(new BackgroundFill(UIConfig.textFieldBckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));
        setPrefWidth(UIConfig.textFieldPrefWidth);
        setMinWidth(UIConfig.textFieldMinWidth);
        setMaxWidth(UIConfig.textFieldMaxWidth);
        setStyle("-fx-text-inner-color: #f8f4e3");
        setFocusTraversable(false);
    }
}
