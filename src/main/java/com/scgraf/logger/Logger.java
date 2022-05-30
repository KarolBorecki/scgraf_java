package com.scgraf.logger;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.generator.GraphGenerator;
import com.scgraf.solver.Solver;
import com.scgraf.utils.Observer;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    public enum StatusLog{
        OK("OK", UIConfig.okColor),
        WARNING("WARNING", UIConfig.warningColor),
        ERROR("ERROR", UIConfig.errorColor),
        CALCULATING("CALCULATING...", UIConfig.textColor),
        DRAWING("DRAWING...", UIConfig.textColor);

        final String text;
        final Color color;

        StatusLog(String text, Color color){
            this.text = text; this.color = color;
        }
    }
    public static Logger instance;

    public FormattedText logText;
    public StatusLog status;

    public Logger(FormattedText text){
        this.logText = text;
    }

    public static Logger getInstance(){
        return instance;
    }

    public static Logger getInstance(FormattedText text) {
        if (instance == null)
            instance = new Logger(text);
        return instance;
    }

    public void log(StatusLog status){
        System.out.println("Changing to: " + status.text);
        logText.setCaption(status.text).setColor(status.color).build();
        this.status = status;
    }

    public void popup(String info){
        //TODO handle popup
    }

    public StatusLog getStatus(){
        return status;
    }
}
