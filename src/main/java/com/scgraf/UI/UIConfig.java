package com.scgraf.UI;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class UIConfig {
    /* COLORS SETTINGS */
    public static Color backgroundColor = Color.color(0.165, 0.176, 0.204);
    public static Color btnColor = Color.color(0.996, 0.6, 0.125);
    public static Color borderColor = Color.color(0.933, 0.957, 0.831);
    public static Color textColor = Color.color(0.933, 0.957, 0.831);

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
