package com.github.cc3002.citricliquid.TurnPhases;

public class RecoveryPhase extends AbstractPhase{


    /**
     * The player rolls the dice. If the roll is equal or more than her required roll, it changes to a Stars phase
     * If the roll is less, the turn ends.
     */
    public void action(){
        int x = turn.getPlayer().roll();
        int required_roll = turn.getPlayer().getRequiredRoll();
        if (x >= required_roll){
            turn.sendMsg(turn.getPlayer().getName()+" needed to roll "+required_roll+" to recover and she rolled a "+x+" so she has recovered!");
            turn.getPlayer().setCurrentHP(turn.getPlayer().getMaxHP());
            turn.setPhase(new StarsPhase());
        }else {
            turn.sendMsg(turn.getPlayer().getName()+" needed to roll "+required_roll+" to recover and she rolled a "+x+" so she couldn't recover");
            turn.getPlayer().setRequiredRoll(turn.getPlayer().getRequiredRoll()-1);
            turn.end();
        }
    }
}
