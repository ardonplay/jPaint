package io.github.ardonplay.paint.application.controllers;

import io.github.ardonplay.paint.application.controllers.utils.ControllerHandler;
import io.github.ardonplay.paint.application.services.DrawService;
import io.github.ardonplay.paint.application.services.circles.DrawCirclesService;
import io.github.ardonplay.paint.application.services.curves.DrawCurvesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CurversController extends AbstractObjectController {

    private final List<Pair<Integer, Integer>> points = new ArrayList<>();

    public CurversController(Canvas canvas, Map<String, DrawCurvesService> curves, ColorPicker colorPicker, ControllerHandler controllerHandler) {
        super(canvas, colorPicker, controllerHandler);
        this.name = "Curves";
        setValues(new HashMap<>(curves));
        setPromptText(name);
    }

    @Override
    protected void drawObject(MouseEvent event, DrawService drawService) {
        if(event.getClickCount() == 3){
            drawService.drawObject(points, colorPicker.valueProperty().get());
            points.clear();
            return;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        points.add(new Pair<>(x, y));
    }
}
