package com.scgraf.UI.elements.popup;

import com.scgraf.UI.UILoader;

public class ErrorPopup extends Popup{
    public ErrorPopup(String infoTxt) {
        super("Error", infoTxt, "OK", UILoader.errImg);
    }
}
