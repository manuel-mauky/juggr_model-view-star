package de.jug_gr.modelviewstar.mvvm;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.business.LibraryServiceMockImpl;
import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMvvm extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        EasyDI context = new EasyDI();
        context.bindInterface(LibraryService.class, LibraryServiceMockImpl.class);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getInstance);

        fxmlLoader.setLocation(this.getClass().getResource("/View.fxml"));

        final Parent root = fxmlLoader.load();


        primaryStage.setTitle("Library MVC");
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMinHeight(700);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);
    }
}
