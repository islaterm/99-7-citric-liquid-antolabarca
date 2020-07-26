package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.board.IEnemyPanel;
import com.github.cc3002.citricjuice.model.board.IPanel;

public interface IEnemy extends IUnit {
    IEnemyPanel getPanel();
    void setPanel(IEnemyPanel panel);
}
