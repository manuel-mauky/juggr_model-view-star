package de.jug_gr.modelviewstar.mvvmfx;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import de.jug_gr.modeviewstar.business.Book;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
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

        final ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();
        Book book1 = createBook("Das Leben des Horst", "Horst", "Eine geschichte über Horst");
        Book book2 = createBook("Die Verwandlung", "Franz Kafka", "Als Gregor Samsa eines Morgens aus unruhigen Träumen erwachte...");

        viewTuple.getViewModel().booksProperty().add(new BookViewModel(book1));
        viewTuple.getViewModel().booksProperty().add(new BookViewModel(book2));


        Scene scene = new Scene(viewTuple.getView(), 1200, 700);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);
    }

    private Book createBook(String title, String author, String desc){
        return new Book(null, title, author, desc, null, null);
    }
}

