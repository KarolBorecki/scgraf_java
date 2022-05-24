package com.scgraf.UI;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class UIConfig {
    /* COLORS SETTINGS */
    public static Color backgroundColor = Color.web("2a2d34");
    public static Color functionsViewBckColor = Color.web("24272d");

    public static Color regularBtnColor = Color.web("6ea4bf");
    public static Color subBtnColor = Color.web("6ea4bf");
    public static Color cancelBtnColor = Color.web("ad343e");

    public static Color borderColor = Color.web("f8f4e3");

    public static Color textColor = Color.web("f8f4e3");

    public static Color textFieldBckColor = Color.web("363b44");

    public static Color errorColor = Color.web("ad343e");
    public static Color warningColor = Color.web("f2af29");
    public static Color okColor = Color.web("4da167");

    /* FONT SETTINGS */
    public static int fontRegularSize = 15;
    public static int fontSmallSize = 13;
    public static int fontBigSize = 22;

    /* STAGE SETTINGS */
    public static int stageWidth;
    public static int stageHeight = 800;

    public static int minStageWidth;
    public static int minStageHeight = 500;

    /* OVERALL PANELS SETTINGS */
    public static int panelRegularSpacing = 20;
    public static int panelSmallSpacing = 2;
    public static Insets panelPadding = new Insets(5, 20, 5, 20);
    public static Insets panelMargin = new Insets(10, 10, 10, 10);

    /* BTN SETTINGS */
    public static int btnPrefWidth = 200;
    public static int btnPrefHeight = 50;

    /* TEXTFIELD SETTINGS */
    public static int textFieldPrefWidth = 150;
    public static int textFieldMinWidth = 60;
    public static int textFieldMaxWidth = 200;

    /* FUNCTIONS VIEW SETTINGS */
    public static int functionsViewMinHeight = 180;

    /* FUNCTIONS PANEL SETTINGS */
    public static int functionsPanelPrefWidth = 600;
    public static int functionsPanelMinWidth = 500;

    /* FILE PANEL SETTINGS */
    public static int filePanelPrefWidth = 220;
    public static int filePanelMinWidth = 150;

    /* BORDER SETTINGS */
    public static int borderHeight = 1;
    public static int borderRadius = 5;

    static {
        stageWidth = functionsPanelPrefWidth + filePanelPrefWidth;
        minStageWidth = functionsPanelMinWidth + filePanelMinWidth + ((int)panelMargin.getLeft()) + ((int)panelMargin.getRight()) + ((int)panelPadding.getLeft()) + ((int)panelPadding.getRight());
    }
}
