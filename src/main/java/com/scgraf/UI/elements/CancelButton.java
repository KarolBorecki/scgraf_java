package com.scgraf.UI.elements;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;

public class CancelButton extends FormattedButton {
    public CancelButton(String label){
        super(label, UIConfig.cancelBtnColor, UIConfig.textColor, UILoader.regularFont, 0.4);
    }
}
