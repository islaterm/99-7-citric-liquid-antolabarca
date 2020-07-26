package com.github.cc3002.citricliquid.controller;

import java.util.Objects;

public abstract class AbstractHandler implements IHandler{
    GameController controller;

    public AbstractHandler(GameController controller){
        super();
        this.controller=controller;
    }

    /**
     * Checks if an object equals this Handler
     * @param o the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHandler that = (AbstractHandler) o;
        return Objects.equals(controller, that.controller);
    }

}
