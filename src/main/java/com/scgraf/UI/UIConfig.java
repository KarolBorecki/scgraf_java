package com.scgraf.UI;

import javafx.geometry.Insets;

public class UIConfig {
    public static int stageWidth = 850;
    public static int stageHeight = 900;

    public static int minStageWidth;
    public static int minStageHeight = 500;

    public static int fontRegularSize = 15;

    public static double panelSpacing = 10.0;
    public static Insets panelPadding = new Insets(10, 10, 10, 10);

    public static int btnPrefWidth = 200;
    public static int btnPrefHeight = 50;

    public static int functionsViewMinHeight = 110;

    public static int functionsChoosePanelPrefWidth = 850;
    public static int functionsChoosePanelMinWidth = 470;

    public static int filePanelPrefWidth = 220;
    public static int filePanelMinWidth = 120;

    static {
        minStageWidth = functionsChoosePanelMinWidth + filePanelMinWidth;
    }
}
