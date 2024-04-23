package io.github.ardonplay.paint.application.services.curves.impl;

import io.github.ardonplay.paint.application.services.curves.AbstractDrawCurvesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("Hermit")
public class HermitService extends AbstractDrawCurvesService {
    public HermitService(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawObject(List<Pair<Integer, Integer>> points, Color color) {
        Pair<Integer, Integer> startPoint = points.get(0);
        Pair<Integer, Integer> endPoint = points.get(3);
        Pair<Integer, Integer> startTangent = points.get(1);
        Pair<Integer, Integer> endTangent = points.get(2);
        int startX = startPoint.getKey();
        int startY = startPoint.getValue();
        int endX = endPoint.getKey();
        int endY = endPoint.getValue();
        int startTanX = startTangent.getKey();
        int startTanY = startTangent.getValue();
        int endTanX = endTangent.getKey();
        int endTanY = endTangent.getValue();

        double t = 0.0;
        double step = 0.0001;

        while (t <= 1.0) {
            double x = calculateHermiteValue(startX, startTanX, endX, endTanX, t);
            double y = calculateHermiteValue(startY, startTanY, endY, endTanY, t);
            pixelWriter.setColor((int) x, (int) y, color);
            t += step;
        }
    }

    private double calculateHermiteValue(int p0, int t0, int p1, int t1, double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double h1 = 2 * t3 - 3 * t2 + 1;
        double h2 = -2 * t3 + 3 * t2;
        double h3 = t3 - 2 * t2 + t;
        double h4 = t3 - t2;

        return h1 * p0 + h2 * p1 + h3 * t0 + h4 * t1;
    }
}
