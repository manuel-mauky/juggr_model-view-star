package de.jug_gr.modelviewstar.flux;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.business.LibraryServiceMockImpl;
import eu.lestard.easydi.EasyDI;

import java.net.URL;


public class FluxApp extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        EasyDI context = new EasyDI();
        context.bindInterface(LibraryService.class, LibraryServiceMockImpl.class);


        final URL resource = this.getClass().getResource("/MainView.fxml");
        if (resource == null) {
            throw new IllegalStateException("Can't find fxml file");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setControllerFactory(context::getInstance);

        final Parent root = fxmlLoader.load();


        stage.setTitle("Library MVC");
        stage.setMinWidth(1200);
        stage.setMaxWidth(1200);
        stage.setMinHeight(700);


        stage.setScene(new Scene(root));
        stage.show();

        FlatterFX.style(FlatterInputType.DEFAULT);
    }
}
