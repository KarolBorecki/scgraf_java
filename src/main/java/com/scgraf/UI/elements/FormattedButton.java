package com.scgraf.UI.elements;

import com.scgraf.UI.UILoader;
import javafx.scene.control.Button;

public class FormattedButton extends Button {
    public FormattedButton(String label){
        super(label);

        setBackground(UILoader.buttonBck);
        setFont(UILoader.regularFont);
    }
}
