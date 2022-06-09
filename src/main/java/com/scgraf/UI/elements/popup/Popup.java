package com.scgraf.UI.elements.popup;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.FormattedButton;
import com.scgraf.UI.elements.buttons.SubfunctionButton;
import com.scgraf.UI.elements.text.FormattedText;
import com.scgraf.logger.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public abstract class Popup extends Stage implements Popupable {

    public Popup(String titleTxt, String infoTxt, String okBtnTxt, Image img) {
        HBox main = new HBox();
        main.setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        main.setAlignment(Pos.CENTER);
        main.setSpacing(UIConfig.panelRegularSpacing);

        Scene scene = new Scene(main, UIConfig.popupWidth, UIConfig.popupHeight);
        setScene(scene);
        setTitle(titleTxt);
        scene.setFill(UIConfig.backgroundColor);

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(UIConfig.popupImgSize);
        imgView.setFitHeight(UIConfig.popupImgSize);

        BorderPane imgPane = new BorderPane();
        imgPane.setCenter(imgView);

        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(UIConfig.panelRegularSpacing);

        FormattedText infoText = new FormattedText(infoTxt);

        FormattedButton okBtn = new SubfunctionButton(okBtnTxt);
        okBtn.setOnAction(event -> onBtnClicked());

        content.getChildren().addAll(infoText, okBtn);
        main.getChildren().addAll(imgPane, content);

        //borderPane.setCenter(main);

        //borderPane.setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void pop() {
        show();
    }

    public void onBtnClicked() {
        close();
        FormattedButton.EnableAll();
        Logger.getInstance().log(Logger.StatusLog.OK);
    }
}
