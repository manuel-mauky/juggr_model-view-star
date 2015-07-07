package fluxfx;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.business.LibraryServiceMockImpl;
import eu.lestard.easydi.EasyDI;
import eu.lestard.fluxfx.ViewLoader;
import fluxfx.views.MainView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FluxFxApp extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        EasyDI context = new EasyDI();
        context.bindInterface(LibraryService.class, LibraryServiceMockImpl.class);

        ViewLoader.setDependencyInjector(context::getInstance);

        final Parent root = ViewLoader.load(MainView.class);

        stage.setTitle("Library MVC");
        stage.setMinWidth(1200);
        stage.setMaxWidth(1200);
        stage.setMinHeight(700);


        stage.setScene(new Scene(root));
        stage.show();

        FlatterFX.style(FlatterInputType.DEFAULT);

    }
}
