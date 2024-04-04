package io.github.ardonplay.paint.application;

import io.github.ardonplay.paint.application.controllers.AbstractObjectController;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NavigationBar extends VBox {

    public NavigationBar(ColorPicker colorPicker, List<AbstractObjectController> controllers) {
        super();
        super.setSpacing(10);
        super.setStyle("-fx-background-color: #333333; -fx-padding: 10px;");
        super.getChildren().addAll(colorPicker);
        super.getChildren().addAll(controllers.stream().map(AbstractObjectController::getSelector).collect(Collectors.toSet()));
    }


}
