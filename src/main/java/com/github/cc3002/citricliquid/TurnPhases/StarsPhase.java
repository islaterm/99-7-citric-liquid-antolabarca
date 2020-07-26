package com.github.cc3002.citricliquid.TurnPhases;

public class StarsPhase extends AbstractPhase{

    /**
     * The player gets the amount of stars required as per the chapter of the game.
     * The player rolls the dice and the phase changes to a move phase
     */
    @Override
    public void action() {
        int stars = turn.getChapter()/5 + 1;
        turn.sendMsg(turn.getPlayer().getName()+" gained "+stars+" stars!");
        turn.getPlayer().increaseStarsBy(stars);
        int x = turn.getPlayer().roll();
        turn.sendMsg(turn.getPlayer().getName()+" rolled "+x);
        turn.setPhase(new MovePhase(x));
    }

}
