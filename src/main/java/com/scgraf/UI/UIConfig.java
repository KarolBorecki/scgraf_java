package com.scgraf.UI;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

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

    public static Color errorColor = Color.web("e04f5f");
    public static Color warningColor = Color.web("f2af29");
    public static Color okColor = Color.web("4da167");

    public static Color graphNodeColor = Color.web("f8f4e3");
    public static Color graphLightPathColor = Color.web("4da167");
    public static Color graphMediumPathColor = Color.web("f2af29");
    public static Color graphHeavyPathColor = Color.web("e04f5f");
    public static Color graphShortestPathColor = Color.web("ff8c00");

    /* FONT SETTINGS */
    public static int fontRegularSize = 15;
    public static int fontSmallSize = 13;
    public static int fontBigSize = 19;

    /* STAGE SETTINGS */
    public static int stageWidth;
    public static int stageHeight;

    /* POPUP SETTINGS */
    public static int popupWidth;
    public static int popupHeight;
    public static int popupImgSize;

    /* OVERALL PANELS SETTINGS */
    public static int panelRegularSpacing = 15;
    public static int panelSmallSpacing = 2;
    public static Insets panelPadding = new Insets(5, 20, 5, 20);
    public static Insets panelMargin = new Insets(10, 10, 10, 10);

    /* BTN SETTINGS */
    public static int btnPrefWidth = 200;
    public static int btnPrefHeight;

    /* DROP SHADOW SETTINGS */
    public static int shadowRadius = 5;
    public static Color shadowColor = Color.web("f8f4e3");

    /* TEXTFIELD SETTINGS */
    public static int textFieldPrefWidth = 150;
    public static int textFieldMinWidth = 60;
    public static int textFieldMaxWidth = 200;

    /* FUNCTIONS VIEW SETTINGS */
    public static int functionsViewHeight;

    /* FUNCTIONS PANEL SETTINGS */
    public static int functionsPanelWidth;

    /* FILE PANEL SETTINGS */
    public static int filePanelWidth;

    /* BORDER SETTINGS */
    public static int borderHeight = 1;
    public static int borderRadius = 5;

    /* GRAPH INFO SETTINGS */
    public static int graphInfoWidth;
    public static int graphInfoHeight;
    public static Insets graphInfoMargin = new Insets(10, 20, 5, 20);

    /* GRAPH SETTINGS */
    public static int graphPanelWidth;
    public static int graphPanelHeight;
    public static double graphNodePathSizeFactor = 2;
    public static double minPathWidth = 0.5;
    public static int maxGraphNodesCountToDrawCaptions = 500;

    static {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        stageHeight = (int)(screenBounds.getHeight() * 0.93);
        stageWidth = (int)(stageHeight/1.15);

        btnPrefHeight = (int)(stageHeight/15);

        functionsViewHeight = (int)(stageHeight / 4.5);
        functionsPanelWidth = (int) (stageWidth / 4 * 5);
        filePanelWidth = (int) (stageWidth / 5);

        popupWidth = stageWidth / 2;
        popupHeight = stageHeight / 6;
        popupImgSize = (int)(popupHeight / 1.5);

        graphInfoWidth = stageWidth;
        graphInfoHeight = stageHeight / 15;
        graphPanelWidth = stageWidth;
        graphPanelHeight = stageHeight - functionsViewHeight - graphInfoHeight;

    }
}
