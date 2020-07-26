package com.github.cc3002.citricliquid.TurnPhases;

import com.github.cc3002.citricjuice.model.unit.HomeDecision;

public class HomeDecisionPhase extends AbstractMovementPhase{

    public HomeDecisionPhase(int y) {
        super(y);
    }

    /**
     * Checks the players homeDecision and creates a new LandAtPanelPhase if its true, a new MovePhase if its false
     */
    @Override
    public void action() {
        while (turn.getPlayer().getHomeDecision()==null){
            ;
        }
        HomeDecision stopAtHome = turn.getPlayer().getHomeDecision();
        if (stopAtHome == HomeDecision.STOP){
            turn.getPlayer().setHomeDecision(null);
            turn.setPhase(new LandAtPanelPhase());
        } else{
            turn.getPlayer().setHomeDecision(null);
            turn.setPhase(new MovePhase(y));
        }
    }
}
