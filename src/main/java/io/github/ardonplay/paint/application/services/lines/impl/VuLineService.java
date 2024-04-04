package io.github.ardonplay.paint.application.services.lines.impl;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import static java.lang.Math.*;

@Component("VuLine")
public class VuLineService extends AbstractDrawLineService {

    public VuLineService(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawObject(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint, Color color) {
        int x0 = firstPoint.getKey();
        int x1 = secondPoint.getKey();
        int y0 = firstPoint.getValue();
        int y1 = secondPoint.getValue();

        boolean steep = abs(y1 - y0) > abs(x1 - x0);
        if (steep)
            drawObject(new Pair<>(y0, x0), new Pair<>(y1, x1), color);

        if (x0 > x1)
            drawObject(new Pair<>(x1, y1), new Pair<>(x0, y0), color);

        double dx = x1 - x0;
        double dy = y1 - y0;
        double gradient = dy / dx;

        // handle first endpoint
        double xend = round(x0);
        double yend = y0 + gradient * (xend - x0);
        double xgap = rfpart(x0 + 0.5);
        double xpxl1 = xend; // this will be used in the main loop
        double ypxl1 = ipart(yend);

        if (steep) {
            plot(ypxl1, xpxl1, rfpart(yend) * xgap, color);
            plot(ypxl1 + 1, xpxl1, fpart(yend) * xgap, color);
        } else {
            plot(xpxl1, ypxl1, rfpart(yend) * xgap, color);
            plot(xpxl1, ypxl1 + 1, fpart(yend) * xgap, color);
        }

        // first y-intersection for the main loop
        double intery = yend + gradient;

        xend = x1;
        yend = y1 + gradient * (xend - x1);
        xgap = fpart(x1 + 0.5);
        double xpxl2 = xend; // this will be used in the main loop
        double ypxl2 = ipart(yend);

        if (steep) {
            plot(ypxl2, xpxl2, rfpart(yend) * xgap, color);
            plot(ypxl2 + 1, xpxl2, fpart(yend) * xgap, color);
        } else {
            plot(xpxl2, ypxl2, rfpart(yend) * xgap, color);
            plot(xpxl2, ypxl2 + 1, fpart(yend) * xgap, color);
        }

        // main loop
        for (double x = xpxl1 + 1; x <= xpxl2 - 1; x++) {
            if (steep) {
                plot(ipart(intery), x, rfpart(intery), color);
                plot(ipart(intery) + 1, x, fpart(intery), color);
            } else {
                plot(x, ipart(intery), rfpart(intery), color);
                plot(x, ipart(intery) + 1, fpart(intery), color);
            }
            intery = intery + gradient;
        }
    }

    int ipart(double x) {
        return (int) x;
    }

    double fpart(double x) {
        return x - floor(x);
    }

    double rfpart(double x) {
        return 1.0 - fpart(x);
    }


    void plot(double x, double y, double c, Color color) {
        pixelWriter.setColor((int) x, (int) y, new Color(color.getRed(), color.getGreen(), color.getBlue(), c));
    }
}
