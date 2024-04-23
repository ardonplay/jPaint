package io.github.ardonplay.paint.application.services.circles.impl;

import io.github.ardonplay.paint.application.services.circles.AbstractDrawCirclesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Hyperbola")
public class HyperbolaService extends AbstractDrawCirclesService {

    public HyperbolaService(Canvas canvas) {
        super(canvas);
    }

    @Override

    public void drawObject(List<Pair<Integer, Integer>> points, Color color) {
        int centerX = points.get(0).getKey();
        int centerY = points.get(0).getValue();

         int a = 20;
         int b = 30;
        for (int x = -a; x <= a; x++) {
            int y = (int) Math.round(b * Math.sqrt(1 + (x * x) / (double) (a * a)));
            drawHyperbolaPoints(centerX, centerY, x, y, color);
            drawHyperbolaPoints(centerX, centerY, x, -y, color);
        }
    }

    private void drawHyperbolaPoints(int centerX, int centerY, int x, int y, Color color) {
        pixelWriter.setColor(centerX + x, centerY + y, color);
        pixelWriter.setColor(centerX - x, centerY + y, color);
    }
}
