package io.github.ardonplay.paint.application.services;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractDrawService implements DrawService {

    protected final PixelWriter pixelWriter;

    public AbstractDrawService(Canvas canvas) {
        this.pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
    }

    @Override
    public abstract void drawObject(List<Pair<Integer, Integer>> points, Color color);
}
