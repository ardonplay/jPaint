package io.github.ardonplay.paint.application.services;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

public interface DrawService {

    void drawObject(List<Pair<Integer, Integer>> points, Color color);
}
