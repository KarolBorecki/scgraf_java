package com.scgraf.UI.elements.text;

import com.scgraf.UI.UILoader;

public class CaptionText extends FormattedText {
    public CaptionText(String txt) {
        super(txt);
        setFont(UILoader.regularBigFont);
    }
}
