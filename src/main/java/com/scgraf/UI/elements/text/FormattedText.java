package com.scgraf.UI.elements.text;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FormattedText extends Text {
    public FormattedText(String txt) {
        super(txt);
        setFont(UILoader.regularFont);
        setFill(UIConfig.textColor);
    }

    public FormattedText() {
        this("");
    }

    public FormattedText setAlignment(TextAlignment alg) {
        setTextAlignment(alg);
        return this;
    }

    public FormattedText setColor(Color c) {
        setFill(c);
        return this;
    }

    public FormattedText setFontSize(int fontSize) {
        setStyle("-fx-font: " + fontSize + " Lato;");
        return this;
    }

    public FormattedText setCaption(String s) {
        setText(s);
        return this;
    }

    public FormattedText setPos(int x, int y) {
        setX(x);
        setY(y);
        return this;
    }

    public void build() {
    }
}
