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
		LibraryService backend = new LibraryServiceMockImpl();
		
		
		Model model = new Model();

		View view = new View(model);
		
		Controller controller = new Controller(model, view, backend);
		
        primaryStage.setTitle("Library MVC");
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMinHeight(700);

        primaryStage.setScene(new Scene(view));
        primaryStage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);
    }
}
