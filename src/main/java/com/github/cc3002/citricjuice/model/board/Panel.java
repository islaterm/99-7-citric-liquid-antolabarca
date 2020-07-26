package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public class Panel implements IPanel { /*this is a neutral panel*/
    protected final int id; /* id of Panel*/
    protected Set<IPanel> nextPanels;
    protected Set<Player> players;
    private PropertyChangeSupport msgProperty = new PropertyChangeSupport(this);

    /**
     * Creates a new panel with coordenates x,y
     * @param id the id of the panel
     */
  public Panel(int id){
      this.id=id;
      nextPanels = new HashSet<>();
      players = new HashSet<>();
  }

    /**
     * Checks if an object o is equal to this Panel
     * @param o the object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panel panel = (Panel) o;
        return id == panel.id &&
                Objects.equals(nextPanels, panel.nextPanels);
    }


    /**
     * Returns a copy of this panel's next ones
     */
    public Set<IPanel> getNextPanels() {
        return Set.copyOf(nextPanels);
    }

    /**
    * Returns a list of this panel's next ones.
    */
    public ArrayList<IPanel> getNextPanelsList() {
        var nextPanels = new ArrayList<IPanel>();
        nextPanels.addAll(this.getNextPanels());
        return nextPanels;
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel
   *     the panel to be added.
   */
  public void addNextPanel(final IPanel panel) {
    if (!(this.equals(panel))){
        nextPanels.add(panel);
    }
  }

  /**
   * Activates the panel's action when a player lands on it
   * @param player
   *        the player that lands on the panel
   */
  public void activatedBy(Player player) { }

    /**
     * Adds a player to this panel, also adds this panel as player's panel
     * @param player
     *      the player
     */
    public void addPlayer(Player player){
        players.add(player);
        player.changePanel(this);
    }

  /**
  * Returns the set of players currently in this panel
  */
  @Override
  public Set<Player> getPlayers() { return Set.copyOf(players); }

    /**
     * Returns a list of this panel's players.
     */
    public ArrayList<Player> getPlayersList() {
        var nextPlayers = new ArrayList<Player>();
        nextPlayers.addAll(this.getPlayers());
        return nextPlayers;
    }

    /**
     * Removes a player from this panel's player set
     * @param player
     *      the player to be removed
     */
    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Adds a listener for this objects messages
     * @param listener the listener
     */
    @Override
    public void addMsgListener(PropertyChangeListener listener){
        msgProperty.addPropertyChangeListener(listener);
    }

    /**
     * sends a notification to the interface to display a popup msg
     * @param s the message
     */
    @Override
    public void sendMsg(String s){
        msgProperty.firePropertyChange("message", null, s);
    }



}


