package io.github.ardonplay.paint.application.config;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConfigurationService {

    @Value("${application.resolution.height}")
    public int resolutionHeight;

    @Value("${application.resolution.width}")
    public int resolutionWidth;

    @Bean
    public ColorPicker colorPicker() {
        return new ColorPicker();
    }

    @Bean
    public Canvas canvas() {
        log.info("Resolution height: {}", resolutionHeight);
        log.info("Resolution width: {}", resolutionWidth);
        return new Canvas(resolutionHeight, resolutionWidth);
    }
}
