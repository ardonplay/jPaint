package io.github.ardonplay.paint.application.controllers;

import io.github.ardonplay.paint.application.controllers.utils.ControllerHandler;
import io.github.ardonplay.paint.application.services.lines.DrawLineService;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LinesController extends AbstractObjectController {

    public LinesController(Canvas canvas, Map<String, DrawLineService> lines, ColorPicker colorPicker, ControllerHandler controllerHandler) {
        super(canvas, colorPicker, controllerHandler);
        this.name = "Lines";
        setValues(new HashMap<>(lines));
        setPromptText(name);
    }
}
