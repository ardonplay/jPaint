package io.github.ardonplay.paint.application;

import io.github.ardonplay.paint.SpringPaintLoader;
import io.github.ardonplay.paint.application.config.ConfigurationService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class PaintApplication extends Application {

    private ConfigurableApplicationContext context;

    private NavigationBar navigationBar;

    private Canvas canvas;

    private int height;

    private int width;

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder()
                .sources(SpringPaintLoader.class)
                .run(getParameters().getRaw().toArray(new String[0]));

        this.navigationBar = context.getBean(NavigationBar.class);
        this.canvas = context.getBean(Canvas.class);

        ConfigurationService configurationService = context.getBean(ConfigurationService.class);

        this.height = configurationService.resolutionHeight;
        this.width = configurationService.resolutionWidth;
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        root.setLeft(navigationBar);
        root.setCenter(canvas);

        Scene scene = new Scene(root, height, width);

        scene.widthProperty().addListener((obs, oldVal, newVal) -> canvas.setWidth((Double) newVal));

        scene.heightProperty().addListener((obs, oldVal, newVal) -> canvas.setHeight((Double) newVal));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Jpaint");
        primaryStage.show();

        context.publishEvent(new ApplicationEvent(primaryStage) {
        });
    }


    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

}
