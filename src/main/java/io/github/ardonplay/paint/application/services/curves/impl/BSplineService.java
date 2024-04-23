package io.github.ardonplay.paint.application.services.curves.impl;

import io.github.ardonplay.paint.application.services.curves.AbstractDrawCurvesService;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("BSpline")
public class BSplineService extends AbstractDrawCurvesService {
    public BSplineService(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawObject(List<Pair<Integer, Integer>> points, Color color) {
        int n = points.size() - 1;
        double step = 0.0001;

        for (double t = 2; t <= n; t += step) {
            double x = 0.0;
            double y = 0.0;

            for (int i = 0; i <= n; i++) {
                double basis = bSplineBasis(i, 3, t);
                x += basis * points.get(i).getKey();
                y += basis * points.get(i).getValue();
            }

            int roundedX = (int) Math.round(x);
            int roundedY = (int) Math.round(y);
            pixelWriter.setColor(roundedX, roundedY, color);
        }
    }

    private double bSplineBasis(int i, int k, double t) {
        if (k == 1) {
            if (i <= t && t < i + 1)
                return 1;
            return 0;
        }
        double denominator1 = i + k - 1 - i;
        double denominator2 = i + k - 1 - (i + k - 1 - 1);
        double basis1 = 0, basis2 = 0;
        if (denominator1 != 0)
            basis1 = ((t - i) / denominator1) * bSplineBasis(i, k - 1, t);
        if (denominator2 != 0)
            basis2 = ((i + k - t) / denominator2) * bSplineBasis(i + 1, k - 1, t);
        return basis1 + basis2;
    }
}
