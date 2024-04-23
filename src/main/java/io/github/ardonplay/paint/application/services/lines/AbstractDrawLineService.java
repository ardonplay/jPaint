package io.github.ardonplay.paint.application.services.lines;

import io.github.ardonplay.paint.application.services.AbstractDrawService;
import javafx.scene.canvas.Canvas;

public abstract class AbstractDrawLineService extends AbstractDrawService implements DrawLineService {

    public AbstractDrawLineService(Canvas canvas) {
        super(canvas);
    }
}
