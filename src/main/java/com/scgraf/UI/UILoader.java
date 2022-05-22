package com.scgraf.UI;

import com.scgraf.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class UILoader {
    public static Image buttonBckImg;
    public static Image testIMG;

    public static Font regularFont;

    public static Background buttonBck;

    static {
        try {
            buttonBckImg = new Image(new FileInputStream("src/main/resources/com/scgraf/img/btn_back.jpg"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            regularFont = Font.loadFont(Objects.requireNonNull(Application.class.getResource("fonts/Lato/Lato-Bold.ttf")).toExternalForm(), UIConfig.fontRegularSize);
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        buttonBck = new Background(new BackgroundImage(buttonBckImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
    }
}
