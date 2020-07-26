package com.github.cc3002.citricliquid.gui.choicehandlers;

import com.github.cc3002.citricliquid.gui.CitricLiquid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FightChoiceHandler implements PropertyChangeListener {
    CitricLiquid citricLiquid;

    public FightChoiceHandler(CitricLiquid citricLiquid){
        super();
        this.citricLiquid=citricLiquid;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { citricLiquid.enableFightChoiceButtons(); }
}
