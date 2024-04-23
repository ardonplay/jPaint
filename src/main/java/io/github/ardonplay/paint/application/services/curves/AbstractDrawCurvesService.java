package io.github.ardonplay.paint.application.services.curves;

import io.github.ardonplay.paint.application.services.AbstractDrawService;
import javafx.scene.canvas.Canvas;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDrawCurvesService extends AbstractDrawService implements DrawCurvesService {

    public AbstractDrawCurvesService(Canvas canvas) {
        super(canvas);
    }
}
