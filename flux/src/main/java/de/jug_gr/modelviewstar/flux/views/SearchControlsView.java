package de.jug_gr.modelviewstar.flux.views;

import de.jug_gr.modelviewstar.flux.Dispatcher;
import de.jug_gr.modelviewstar.flux.actions.SearchAction;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchControlsView {

    @FXML
    public TextField searchTextField;

    private final Dispatcher dispatcher;

    public SearchControlsView(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    public void searchButtonPressed() {
        dispatcher.dispatch(new SearchAction(searchTextField.getText()));
    }
}
