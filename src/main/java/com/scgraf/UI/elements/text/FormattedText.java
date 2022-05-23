package com.scgraf.UI.elements.text;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FormattedText extends Text {
    public FormattedText(String txt){
        super(txt);
        setFont(UILoader.regularFont);
        setFill(UIConfig.textColor);
    }
}
