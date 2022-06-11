package com.scgraf.UI.elements.buttons;

import com.scgraf.Application;
import com.scgraf.UI.UIConfig;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class FormattedDropdown<T> extends ComboBox<T> {
    public FormattedDropdown(T[] list) {
        super();
        getItems().setAll(list);
        getSelectionModel().selectFirst();
        setPrefWidth(UIConfig.btnPrefWidth);
        setStyle("-fx-text-fill: #f8f4e3; -fx-background-color: #363b44;");

        setCellFactory(new Callback<>() {
            @Override public ListCell<T> call(ListView<T> param) {
                return new ListCell<>() {
                    @Override public void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        param.setStyle("-fx-background-color: #363b44;");
                        setStyle("-fx-background-color: #363b44;");
                        setTextFill(UIConfig.textColor);
                        if(item != null){
                            setText(item.toString());
                            addEventHandler(MouseEvent.MOUSE_ENTERED, event -> OnMouseEnter());
                            addEventHandler(MouseEvent.MOUSE_EXITED, event -> OnMouseExit());
                        }
                    }
                };
            }
        });

        addEventHandler(MouseEvent.MOUSE_ENTERED, event -> OnMouseEnter());
        addEventHandler(MouseEvent.MOUSE_EXITED, event -> OnMouseExit());



    }

    private void OnMouseEnter(){
        Application.scene.setCursor(Cursor.HAND);
    }

    private void OnMouseExit(){
        Application.scene.setCursor(Cursor.DEFAULT);
    }
}
