package io.github.ardonplay.paint.application.controllers;


import io.github.ardonplay.paint.application.controllers.utils.ControllerHandler;
import io.github.ardonplay.paint.application.services.DrawService;
import io.github.ardonplay.paint.application.services.circles.DrawCirclesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class CirclesController extends AbstractObjectController {


    public CirclesController(Canvas canvas, Map<String, DrawCirclesService> circles, ColorPicker colorPicker, ControllerHandler controllerHandler) {
        super(canvas, colorPicker, controllerHandler);
        this.name = "Circles";
        setValues(new HashMap<>(circles));
        setPromptText(name);
    }

    @Override
    protected void drawObject(MouseEvent event, DrawService drawService) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        drawService.drawObject(List.of(new Pair<>(x, y)), colorPicker.valueProperty().get());
    }
}
