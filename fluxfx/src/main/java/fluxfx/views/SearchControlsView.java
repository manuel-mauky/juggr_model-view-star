package fluxfx.views;

import eu.lestard.fluxfx.View;
import fluxfx.actions.SearchAction;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchControlsView implements View {
    @FXML
    public TextField searchTextField;

    public void searchButtonPressed() {
        publishAction(new SearchAction(searchTextField.getText()));
    }
}
