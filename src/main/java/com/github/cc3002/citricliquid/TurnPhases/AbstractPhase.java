package com.github.cc3002.citricliquid.TurnPhases;

public abstract class AbstractPhase implements IPhase {
    Turn turn;

    /**
     * Sets this phases corresponding turn
     * @param turn
     *      the turn
     */
    @Override
    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    /**
     * Gets this phases corresponding turn
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * Checks if another object is equal to this phase
     * @param o the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; //this checks that they are the same type of phase only, since they are equivalent if their phase is the same
    }

}
