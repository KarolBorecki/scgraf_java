package com.scgraf.UI.elements.buttons;

import com.scgraf.Application;
import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class FormattedButton extends Button {
    DropShadow shadow = new DropShadow(UIConfig.shadowRadius, UIConfig.shadowColor);

    private static boolean isAllDisabled = false;

    public FormattedButton(String label, Color bckColor, Color textColor, Font font, double sizeFactor) {
        this(label, bckColor, textColor, font);

        setPrefSize(UIConfig.btnPrefWidth * sizeFactor, UIConfig.btnPrefHeight * sizeFactor);
    }

    public FormattedButton(String label, Color bckColor, Color textColor, Font font) {
        super(label);

        setPrefSize(UIConfig.btnPrefWidth, UIConfig.btnPrefHeight);

        //setBackground(UILoader.buttonBck);
        setBackground(new Background(new BackgroundFill(bckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));

        setFont(font);
        wrapTextProperty().setValue(true);

        textAlignmentProperty().set(TextAlignment.CENTER);
        setTextFill(textColor);

        addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        setEffect(shadow);
                        Application.scene.setCursor(Cursor.HAND);
                    }
                });
        addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        setEffect(null);
                        Application.scene.setCursor(Cursor.DEFAULT);
                    }
                });
        disableProperty().bind(Bindings.createBooleanBinding(() -> FormattedButton.isAllDisabled));
    }

    public static void DisableAll(){
        isAllDisabled = true;
    }

    public static void EnableAll(){
        isAllDisabled = false;
    }
}
