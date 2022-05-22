package com.scgraf.UI;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class UIConfig {
    public static Color backgroundColor = Color.color(0.165, 0.176, 0.204);
    public static Color btnColor = Color.color(0.996, 0.6, 0.125);
    public static Color borderColor = Color.color(0.933, 0.957, 0.831);
    public static Color textColor = Color.color(0.933, 0.957, 0.831);

    public static int stageWidth = 850;
    public static int stageHeight = 900;

    public static int minStageWidth;
    public static int minStageHeight = 500;

    public static int fontRegularSize = 15;

    public static double panelSpacing = 10.0;
    public static Insets panelPadding = new Insets(20, 10, 20, 10);

    public static int btnPrefWidth = 200;
    public static int btnPrefHeight = 50;

    public static int functionsViewMinHeight = 130;

    public static int functionsChoosePanelPrefWidth = 850;
    public static int functionsChoosePanelMinWidth = 470;

    public static int filePanelPrefWidth = 220;
    public static int filePanelMinWidth = 120;

    public static int borderHeight = 1;

    static {
        stageWidth = functionsChoosePanelPrefWidth + filePanelPrefWidth;
        minStageWidth = functionsChoosePanelMinWidth + filePanelMinWidth;
    }
}
