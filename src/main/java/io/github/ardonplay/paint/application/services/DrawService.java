package io.github.ardonplay.paint.application.services;

import javafx.scene.paint.Color;
import javafx.util.Pair;

public interface DrawService {

    void drawObject(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint, Color color);
}
