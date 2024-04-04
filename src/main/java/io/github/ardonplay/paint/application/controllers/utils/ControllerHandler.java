package io.github.ardonplay.paint.application.controllers.utils;

import io.github.ardonplay.paint.application.controllers.AbstractObjectController;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class ControllerHandler {

    private final List<AbstractObjectController> controllers;

    public ControllerHandler() {
        this.controllers = new ArrayList<>();
    }

    public void addController(AbstractObjectController controller) {
        this.controllers.add(controller);
    }

    public void removeController(AbstractObjectController controller) {
        this.controllers.remove(controller);
    }

}
