package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.IUnit;

import java.beans.PropertyChangeEvent;

public class BossBattleHandler extends AbstractHandler{

    public BossBattleHandler(GameController controller){
        super(controller);
    }

    /**
     * Notifies the controller that a fight must begin
     * @param evt the propertyChangeEvent that signals a player landed on an enemy panel
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        IUnit enemy = (BossUnit) evt.getNewValue();
        controller.battle(controller.getTurnOwner(), enemy);
    }
}
