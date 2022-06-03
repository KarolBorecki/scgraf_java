package com.scgraf.UI.elements.buttons;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.beans.binding.Bindings;

public class RegularButton extends FormattedButton {
    public boolean isDisabled;
    public RegularButton(String label){
        super(label, UIConfig.regularBtnColor, UIConfig.textColor, UILoader.regularFont);
        //disableProperty().bind(Bindings.createBooleanBinding(()->isDisabled));
    }
}
