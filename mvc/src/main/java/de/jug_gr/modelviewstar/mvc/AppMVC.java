package de.jug_gr.modelviewstar.mvc;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.business.LibraryServiceImpl;
import de.jug_gr.modelviewstar.business.LibraryServiceMockImpl;
import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMVC extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        EasyDI context = new EasyDI();
        context.bindInterface(LibraryService.class, LibraryServiceMockImpl.class);

        final Controller controller = context.getInstance(Controller.class);

        final View view = controller.getView();

        primaryStage.setTitle("Library MVC");
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMinHeight(700);

        primaryStage.setScene(new Scene(view));
        primaryStage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);
    }
}
