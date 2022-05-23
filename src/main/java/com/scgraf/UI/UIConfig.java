package com.scgraf.UI;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class UIConfig {
    /* COLORS SETTINGS */
    public static Color backgroundColor = Color.web("2a2d34");

    public static Color regularBtnColor = Color.web("6ea4bf");
    public static Color subBtnColor = Color.web("6ea4bf");
    public static Color cancelBtnColor = Color.web("ad343e");

    public static Color borderColor = Color.web("f8f4e3");

    public static Color textColor = Color.web("f8f4e3");

    public static Color errorColor = Color.web("ad343e");
    public static Color warningColor = Color.web("f2af29");
    public static Color okColor = Color.web("4da167");

    /* FONT SETTINGS */
    public static int fontRegularSize = 15;

    /* STAGE SETTINGS */
    public static int stageWidth;
    public static int stageHeight = 800;

    public static int minStageWidth;
    public static int minStageHeight = 500;

    /* OVERALL PANELS SETTINGS */
    public static double panelSpacing = 20.0;
    public static Insets panelPadding = new Insets(20, 20, 20, 20);

    /* BTN SETTINGS */
    public static int btnPrefWidth = 200;
    public static int btnPrefHeight = 50;

    /* FUNCTIONS VIEW SETTINGS */
    public static int functionsViewMinHeight = 130;

    public static int functionsPanelPrefWidth = 600;
    public static int functionsPanelMinWidth = 510;

    /* FILE PANEL VIEW */
    public static int filePanelPrefWidth = 220;
    public static int filePanelMinWidth = 160;

    /* BORDER SETTINGS */
    public static int borderHeight = 1;

    static {
        stageWidth = functionsPanelPrefWidth + filePanelPrefWidth;
        minStageWidth = functionsPanelMinWidth + filePanelMinWidth;
    }
}
