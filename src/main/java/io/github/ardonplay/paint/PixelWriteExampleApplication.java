package io.github.ardonplay.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PixelWriteExampleApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        PixelWriter pixelWriter = gc.getPixelWriter();

        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);


        scene.setOnMouseDragged(event -> {
            double x = event.getX(); // Получаем координату X, где был клик
            double y = event.getY(); // Получаем координату Y, где был клик
            pixelWriter.setColor((int)x, (int) y, Color.BLACK);
        });

        root.getChildren().add(canvas);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pixel paint example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
