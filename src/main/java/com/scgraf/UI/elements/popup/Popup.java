package com.scgraf.UI.elements.popup;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.buttons.ButtonsDisabler;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class Popup extends Stage implements Popupable {
    FormattedText infoText;
    FormattedButton okBtn;

    public Popup(String titleTxt, String infoTxt, String okBtnTxt, Image img) {
        setResizable(false);
        initModality(Modality.APPLICATION_MODAL);

        HBox main = new HBox();
        main.setBackground(new Background(new BackgroundFill(UIConfig.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        main.setAlignment(Pos.CENTER);

        Scene scene = new Scene(main, UIConfig.popupWidth, UIConfig.popupHeight);
        setScene(scene);
        setTitle(titleTxt);
        scene.setFill(UIConfig.backgroundColor);

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(UIConfig.popupImgSize);
        imgView.setFitHeight(UIConfig.popupImgSize);

        BorderPane imgPane = new BorderPane();
        imgPane.setCenter(imgView);
        imgPane.setPadding(UIConfig.panelPadding);

        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(UIConfig.panelRegularSpacing);
        content.setPadding(UIConfig.panelPadding);

        infoText = new FormattedText(infoTxt);
        infoText.setWrapping(UIConfig.popupTextWrapWidth).setAlignment(TextAlignment.CENTER).build();

        okBtn = new SubfunctionButton(okBtnTxt);
        okBtn.setOnAction(event -> onBtnClicked());
        getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> onBtnClicked());

        content.getChildren().addAll(infoText, okBtn);
        main.getChildren().addAll(imgPane, content);
    }

    public void pop() {
        ButtonsDisabler.DisableAll();
        okBtn.setDisable(false);
        show();
    }

    public void onBtnClicked() {
        close();
        ButtonsDisabler.EnableAll();
        Logger.getInstance().log(Logger.StatusLog.OK);
    }
}
