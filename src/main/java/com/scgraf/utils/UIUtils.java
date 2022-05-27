package com.scgraf.utils;

import com.scgraf.UI.Panels.GraphView.GraphInfoPanel;
import com.scgraf.UI.Panels.GraphView.GraphPanel;
import com.scgraf.UI.UIConfig;
import com.scgraf.data_structures.graph.Graph;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
