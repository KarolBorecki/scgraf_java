package com.scgraf.UI;

import com.scgraf.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UILoader {
    public static Image buttonBckImg;

    public static Image infoImg;
    public static Image errImg;

    public static Image iconImg;

    public static Font regularFont;
    public static Font regularSmallFont;
    public static Font regularBigFont;

    public static Background buttonBck;

    static {
        try {
            buttonBckImg = new Image(new FileInputStream("src/main/resources/com/scgraf/img/btn_back.jpg"));
            infoImg = new Image(new FileInputStream("src/main/resources/com/scgraf/img/info_img.png"));
            errImg = new Image(new FileInputStream("src/main/resources/com/scgraf/img/err_img.png"));
            iconImg = new Image(new FileInputStream("src/main/resources/com/scgraf/img/icon_img.png"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            regularFont = Font.loadFont(Application.class.getResourceAsStream("fonts/Lato/Lato-Bold.ttf"), UIConfig.fontRegularSize);
            regularSmallFont = Font.loadFont(Application.class.getResourceAsStream("fonts/Lato/Lato-Bold.ttf"), UIConfig.fontSmallSize);
            regularBigFont = Font.loadFont(Application.class.getResourceAsStream("fonts/Lato/Lato-Bold.ttf"), UIConfig.fontBigSize);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (regularFont == null)
            throw new NullPointerException("Regular font could not be be loaded!");
        if (regularSmallFont == null)
            throw new NullPointerException("Small font could not be be loaded!");
        if (regularBigFont == null)
            throw new NullPointerException("Big font could not be be loaded!");

        buttonBck = new Background(new BackgroundImage(buttonBckImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
    }
}
