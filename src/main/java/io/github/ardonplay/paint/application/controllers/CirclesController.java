package io.github.ardonplay.paint.application.controllers;


import io.github.ardonplay.paint.application.controllers.utils.ControllerHandler;
import io.github.ardonplay.paint.application.services.circles.DrawCirclesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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

}
