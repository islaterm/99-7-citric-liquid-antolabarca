package com.github.cc3002.citricliquid.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayMessageHandler implements PropertyChangeListener {
    CitricLiquid citricLiquid;

    public DisplayMessageHandler(CitricLiquid citricLiquid){
        super();
        this.citricLiquid = citricLiquid;
    }

    /**
     * calls the gui method to display a message popup
     * @param evt the event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String msg = (String) evt.getNewValue();
        citricLiquid.displayMsg(msg);
    }
}
