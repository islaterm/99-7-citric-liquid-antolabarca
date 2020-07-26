package com.github.cc3002.citricliquid.TurnPhases;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.controller.GameController;
import javafx.util.Pair;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class Turn {
    private IPhase phase;
    private GameController controller;
    private PropertyChangeSupport msgProperty = new PropertyChangeSupport(this);
    private PropertyChangeSupport playerMoves = new PropertyChangeSupport(this);

    public Turn(GameController controller){
        setPhase(new FirstPhase());
        this.controller = controller;
    }


    /**
     * Checks if an object is equal to this turn
     * @param o the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return phase.equals(turn.phase) &&
                Objects.equals(controller, turn.controller);
    }


    /**
     * Sets this turns current phase
     * @param phase
     *      the phase the turn is on
     */
    public void setPhase(IPhase phase) {
        this.phase = phase;
        this.phase.setTurn(this);
    }

    /**
     * Returns this turns current phase
     */
    public IPhase getPhase(){
        return this.phase;
    }

    /**
     * Returns the current player
     */
    public Player getPlayer(){
        return this.controller.getTurnOwner();
    }

    /**
     * Returns the current chapter of the game
     */
    public int getChapter(){
        return controller.getChapter();
    }

    /**
     * Ends the current turn (goes back to the first phase with a new player)
     */
    public void end(){
        controller.endTurn();
        setPhase(new FirstPhase());
    }


    /**
     * Sends a notification to display a message
     */
    public void sendMsg(String s){
        msgProperty.firePropertyChange("message", null, s);
    }

    /**
     * Adds a listener for this objects messages
     * @param listener the listener
     */
    public void addMsgListener(PropertyChangeListener listener){
        msgProperty.addPropertyChangeListener(listener);
    }

    /**
     * Returns this turns controller
     */
    public GameController getController() {
        return controller;
    }

    /**
     * Adds a listener to players movement
     * @param listener the listener that is added
     */
    public void addPlayerMovementListener(PropertyChangeListener listener) {
        playerMoves.addPropertyChangeListener(listener);
    }

    /**
     * Sends a notification to the interface to move the player's sprite
     * @param old the previous panel
     * @param newPanel the panel to move to
     */
    public void moveNotification(IPanel old, IPanel newPanel, Player player) {
        playerMoves.firePropertyChange("moves", old, new Pair<>(player, newPanel));
    }
}
