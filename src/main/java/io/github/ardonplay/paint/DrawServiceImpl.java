package io.github.ardonplay.paint;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.util.Pair;

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
        int x1 = firstPoint.getKey();
        int x2 = secondPoint.getKey();
        int y1 = firstPoint.getValue();
        int y2 = secondPoint.getValue();

        if (x2 < x1) {
            x1 += x2;
            x2 = x1 - x2;
            x1 -= x2;
            y1 += y2;
            y2 = y1 - y2;
            y1 -= y2;
        }

        int dx = x2 - x1;
        int dy = y2 - y1;

        if (dx == 0 || dy == 0) {
            drawLine(x1, y1, x2, y2, color);
            return;
        }

        float gradient;

        if (dx > dy) {
            System.out.println("dx > dy");
            gradient = (float) dy / dx;
            float intery = y1 + gradient;
            pixelWriter.setColor(x1, y1, color);
            for (int x = x1; x < x2; ++x) {
                pixelWriter.setColor(x, (int)intery, new Color(color.getRed(), color.getBlue(),color.getGreen(), (1 - fractionalPart(intery) * 1))); //Меняем прозрачность
                pixelWriter.setColor(x, (int)intery + 1,new Color(color.getRed(), color.getBlue(),color.getGreen(),  fractionalPart(intery) * 1));
                intery += gradient;
            }
            pixelWriter.setColor(x2, y2, color);
        }
        else {
            System.out.println("dx < dy");
            gradient = (float) dx / dy;
            float interx = x1 + gradient;
            pixelWriter.setColor(x1, y1, color);
            for (int y = y1; y < y2; ++y) {
                pixelWriter.setColor((int)interx, y, new Color(color.getRed(), color.getBlue(),color.getGreen(), (fractionalPart(interx) * 1)));
                pixelWriter.setColor((int)interx + 1, y,new Color(color.getRed(), color.getBlue(),color.getGreen(), (fractionalPart(interx) * 1)));
                interx += gradient;
            }
            pixelWriter.setColor(x2, y2, color);
        }

    }

    private float fractionalPart(float x) {
        int tmp = (int) x;
        return x - tmp;
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
