package de.jug_gr.modelviewstar.mvc_widget;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Model {

    private String text = "";
    private boolean focus;

    private List<Consumer<String>> textObserver = new ArrayList<>();

    public String getText(){
        return text;
    }

    public void setText(String newText){
        this.text = newText;
        notifyTextObservers();
    }

    public void registerTextObserver(Consumer<String> observer){
        this.textObserver.add(observer);
    }

    public void unregisterTextObserver(Consumer<String> observer){
        this.textObserver.remove(observer);
    }

    void notifyTextObservers(){
        this.textObserver.forEach(observer -> observer.accept(this.text));
    }

}
