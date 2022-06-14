package com.scgraf.UI;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class UIConfig {
    /* FONT SETTINGS */
    public static int fontRegularSize = 15;
    public static int fontSmallSize = 13;
    public static int fontBigSize = 19;

    /* STAGE SETTINGS */
    public static int stageWidth;
    public static int stageHeight;
    public static Color backgroundColor = Color.web("2a2d34");
    public static Color textColor = Color.web("f8f4e3");

    public static Color errorColor = Color.web("e04f5f");
    public static Color warningColor = Color.web("f2af29");
    public static Color okColor = Color.web("4da167");

    /* POPUP SETTINGS */
    public static int popupWidth;
    public static int popupHeight;
    public static int popupImgSize;
    public static int popupTextWrapWidth;

    /* OVERALL PANELS SETTINGS */
    public static int panelRegularSpacing = 15;
    public static int panelSmallSpacing = 2;
    public static Insets panelPadding = new Insets(5, 20, 5, 20);
    public static Insets panelMargin = new Insets(10, 10, 10, 10);

    /* BTN SETTINGS */
    public static int btnPrefWidth = 200;
    public static int btnPrefHeight;
    public static Color regularBtnColor = Color.web("6ea4bf");
    public static Color subBtnColor; /* = regularBtnColor */
    public static Color cancelBtnColor = Color.web("ad343e");
    public static double cancelBtnSizeFactor = 0.3;

    /* DROP SHADOW SETTINGS */
    public static int shadowRadius = 5;
    public static Color shadowColor = Color.web("9cc1d3");

    /* TEXTFIELD SETTINGS */
    public static int textFieldPrefWidth = 150;
    public static int textFieldMinWidth = 60;
    public static int textFieldMaxWidth = 200;
    public static Color textFieldBckColor = Color.web("363b44");

    /* BORDER SETTINGS */
    public static int borderHeight = 1;
    public static int borderRadius = 5;
    public static Color borderColor = Color.web("f8f4e3");

    /* FUNCTIONS VIEW SETTINGS */
    public static int functionsViewHeight;
    public static Color functionsViewBckColor = Color.web("24272d");

    /* FUNCTIONS PANEL SETTINGS */
    public static int functionsPanelWidth;

    /* FILE PANEL SETTINGS */
    public static int filePanelWidth;

    /* GRAPH INFO SETTINGS */
    public static int graphInfoWidth;
    public static int graphInfoHeight;
    public static Insets graphInfoMargin = new Insets(10, 20, 5, 20);

    /* GRAPH SETTINGS */
    public static int graphPanelWidth;
    public static int graphPanelHeight;
    public static double graphNodePathSizeFactor = 2;
    public static double minPathWidth = 0.5;
    public static int maxGraphNodesCountToDrawCaptions = 1000;
    public static Color graphNodeColor = Color.web("f8f4e3");
    public static Color graphShortestPathColor = Color.web("ff5154");
    public static double hsbPathSaturation = 0.7;
    public static double hsbPathBrightness = 0.9;

    static {
        subBtnColor = regularBtnColor;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        stageHeight = (int) (screenBounds.getHeight() * 0.93);
        stageWidth = (int) (stageHeight / 1.15);

        btnPrefHeight = stageHeight / 15;

        functionsViewHeight = (int) (stageHeight / 4.5);
        functionsPanelWidth = (int) (stageWidth * 0.75);
        filePanelWidth = stageWidth - functionsPanelWidth;

        popupWidth = stageWidth / 2;
        popupHeight = stageHeight / 6;
        popupImgSize = (int) (popupHeight / 1.5);
        popupTextWrapWidth = (int) (popupWidth - popupImgSize * 1.8);

        graphInfoWidth = stageWidth;
        graphInfoHeight = stageHeight / 15;
        graphPanelWidth = stageWidth;
        graphPanelHeight = stageHeight - functionsViewHeight - graphInfoHeight;

    }
}
