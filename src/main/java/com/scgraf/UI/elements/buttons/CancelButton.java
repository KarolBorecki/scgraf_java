package com.scgraf.UI.elements.buttons;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;

public class CancelButton extends FormattedButton {
    public CancelButton(String label) {
        super(label, UIConfig.cancelBtnColor, UIConfig.cancelBtnColor, UILoader.regularSmallFont, UIConfig.cancelBtnSizeFactor);
        setBackground(null);
    }
}
