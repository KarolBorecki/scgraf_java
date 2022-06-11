package com.scgraf.UI.elements.buttons;

import javafx.scene.control.ButtonBase;

import java.util.ArrayList;

public abstract class ButtonsDisabler {
    protected static ArrayList<ButtonBase> instances = new ArrayList();

    public static void Add(ButtonBase btn) {
        instances.add(btn);
    }

    public static void DisableAll() {
        for (ButtonBase btn : instances)
            btn.setDisable(true);
    }

    public static void EnableAll() {
        for (ButtonBase btn : instances)
            btn.setDisable(false);
    }
}
