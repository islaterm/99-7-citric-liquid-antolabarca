package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.beans.PropertyChangeEvent;

public class Norma4Handler extends AbstractHandler {

    public Norma4Handler(GameController controller){
        super(controller);
    }

    /**
     * Notifies the controller that a player has reached norma 4
     * @param evt the propertyChangeEvent that signals a player won
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Player norma4Player = (Player) evt.getNewValue();
        controller.playerReachedNorma4(norma4Player);
    }
}
