package juggr.mvc_widget;

import javafx.scene.input.KeyCode;

public class Controller {

    private final View view;
    private final Model model;

    public Controller(){
        this.model = new Model();
        this.view = new View(model);

        view.registerInputObserver(keyCode -> {
            if(keyCode.isLetterKey()){
                model.setText(model.getText() + keyCode.getName());
            }else if(keyCode.equals(KeyCode.BACK_SPACE)){
                final String oldText = model.getText();
                if(oldText.length() != 0){
                    model.setText(oldText.substring(0, oldText.length()-1));
                }
            }
        });
    }

    public View getView(){
        return view;
    }
}
