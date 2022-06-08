package com.scgraf.logger;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.popup.ErrorPopup;
import com.scgraf.UI.elements.popup.InfoPopup;
import com.scgraf.UI.elements.popup.Popup;
import com.scgraf.UI.elements.text.FormattedText;
import javafx.scene.paint.Color;

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
        logText.setCaption(status.text).setColor(status.color).build();
        this.status = status;
    }

    public void popup(String info){
        Popup popup = new InfoPopup(info);
        popup.pop();
    }

    public void errPopup(String info){
        Popup errPopup = new ErrorPopup(info);
        errPopup.pop();
    }

    public StatusLog getStatus(){
        return status;
    }
}
