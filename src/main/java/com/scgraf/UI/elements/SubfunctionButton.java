package com.scgraf.UI.elements;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;

public class SubfunctionButton extends FormattedButton {
    public SubfunctionButton(String label){
        super(label, UIConfig.subBtnColor, UIConfig.textColor, UILoader.regularFont, 0.8);
    }
}
