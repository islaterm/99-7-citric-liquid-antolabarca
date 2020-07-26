package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EncounterPanel extends Panel implements IEnemyPanel{
  WildUnit wild;
  PropertyChangeSupport notification = new PropertyChangeSupport(this);

  public EncounterPanel(int id){
    super(id);
    setWild(new WildUnit("Wildie", 4,1,0,0));
  }

  /**
   * Sets this panels wild unit
   * @param wild
   */
  public void setWild(WildUnit wild){
    this.wild=wild;
    wild.setPanel(this);
  }

  /**
   * Returns this panels wild unit
   */
  public WildUnit getUnit(){
    return wild;
  }

  /**
   * Starts a battle with this wild unit
   */
  @Override
  public void activatedBy(Player player){
    startWildBattle();
  }

   /**
   * Signals the controller that a battle should be started
   */
  public void startWildBattle(){
    System.out.println("start wild battle");
    this.notification.firePropertyChange("wild battle start", null, wild);
  }

  /**
   * adds listener
   */
  public void addEncounterPanelListener(PropertyChangeListener listener){
    notification.addPropertyChangeListener(listener);
  }

}