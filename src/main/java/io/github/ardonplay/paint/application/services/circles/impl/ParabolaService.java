package io.github.ardonplay.paint.application.services.circles.impl;

import io.github.ardonplay.paint.application.services.circles.AbstractDrawCirclesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Parabola")
public class ParabolaService extends AbstractDrawCirclesService {

    public ParabolaService(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawObject(List<Pair<Integer, Integer>> points, Color color) {
        int focusX = points.get(0).getKey();
        int focusY = points.get(0).getValue();

        int a = 50;
        int direction = -1;

        for (int x = -a; x <= a; x++) {
            int y = direction * x * x / (2 * a);
            drawParabolaPoints(focusX, focusY, x, y, color);
        }
    }

    private void drawParabolaPoints(int centerX, int centerY, int x, int y, Color color) {
        pixelWriter.setColor(centerX + x, centerY + y, color);
        pixelWriter.setColor(centerX - x, centerY + y, color);
    }
}
