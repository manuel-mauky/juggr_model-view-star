package de.jug_gr.modelviewstar.flux.stores;

import java.util.HashSet;
import java.util.Set;

public abstract class StoreBase implements Store {

    private Set<Runnable> onChangeListener = new HashSet<>();

    @Override
    public void onChange(Runnable runnable) {
        onChangeListener.add(runnable);
    }

    protected void publishOnChange() {
        onChangeListener.forEach(Runnable::run);
    }
}
