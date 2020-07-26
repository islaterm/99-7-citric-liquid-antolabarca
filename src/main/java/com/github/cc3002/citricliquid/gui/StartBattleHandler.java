package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.unit.IUnit;
import javafx.util.Pair;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

public class StartBattleHandler implements PropertyChangeListener {
    CitricLiquid citricLiquid;

    public StartBattleHandler(CitricLiquid citricLiquid){
        super();
        this.citricLiquid = citricLiquid;
    }

    /**
     * Calls the gui method that opens a new battle window
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Pair<IUnit, IUnit> pair = (Pair<IUnit, IUnit>) evt.getNewValue();
        IUnit unit1= pair.getKey();
        IUnit unit2= pair.getValue();
        try {
            citricLiquid.startBattle(unit1, unit2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
