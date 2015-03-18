package de.jug_gr.modelviewstar.mvpab;

import com.airhacks.afterburner.injection.Injector;
import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.business.LibraryServiceImpl;
import de.jug_gr.modelviewstar.business.LibraryServiceMockImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AppMVPAB extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Injector.setModelOrService(LibraryService.class, new LibraryServiceMockImpl());


        MainView mainView = new MainView();

        primaryStage.setTitle("Library MVP AfterBurner");
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMinHeight(700);

        Scene scene = new Scene(mainView.getView());

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);
    }
}
