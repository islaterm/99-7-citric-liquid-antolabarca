package com.github.cc3002.citricliquid.TurnPhases;

public class LandAtPanelPhase extends AbstractPhase{

    LandAtPanelPhase(){

    }

    /**
     * The player activates the panel where she landed, and the turn ends
     */
    @Override
    public void action() {
        turn.getPlayer().activatePanel();
        turn.getController().endTurn();
        turn.setPhase(new FirstPhase());
    }
}
