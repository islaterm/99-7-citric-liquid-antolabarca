package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.beans.PropertyChangeEvent;

public class WinHandler extends AbstractHandler{

    public WinHandler(GameController controller){
        super(controller);
    }

    /**
     * Notifies the controller that a player has won
     * @param evt the propertyChangeEvent that signals a player won
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Player winner = (Player) evt.getNewValue();
        controller.playerWins(winner);
    }
}
