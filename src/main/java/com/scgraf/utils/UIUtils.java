package com.scgraf.utils;

import javafx.scene.layout.*;

public class UIUtils {
    public static void setStaticSize(Pane pane, double width, double height){
        if(width != 0) {
            pane.setMinWidth(width);
            pane.setMaxWidth(width);
        }
        if(height != 0) {
            pane.setMinHeight(height);
            pane.setMaxHeight(height);
        }
    }
}
