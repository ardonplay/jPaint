package io.github.ardonplay.paint.application.services.circles;

import io.github.ardonplay.paint.application.services.AbstractDrawService;
import javafx.scene.canvas.Canvas;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDrawCirclesService extends AbstractDrawService implements DrawCirclesService {

    public AbstractDrawCirclesService(Canvas canvas) {
        super(canvas);
    }
}
