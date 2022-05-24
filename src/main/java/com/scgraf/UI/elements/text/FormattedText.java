package com.scgraf.UI.elements.text;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FormattedText extends Text {

    public FormattedText(String txt, TextAlignment alg){
        this(txt);
        setTextAlignment(alg);
    }

    public FormattedText(String txt){
        super(txt);
        setFont(UILoader.regularFont);
        setFill(UIConfig.textColor);
    }

    public FormattedText(){
        this("");
    }

    public void setText(String s, Color c){
        setFill(c);
        setText(s);
    }
}
