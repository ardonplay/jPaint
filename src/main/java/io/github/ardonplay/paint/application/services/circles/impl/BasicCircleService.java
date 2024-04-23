package io.github.ardonplay.paint.application.services.circles.impl;

import io.github.ardonplay.paint.application.services.circles.AbstractDrawCirclesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("BasicCircle")
public class BasicCircleService extends AbstractDrawCirclesService {

    public BasicCircleService(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawObject(List<Pair<Integer, Integer>> points, Color color) {
        var firstPoint = points.get(0);
        int centerX = firstPoint.getKey();
        int centerY = firstPoint.getValue();

        int radius = 100;
        int y = 0;
        int decisionOver2 = 1 - radius;

        while (radius >= y) {
            drawCirclePoints(centerX, centerY, radius, y, color);
            y++;

            if (decisionOver2 <= 0) {
                decisionOver2 += 2 * y + 1;
            } else {
                radius--;
                decisionOver2 += 2 * (y - radius) + 1;
            }
            drawCirclePoints(centerX, centerY, radius, y, color);
        }
    }

    private void drawCirclePoints(int centerX, int centerY, int x, int y, Color color) {
        pixelWriter.setColor(centerX + x, centerY + y, color);
        pixelWriter.setColor(centerX - x, centerY + y, color);
        pixelWriter.setColor(centerX + x, centerY - y, color);
        pixelWriter.setColor(centerX - x, centerY - y, color);
        pixelWriter.setColor(centerX + y, centerY + x, color);
        pixelWriter.setColor(centerX - y, centerY + x, color);
        pixelWriter.setColor(centerX + y, centerY - x, color);
        pixelWriter.setColor(centerX - y, centerY - x, color);
    }
}
