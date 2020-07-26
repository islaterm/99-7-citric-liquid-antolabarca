package com.github.cc3002.citricliquid.TurnPhases;

public class FirstPhase extends AbstractPhase{

    public FirstPhase(){super();}


    /**
     * The first phase of the turn, checks if the player needs to recover and changes to the accoring phase
     */
    public void action(){
        turn.sendMsg(turn.getController().getTurnOwner().getName()+"'s turn!");
        if(turn.getPlayer().isDown()){
            turn.setPhase(new RecoveryPhase());
        }else {
            turn.setPhase(new StarsPhase());
        }
    }
}
