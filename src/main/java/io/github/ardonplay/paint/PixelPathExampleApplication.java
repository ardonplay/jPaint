package io.github.ardonplay.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;

public class PixelPathExampleApplication extends Application {

    List<Path> paths = new ArrayList<>();

    Path path;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);

        scene.setOnMousePressed(event -> {
            this.path = new Path();
            this.path.setStrokeWidth(2);
            this.path.setStroke(Color.BLACK);

            paths.add(this.path);

            root.getChildren().clear();
            root.getChildren().addAll(paths);
        });
        scene.setOnMouseDragged(this::drawLine);

        scene.setOnMouseReleased(event -> {
            this.path = null;
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Smooth Line Drawing");
        primaryStage.show();
    }

    private void drawLine(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        if (path.getElements().isEmpty()) {
            path.getElements().add(new javafx.scene.shape.MoveTo(x, y));
        } else {
            path.getElements().add(new javafx.scene.shape.LineTo(x, y));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

