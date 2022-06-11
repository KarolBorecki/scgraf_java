package com.scgraf.UI.elements.buttons;

import com.scgraf.Application;
import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class FormattedButton extends Button {
    DropShadow shadow = new DropShadow(UIConfig.shadowRadius, UIConfig.shadowColor);

    public FormattedButton(String label, Color bckColor, Color textColor, Font font, double sizeFactor) {
        this(label, bckColor, textColor, font);

        setPrefSize(UIConfig.btnPrefWidth * sizeFactor, UIConfig.btnPrefHeight * sizeFactor);
    }

    public FormattedButton(String label, Color bckColor, Color textColor, Font font) {
        super(label);
        ButtonsDisabler.Add(this);
        setPrefSize(UIConfig.btnPrefWidth, UIConfig.btnPrefHeight);

        //setBackground(UILoader.buttonBck);
        setBackground(new Background(new BackgroundFill(bckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));

        setFont(font);
        wrapTextProperty().setValue(true);

        textAlignmentProperty().set(TextAlignment.CENTER);
        setTextFill(textColor);

        addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            setEffect(shadow);
            Application.scene.setCursor(Cursor.HAND);
        });
        addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            setEffect(null);
            Application.scene.setCursor(Cursor.DEFAULT);
        });
    }
}
