package com.scgraf.UI.elements.text;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.scene.text.Text;

public class CaptionText extends FormattedText {
    public CaptionText(String txt){
        super(txt);
        setFont(UILoader.regularBigFont);
    }
}
