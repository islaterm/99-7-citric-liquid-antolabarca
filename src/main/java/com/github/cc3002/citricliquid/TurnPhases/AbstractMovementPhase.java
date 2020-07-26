package com.github.cc3002.citricliquid.TurnPhases;

public abstract class AbstractMovementPhase extends AbstractPhase implements IMovementPhase{
    int y;

    public AbstractMovementPhase(int y) {
        super();
        this.y=y;
    }

    /**
     * returns this phases y value
     */
    public int getY(){return this.y;}


    /**
     * Checks if another object equals this one
     * @param o the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IMovementPhase that = (IMovementPhase) o;
        return y == that.getY();
    }

}
