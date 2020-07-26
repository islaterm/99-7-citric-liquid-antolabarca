package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricjuice.model.unit.BossUnit;

import java.beans.PropertyChangeEvent;

public class BossDefeatHandler extends AbstractHandler {

    public BossDefeatHandler(GameController controller){
        super(controller);
    }

    /**
     * Notifies the controller that a boss has been defeated
     * @param evt the propertyChangeEvent that signals a boss was defeated
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BossUnit boss = (BossUnit) evt.getNewValue();
        controller.bossDefeated(boss);
    }
}
