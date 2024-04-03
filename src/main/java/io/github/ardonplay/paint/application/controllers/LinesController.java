package io.github.ardonplay.paint.application.controllers;

import io.github.ardonplay.paint.application.services.lines.DrawLineService;
import io.github.ardonplay.paint.application.services.DrawService;
import javafx.collections.FXCollections;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
@Slf4j
@Component
@RequiredArgsConstructor
public class LinesController {

    private final Canvas canvas;

    private final ColorPicker colorPicker;

    private Pair<Integer, Integer> firstPoint;
    private Pair<Integer, Integer> secondPoint;


    private final  Map<String, DrawLineService> lines;

    private boolean tapped;


    public void subscribe(String mode) {
        DrawService drawService = lines.get(mode);
        this.canvas.setOnMousePressed(event -> {
            int x = (int) event.getX(); // Получаем координату X, где был клик
            int y = (int) event.getY(); // Получаем координату Y, где был клик

            if (!tapped) {
                firstPoint = new Pair<>(x, y);
                tapped = true;
            } else {
                secondPoint = new Pair<>(x, y);
                drawService.drawObject(firstPoint, secondPoint, colorPicker.valueProperty().get());
                tapped = false;
            }
        });
    }

    public void unsubscribe(){
        this.canvas.setOnMousePressed(event -> {});
    }

    public ComboBox<String> getSelector(){
        ComboBox<String> lineSelector = new ComboBox<>();
        lineSelector
                .setItems(lines.keySet().stream()
                .collect(Collectors.collectingAndThen(toList(), FXCollections::observableArrayList)));
        lineSelector.setPromptText("Lines");

        lineSelector.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                unsubscribe();
            } else {
                subscribe(newValue);
            }
        });

        return lineSelector;
    }


}
