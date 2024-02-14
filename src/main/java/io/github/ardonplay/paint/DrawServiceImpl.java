package io.github.ardonplay.paint;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import static java.lang.Math.*;


public class DrawServiceImpl implements DrawService {

    private String mode;


    private final PixelWriter pixelWriter;

    public DrawServiceImpl(PixelWriter pixelWriter) {
        this.pixelWriter = pixelWriter;
    }

    @Override
    public void setMode(String mode){
        this.mode = mode;
    }
    @Override
    public void printLine(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint, Color color) {
        switch (mode){
            case "Vu" -> vuLine(firstPoint, secondPoint, color);
            case "Bresenham" -> bresenhamLine(firstPoint, secondPoint,color);
            case "DDA" -> basicLine(firstPoint, secondPoint,color);
        }
    }

    @Override
    public void basicLine(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint, Color color) {

        int x1 = firstPoint.getKey();
        int x2 = secondPoint.getKey();
        int y1 = firstPoint.getValue();
        int y2 = secondPoint.getValue();

        drawLine(x1, y1, x2, y2, color);
    }

    @Override
    public void bresenhamLine(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint, Color color) {
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

    @Override
    public void vuLine(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint, Color color) {
        int x0 = firstPoint.getKey();
        int x1 = secondPoint.getKey();
        int y0 = firstPoint.getValue();
        int y1 = secondPoint.getValue();

        boolean steep = abs(y1 - y0) > abs(x1 - x0);
        if (steep)
           vuLine(new Pair<>(y0, x0), new Pair<>(y1, x1), color);

        if (x0 > x1)
            vuLine(new Pair<>(x1, y1), new Pair<>(x0, y0), color);

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

        xend = round(x1);
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
