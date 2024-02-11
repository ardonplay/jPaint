package io.github.ardonplay.paint;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class ColorChooser extends ComboBox<Slider> {
    private Color color;

    private static ColorChooser colorChooser;

    private ColorChooser() {
        super();
        setPromptText("Color");
        this.color = Color.rgb(255, 255, 255, 1);

        Slider redSlider = new Slider(0, 1, 1);
        Slider greenSlider = new Slider(0, 1, 1);
        Slider blueSlider = new Slider(0, 1, 1);
        Slider alphaSlider = new Slider(0, 1, 1);

        redSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.color = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), alphaSlider.getValue());
            propertyOnAction.get().handle(new ActionEvent());
        });
        greenSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.color = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), alphaSlider.getValue());

            propertyOnAction.get().handle(new ActionEvent());
        });
        blueSlider.valueProperty().addListener((observable, oldValue, newValue)  -> {
            this.color = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), alphaSlider.getValue());

            propertyOnAction.get().handle(new ActionEvent());
        });
        alphaSlider.valueProperty().addListener((observable, oldValue, newValue)  -> {
            this.color = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), alphaSlider.getValue());

            propertyOnAction.get().handle(new ActionEvent());
        });

        setItems(FXCollections.observableArrayList(redSlider, greenSlider, blueSlider, alphaSlider));

    }

    public static ColorChooser getInstance() {
        if (colorChooser == null) {
            colorChooser = new ColorChooser();
        }
        return colorChooser;
    }

    public Color getColor() {
        return color;
    }


    private final ObjectProperty<EventHandler<ActionEvent>> propertyOnAction = new SimpleObjectProperty<>();


    public final void onChangeColor(EventHandler<ActionEvent> handler) {
        propertyOnAction.set(handler);
    }

}
