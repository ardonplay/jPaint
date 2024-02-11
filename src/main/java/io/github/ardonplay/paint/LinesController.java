package io.github.ardonplay.paint;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;


public class LinesController {
    private Canvas canvas;
    private Pair<Integer, Integer> firstPoint;
    private Pair<Integer, Integer> secondPoint;

    private final DrawService drawService;
    private boolean tapped;
    public LinesController(Canvas canvas) {
        this.canvas = canvas;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.drawService = new DrawServiceImpl( gc.getPixelWriter());

    }

    public void subscribe(String mode){
        drawService.setMode(mode);
        this.canvas.setOnMousePressed(event -> {
            int x = (int) event.getX(); // Получаем координату X, где был клик
            int y = (int) event.getY(); // Получаем координату Y, где был клик

            if (!tapped) {
                firstPoint = new Pair<>(x, y);
                tapped = true;
            } else {
                secondPoint = new Pair<>(x, y);
                drawService.printLine(firstPoint, secondPoint, ColorChooser.getInstance().getColor());
                tapped = false;
            }
        });
    }

    public void unsubscribe(){
        this.canvas.setOnMousePressed(event -> {});
    }




}
