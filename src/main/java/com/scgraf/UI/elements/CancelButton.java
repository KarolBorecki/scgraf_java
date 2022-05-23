package com.scgraf.UI.elements;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.UILoader;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

public class CancelButton extends FormattedButton {
    public CancelButton(String label){
        super(label, UIConfig.cancelBtnColor, UIConfig.cancelBtnColor, UILoader.regularSmallFont, 0.3);
        setBackground(null);
        //setBorder(new Border(new BorderStroke(UIConfig.cancelBtnColor, BorderStrokeStyle.SOLID, new CornerRadii(UIConfig.borderRadius), new BorderWidths(UIConfig.borderHeight), Insets.EMPTY)));
    }
}
