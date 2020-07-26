package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.IEnemy;
import com.github.cc3002.citricjuice.model.unit.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EnemyTestHandler implements PropertyChangeListener {
    private PanelTest test;

    public EnemyTestHandler(PanelTest test){
        this.test= test;
    }

    /**
     * Notifies the controller that a fight must begin
     * @param evt the propertyChangeEvent that signals a player landed on an enemy panel
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        IEnemy enemy = (IEnemy) evt.getNewValue();
        test.setAttacker(enemy);
    }
}
