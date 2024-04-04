package io.github.ardonplay.paint.application.controllers.utils;

import io.github.ardonplay.paint.application.services.DrawService;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

import java.util.Map;

public class ObjectSelector extends ComboBox<String> {
    protected Map<String, DrawService> values;

    public ObjectSelector() {
        setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(getPromptText());
                } else {
                    setText(item);
                }
            }
        });
    }
}
