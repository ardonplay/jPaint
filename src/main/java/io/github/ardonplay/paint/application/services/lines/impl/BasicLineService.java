package io.github.ardonplay.paint.application.services.lines.impl;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

@Component("BasicLine")
public class BasicLineService extends AbstractDrawLineService {

    public BasicLineService(Canvas canvas) {
        super(canvas);
        this.name = "BasicLine";
    }

    @Override
    public void drawObject(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint, Color color) {
        int x1 = firstPoint.getKey();
        int x2 = secondPoint.getKey();
        int y1 = firstPoint.getValue();
        int y2 = secondPoint.getValue();

        drawLine(x1, y1, x2, y2, color);
    }

    private void drawLine(int x1, int y1, int x2, int y2, Color color) {

        double length = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));

        double deltaX = (x2 - x1) / length;

        double deltaY = (y2 - y1) / length;

        double xren = (x1 + 0.5 * Math.signum(deltaX));
        double yren = (y1 + 0.5 * Math.signum(deltaY));

        pixelWriter.setColor((int) xren, (int) yren, color);

        double i = 0;
        while (i <= length) {
            xren = xren + deltaX;
            yren = yren + deltaY;
            pixelWriter.setColor((int) xren, (int) yren, color);
            i = i + 1;
        }
    }
}
