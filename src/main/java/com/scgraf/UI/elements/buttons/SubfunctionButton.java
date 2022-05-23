package com.scgraf.UI.elements.buttons;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;

public class SubfunctionButton extends FormattedButton {
    public SubfunctionButton(String label){
        super(label, UIConfig.subBtnColor, UIConfig.textColor, UILoader.regularFont, 0.8);
    }
}
