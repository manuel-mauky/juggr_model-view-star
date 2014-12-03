package de.jug_gr.modelviewstar.mvppv;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.business.LibraryServiceImpl;
import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMVPPV extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        EasyDI context = new EasyDI();
        context.bindInterface(View.class, ViewImpl.class);
        context.bindInterface(Presenter.class, PresenterImpl.class);
        context.bindInterface(LibraryService.class, LibraryServiceImpl.class);

        final Presenter presenter = context.getInstance(Presenter.class);

        primaryStage.setTitle("Library MVP Passive View");
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMinHeight(700);

        primaryStage.setScene(new Scene(presenter.getView().getRoot()));
        primaryStage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);


    }
}
