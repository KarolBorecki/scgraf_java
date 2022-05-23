package com.scgraf.UI.elements.buttons;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class FormattedButton extends Button {
    public FormattedButton(String label, Color bckColor, Color textColor, Font font, double sizeFactor){
        this(label, bckColor, textColor, font);

        setPrefSize(UIConfig.btnPrefWidth * sizeFactor, UIConfig.btnPrefHeight * sizeFactor);
    }
    public FormattedButton(String label, Color bckColor, Color textColor, Font font){
        super(label);

        setPrefSize(UIConfig.btnPrefWidth, UIConfig.btnPrefHeight);

        //setBackground(UILoader.buttonBck);
        setBackground(new Background(new BackgroundFill(bckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));

        setFont(font);
        wrapTextProperty().setValue(true);

        textAlignmentProperty().set(TextAlignment.CENTER);
        setTextFill(textColor);
    }
}
