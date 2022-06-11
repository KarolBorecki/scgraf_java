package com.scgraf.UI.elements.buttons;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;

public class RegularButton extends FormattedButton {
    public RegularButton(String label) {
        super(label, UIConfig.regularBtnColor, UIConfig.textColor, UILoader.regularFont);
    }
}
