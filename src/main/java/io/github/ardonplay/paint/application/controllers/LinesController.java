package io.github.ardonplay.paint.application.controllers;

import io.github.ardonplay.paint.application.controllers.utils.ControllerHandler;
import io.github.ardonplay.paint.application.services.DrawService;
import io.github.ardonplay.paint.application.services.lines.DrawLineService;
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
public class LinesController extends AbstractObjectController {

    private Pair<Integer, Integer> firstPoint;

    private boolean tapped;


    public LinesController(Canvas canvas, Map<String, DrawLineService> lines, ColorPicker colorPicker, ControllerHandler controllerHandler) {
        super(canvas, colorPicker, controllerHandler);
        this.name = "Lines";
        setValues(new HashMap<>(lines));
        setPromptText(name);
    }

    @Override
    protected void drawObject(MouseEvent event, DrawService drawService) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (!tapped) {
            firstPoint = new Pair<>(x, y);
            tapped = true;
        } else {
            Pair<Integer, Integer> secondPoint = new Pair<>(x, y);
            drawService.drawObject(List.of(firstPoint, secondPoint), colorPicker.valueProperty().get());
            tapped = false;
        }
    }
}
