package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.unit.Player;
import javafx.util.Pair;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerMovementHandler implements PropertyChangeListener {
    CitricLiquid citricLiquid;

    public PlayerMovementHandler(CitricLiquid citricLiquid){
        super();
        this.citricLiquid = citricLiquid;
    }


    /**
     * calls the gui method that moves the sprite
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Pair<Player, IPanel> pair = (Pair<Player, IPanel>) evt.getNewValue();
        Player player= pair.getKey();
        IPanel panel= pair.getValue();
        citricLiquid.moveSprite(player,panel);

    }
}
