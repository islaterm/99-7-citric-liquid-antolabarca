package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BossPanel extends Panel implements IEnemyPanel{
  BossUnit boss;
  PropertyChangeSupport notification = new PropertyChangeSupport(this);

  public BossPanel(int id){ super(id);}

  /**
   * Sets this panels boss unit
   * @param enemy
   */
  public void setBoss(BossUnit enemy){
    this.boss=enemy;
    boss.setPanel(this);
  }

  /**
   * Returns the current boss of this panel
   */
  public BossUnit getUnit() { return boss;  }

  /**
   * Starts a battle with the player
   * @param player
   */
  @Override
  public void activatedBy(Player player) {
    startBossBattle();
  }

  /**
   * Signals the controller that a battle should be started
   */
  public void startBossBattle(){this.notification.firePropertyChange("boss battle start", null, boss);}

  /**
   * adds listener
   */
  public void addBossPanelListener(PropertyChangeListener listener){
    notification.addPropertyChangeListener(listener);
  }
}
