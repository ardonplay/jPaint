package io.github.ardonplay.paint.application.services.circles;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

@Component("BasicCircle")
public class BasicCircleService extends AbstractDrawCirclesService {

    public BasicCircleService(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawObject(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint, Color color) {
        int centerX = firstPoint.getKey();
        int centerY = firstPoint.getValue();

        int a = 80;
        int b = 50;
        for (int x = -a; x <= a; x++) {
            int y = (int) Math.round(b * Math.sqrt(1 - (x * x) / (double) (a * a)));
            drawEllipsePoints(centerX, centerY, x, y, color);
            drawEllipsePoints(centerX, centerY, x, -y, color);
        }
    }
    private void drawEllipsePoints(int centerX, int centerY, int x, int y, Color color) {
        pixelWriter.setColor(centerX + x, centerY + y, color);
        pixelWriter.setColor(centerX - x, centerY + y, color);
    }
}
