package io.github.ardonplay.paint.application.services.lines.impl;

import io.github.ardonplay.paint.application.services.AbstractDrawService;
import io.github.ardonplay.paint.application.services.lines.DrawLineService;
import javafx.scene.canvas.Canvas;

public abstract class AbstractDrawLineService extends AbstractDrawService implements DrawLineService {

    public AbstractDrawLineService(Canvas canvas) {
        super(canvas);
    }
}
