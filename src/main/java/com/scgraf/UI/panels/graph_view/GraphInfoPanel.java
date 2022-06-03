package com.scgraf.UI.panels.graph_view;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.logger.Logger;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class GraphInfoPanel extends BorderPane {
    FormattedText graphInfo;
    FormattedText loggerInfo;

    public GraphInfoPanel(Graph graph) {
        super();
        setPrefWidth(UIConfig.graphPanelWidth);
        setPadding(UIConfig.graphInfoMargin);

        graphInfo = new FormattedText();
        loggerInfo = new FormattedText();
        loggerInfo.setCaption("OK").setColor(UIConfig.okColor).setAlignment(TextAlignment.RIGHT).build();

        setLeft(graphInfo);
        setRight(loggerInfo);

        Logger.getInstance(loggerInfo).log(Logger.StatusLog.OK);

        updateGraph(graph);
    }

    public void updateGraph(Graph graph){
        System.out.println("UPDATING GRAPHINFO"); //TODO DELETE
        graphInfo.setText("Width: " + graph.getSize().width() +"     Height: " + graph.getSize().height() +"     Weight: " + graph.getMaxConnectionWeight());
    }

}
