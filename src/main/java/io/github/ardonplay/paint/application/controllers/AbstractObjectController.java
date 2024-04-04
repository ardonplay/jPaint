package io.github.ardonplay.paint.application.controllers;

import io.github.ardonplay.paint.application.controllers.utils.ControllerHandler;
import io.github.ardonplay.paint.application.controllers.utils.ObjectSelector;
import io.github.ardonplay.paint.application.services.DrawService;
import javafx.collections.FXCollections;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
public abstract class AbstractObjectController {

    protected final Canvas canvas;

    protected String name;

    protected final ColorPicker colorPicker;
    protected final ControllerHandler controllerHandler;
    protected Pair<Integer, Integer> firstPoint;
    protected Pair<Integer, Integer> secondPoint;

    protected Map<String, DrawService> values;

    protected boolean tapped;

    @Getter
    protected final ObjectSelector selector;

    protected AbstractObjectController(Canvas canvas, ColorPicker colorPicker, ControllerHandler controllerHandler) {
        this.canvas = canvas;
        this.colorPicker = colorPicker;
        this.controllerHandler = controllerHandler;
        controllerHandler.addController(this);
        this.selector = new ObjectSelector();
        selector.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> Optional.ofNullable(newValue).ifPresent(this::subscribe));
    }

    protected void setValues(Map<String, DrawService> values) {
        this.values = values;
        selector.setItems(values.keySet().stream()
                .collect(Collectors.collectingAndThen(toList(), FXCollections::observableArrayList)));
    }

    protected void subscribe(String mode) {

        controllerHandler.getControllers().stream()
                .filter(controller -> !controller.equals(this))
                .collect(Collectors.toSet())
                .forEach(controller -> {
                    controller.unsubscribe();
                    log.info("Controller {} unsubscribed", controller);
                });

        DrawService drawService = values.get(mode);
        this.canvas.setOnMousePressed(event -> drawObject(event, drawService));
    }

    private void drawObject(MouseEvent event, DrawService drawService) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (!tapped) {
            firstPoint = new Pair<>(x, y);
            tapped = true;
        } else {
            secondPoint = new Pair<>(x, y);
            drawService.drawObject(firstPoint, secondPoint, colorPicker.valueProperty().get());
            tapped = false;
        }
    }

    public void unsubscribe() {
        selector.setValue(null);
    }

    protected void setPromptText(String text) {
        this.selector.setPromptText(text);
    }

}
