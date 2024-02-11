package io.github.ardonplay.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaintApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox();

        Canvas canvas = new Canvas(400, 400);

        NavigationBar navigationBar = new NavigationBar(canvas);
        root.getChildren().add(navigationBar);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 400, 400);


        scene.widthProperty().addListener((obs, oldVal, newVal) -> canvas.setWidth((Double) newVal));

        scene.heightProperty().addListener((obs, oldVal, newVal) -> canvas.setHeight((Double) newVal));




        primaryStage.setScene(scene);
        primaryStage.setTitle("Pixel paint example");
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
