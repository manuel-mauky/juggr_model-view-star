package de.jug_gr.modelviewstar.mvppv;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.LibraryService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class PresenterTest {

    private Presenter presenter;
    private View view;
    private Model model;
    private LibraryService libraryService;

    @Before
    public void setup(){
        view = mock(View.class);

        libraryService = mock(LibraryService.class);
        model = new Model(libraryService);

        presenter = new PresenterImpl(model, view);
    }


    @Test
    public void testSearch(){
        Book book1 = createBook("a book starting with a", "some author", null);
        Book book1WithDescription = createBook("a book starting with a", "some author", "some description 1");
        Book book2 = createBook("another book starting with a", "some author", null);
        Book book2WithDescription = createBook("another book starting with a", "some author", "some description 2");
        Book book3 = createBook("book starting with b", "some author", null);
        Book book3WithDescription = createBook("book starting with b", "some author", "some description 3");

        when(libraryService.search(eq("a"), any())).thenReturn(Arrays.asList(book1, book2));
        when(libraryService.search(eq("b"), any())).thenReturn(Arrays.asList(book3));
        when(libraryService.search(eq(""), any())).thenReturn(Collections.emptyList());

        when(libraryService.showDetails(eq(book1), any())).thenReturn(book1WithDescription);
        when(libraryService.showDetails(eq(book2), any())).thenReturn(book2WithDescription);
        when(libraryService.showDetails(eq(book3), any())).thenReturn(book3WithDescription);


        when(view.getSearchString()).thenReturn("a");

        presenter.search();

        List<String> expectedTitles = new ArrayList<>();
        expectedTitles.add("a book starting with a");
        expectedTitles.add("another book starting with a");
        verify(view).setBooks(expectedTitles);

    }

    private Book createBook(String title, String author, String desc){
        return new Book(null, title, author, desc);
    }
}
