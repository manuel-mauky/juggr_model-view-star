package de.jug_gr.modelviewstar.mvvmfx;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Library JavaFX");
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMinHeight(700);

        Scene scene = new Scene(FluentViewLoader.fxmlView(MainView.class).load().getView(), 1200, 700);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);
    }
}
