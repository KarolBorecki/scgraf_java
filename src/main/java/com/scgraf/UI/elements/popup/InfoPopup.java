package com.scgraf.UI.elements.popup;

import com.scgraf.UI.UILoader;

public class InfoPopup extends Popup {
    public InfoPopup(String infoTxt) {
        super("Info", infoTxt, "That's cool", UILoader.infoImg);
    }
}
