package com.scgraf.UI.elements.buttons;

import com.scgraf.UI.UIConfig;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class FormattedDropdown<T> extends ComboBox<T> {
    public FormattedDropdown(T[] list) {
        super();
        setBackground(new Background(new BackgroundFill(UIConfig.textFieldBckColor, new CornerRadii(UIConfig.borderRadius), Insets.EMPTY)));

        getEditor().setStyle("-fx-text-fill: #fff;" + "-fx-backgroundcolor: #000;");
        setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
            @Override public ListCell<T> call(ListView<T> param) {
                final ListCell<T> cell = new ListCell<T>() {
                    {
                        super.setPrefWidth(100);
                    }
                    @Override public void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.toString());
                            setTextFill(UIConfig.textColor);
                            setBackground(new Background(new BackgroundFill(UIConfig.textFieldBckColor, null, Insets.EMPTY)));
                        }
                        else {
                            setTextFill(UIConfig.textColor);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        getSelectionModel().selectFirst();
        getItems().setAll(list);
    }
}
