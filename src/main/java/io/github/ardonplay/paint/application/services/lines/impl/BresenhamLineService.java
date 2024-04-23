package io.github.ardonplay.paint.application.services.lines.impl;

import io.github.ardonplay.paint.application.services.lines.AbstractDrawLineService;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("BresenhamLine")
public class BresenhamLineService extends AbstractDrawLineService {

    public BresenhamLineService(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawObject(List<Pair<Integer, Integer>> points, Color color) {
        var firstPoint = points.get(0);
        var secondPoint = points.get(1);
        int x1 = firstPoint.getKey();
        int y1 = firstPoint.getValue();
        int x2 = secondPoint.getKey();
        int y2 = secondPoint.getValue();

        int deltaX = Math.abs(x2 - x1);
        int deltaY = Math.abs(y2 - y1);
        int signX = x1 < x2 ? 1 : -1;
        int signY = y1 < y2 ? 1 : -1;

        var error = deltaX - deltaY;

        pixelWriter.setColor(x2, y2, color);

        while (x1 != x2 || y1 != y2) {
            pixelWriter.setColor(x1, y1, color);

            var error2 = error * 2;
            if (error2 > -deltaY) {
                error -= deltaY;
                x1 += signX;
            }

            if (error2 < deltaX) {
                error += deltaX;
                y1 += signY;
            }
        }
    }
}
