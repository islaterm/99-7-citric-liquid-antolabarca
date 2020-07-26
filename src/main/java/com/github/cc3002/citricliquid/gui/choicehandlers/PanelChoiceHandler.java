package com.github.cc3002.citricliquid.gui.choicehandlers;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricliquid.gui.CitricLiquid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

public class PanelChoiceHandler implements PropertyChangeListener {
    CitricLiquid citricLiquid;

    public PanelChoiceHandler(CitricLiquid citricLiquid){
        super();
        this.citricLiquid=citricLiquid;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Set<IPanel> panels = (Set<IPanel>) evt.getNewValue();
        citricLiquid.enablePanelButtons(panels);
    }
}
