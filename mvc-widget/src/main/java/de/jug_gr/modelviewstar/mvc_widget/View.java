package de.jug_gr.modelviewstar.mvc_widget;

import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class View extends StackPane{


    private final Rectangle rectangle = new Rectangle();
    private final Text text = new Text();

    public View(Model model){
        initLayout();

        text.setText(model.getText());

        model.registerTextObserver(text::setText);
    }

    private void initLayout() {
        this.setAlignment(Pos.BOTTOM_LEFT);

        this.setWidth(400);
        this.setHeight(50);

        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.GREY);
        rectangle.setWidth(this.getWidth());
        rectangle.setHeight(this.getHeight());
        rectangle.setStrokeWidth(2);
        text.setStyle("-fx-font-size:30");

        this.getChildren().add(rectangle);
        this.getChildren().add(text);
    }

    public void registerInputObserver(Consumer<KeyCode> eventObserver){
        this.addEventHandler(KeyEvent.KEY_PRESSED, event->eventObserver.accept(event.getCode()));
    }

}
