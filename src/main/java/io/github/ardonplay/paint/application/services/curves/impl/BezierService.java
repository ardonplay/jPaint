package io.github.ardonplay.paint.application.services.curves.impl;

import io.github.ardonplay.paint.application.services.curves.AbstractDrawCurvesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Bezier")
public class BezierService extends AbstractDrawCurvesService {
    public BezierService(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawObject(List<Pair<Integer, Integer>> points, Color color) {
        int n = points.size() - 1;
        double step = 0.0001;

        for (double t = 0.0; t <= 1.0; t += step) {
            double x = 0.0;
            double y = 0.0;

            for (int i = 0; i <= n; i++) {
                double coefficient = binomialCoefficient(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i);
                x += coefficient * points.get(i).getKey();
                y += coefficient * points.get(i).getValue();
            }

            pixelWriter.setColor((int) x, (int) y, color);
        }
    }

    private int binomialCoefficient(int n, int k) {
        int res = 1;
        if (k > n - k)
            k = n - k;
        for (int i = 0; i < k; ++i) {
            res *= (n - i);
            res /= (i + 1);
        }
        return res;
    }


}
