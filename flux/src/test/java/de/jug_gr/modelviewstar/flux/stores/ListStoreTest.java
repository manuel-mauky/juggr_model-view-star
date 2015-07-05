package de.jug_gr.modelviewstar.flux.stores;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.flux.Dispatcher;
import de.jug_gr.modelviewstar.flux.actions.SearchAction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListStoreTest {


    private ListStore store;

    private LibraryService backend;

    private Dispatcher dispatcher;

    @Before
    public void setup() {
        backend = mock(LibraryService.class);
        dispatcher = mock(Dispatcher.class);

        store = new ListStore(backend, dispatcher);
    }

    @Test
    public void testSearchSuccessful() {
        // given
        List<Book> result = new ArrayList<>();
        final Book book = new Book("/1", "testTitle", "testAuthor", "testDescription");
        result.add(book);

        when(backend.search(eq("testTitle"), any())).thenReturn(result);

        assertThat(store.getBooks()).isEmpty();


        // when
        store.processAction(new SearchAction("testTitle"));


        // then
        assertThat(store.getBooks()).hasSize(1).contains(book);
        assertThat(store.getError()).isEmpty();

    }

    @Test
    public void testSearchError() {

        // given
        when(backend.search(eq("something"), any())).thenAnswer(invocation -> {
            final Object[] arguments = invocation.getArguments();
            assertThat(arguments.length).isEqualTo(2);
            assertThat(arguments[1]).isInstanceOf(Consumer.class);

            Consumer<ErrorObject> errorConsumer = (Consumer<ErrorObject>) arguments[1];

            errorConsumer.accept(ErrorObject.error("testError", "testDetails"));

            return Collections.emptyList();
        });


        // when
        store.processAction(new SearchAction("something"));

        // then

        assertThat(store.getBooks()).isEmpty();
        assertThat(store.getError()).isPresent();

        final ErrorObject error = store.getError().get();
        assertThat(error.getMessage()).isEqualTo("testError");
        assertThat(error.getDetails()).isEqualTo("testDetails");

    }
}
