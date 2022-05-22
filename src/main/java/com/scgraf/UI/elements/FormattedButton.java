package com.scgraf.UI.elements;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class FormattedButton extends Button {
    public FormattedButton(String label){
        super(label);

        setPrefSize(UIConfig.btnPrefWidth, UIConfig.btnPrefHeight);

        setBackground(UILoader.buttonBck);

        setFont(UILoader.regularFont);
        wrapTextProperty().setValue(true);
        textAlignmentProperty().set(TextAlignment.CENTER);
    }
}
