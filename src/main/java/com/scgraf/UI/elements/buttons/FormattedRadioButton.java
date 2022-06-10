package com.scgraf.UI.elements.buttons;

import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class FormattedRadioButton extends RadioButton {
    public FormattedRadioButton(){
        super();
        setBackground(new Background(new BackgroundFill(UIConfig.regularBtnColor, new CornerRadii(0), Insets.EMPTY)));
    }
}
