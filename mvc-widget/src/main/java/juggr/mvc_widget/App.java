package juggr.mvc_widget;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        Controller textField = new Controller();


        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        root.getChildren().add(textField.getView());

        final Scene scene = new Scene(root, 800, 600);

        textField.getView().requestFocus();

        scene.setFill(Color.LIGHTGRAY);
        stage.setScene(scene);
        stage.show();

    }
}
