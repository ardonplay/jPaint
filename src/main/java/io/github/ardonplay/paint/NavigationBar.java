package io.github.ardonplay.paint;

import javafx.collections.FXCollections;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class NavigationBar extends HBox {
    private  final ColorChooser colorChooser;

    private final LinesController linesController;
    public NavigationBar(Canvas canvas){
        super();
        this.colorChooser = ColorChooser.getInstance();

        this.linesController = new LinesController(canvas);

        ComboBox<String> lineSelector = new ComboBox<>();
        lineSelector.setItems(FXCollections.observableArrayList("DDA", "Bresenham", "Vu", ""));
        lineSelector.setPromptText("Lines");

        lineSelector.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(newValue.isEmpty()){
                linesController.unsubscribe();
            }else {
                linesController.subscribe(newValue);
            }
        });

        Rectangle colorPreview = new Rectangle(100, 20);
        colorPreview.setFill(colorChooser.getColor());

        colorChooser.onChangeColor(event -> colorPreview.setFill(colorChooser.getColor()));


        super.setSpacing(10);
        super.setStyle("-fx-background-color: #333333; -fx-padding: 10px;");
        super.getChildren().addAll(lineSelector, colorChooser, colorPreview);
    }


}
