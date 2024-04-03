package io.github.ardonplay.paint.application;

import io.github.ardonplay.paint.application.controllers.LinesController;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class NavigationBar extends VBox {

    public NavigationBar(ColorPicker colorPicker, LinesController linesController) {
        super();

        super.setSpacing(10);
        super.setStyle("-fx-background-color: #333333; -fx-padding: 10px;");
        super.getChildren().addAll(colorPicker, linesController.getSelector());
    }


}
