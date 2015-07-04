package de.jug_gr.modelviewstar.mvppv;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.business.LibraryServiceMockImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMVPPV extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LibraryService backend = new LibraryServiceMockImpl();

        View view = new ViewImpl();

        Model model = new Model();

        Presenter presenter = new PresenterImpl(backend, model, view);


        primaryStage.setTitle("Library MVP Passive de.jug_gr.modelviewstar.mvppv.View");
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMinHeight(700);

        primaryStage.setScene(new Scene(view.getRoot()));
        primaryStage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);


    }
}
