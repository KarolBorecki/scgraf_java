package com.scgraf.UI;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class UIConfig {
    public static Color backgroundColor = Color.color(0.165, 0.176, 0.204);
    public static Color btnColor = Color.color(0.996, 0.6, 0.125);
    public static Color borderColor = Color.color(0.933, 0.957, 0.831);
    public static Color textColor = Color.color(0.933, 0.957, 0.831);

    public static int stageWidth;
    public static int stageHeight = 800;

    public static int minStageWidth;
    public static int minStageHeight = 500;

    public static int fontRegularSize = 15;

    public static double panelSpacing = 20.0;
    public static Insets panelPadding = new Insets(20, 20, 20, 20);

    public static int btnPrefWidth = 200;
    public static int btnPrefHeight = 50;

    public static int functionsViewMinHeight = 130;

    public static int functionsPanelPrefWidth = 600;
    public static int functionsPanelMinWidth = 510;

    public static int filePanelPrefWidth = 220;
    public static int filePanelMinWidth = 160;

    public static int borderHeight = 1;

    static {
        stageWidth = functionsPanelPrefWidth + filePanelPrefWidth;
        minStageWidth = functionsPanelMinWidth + filePanelMinWidth;
    }
}
