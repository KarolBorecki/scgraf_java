package com.scgraf.UI.elements;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

public class FormattedButton extends Button {
    public FormattedButton(String label){
        super(label);

        setPrefSize(UIConfig.btnPrefWidth, UIConfig.btnPrefHeight);

        //setBackground(UILoader.buttonBck);
        setBackground(new Background(new BackgroundFill(UIConfig.btnColor, CornerRadii.EMPTY, Insets.EMPTY)));

        setFont(UILoader.regularFont);
        wrapTextProperty().setValue(true);
        textAlignmentProperty().set(TextAlignment.CENTER);
        setTextFill(UIConfig.textColor);
    }
}
