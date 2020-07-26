package com.github.cc3002.citricliquid.TurnPhases;


import com.github.cc3002.citricjuice.model.unit.Player;


public class MovePhase extends AbstractMovementPhase{

    public MovePhase(int y){
        super(y);
    }


    /**
     * Moves the player to a new panel, stopping along the way if decision needs to be made, and changes phase to the land at panel phase
     */
    @Override
    public void action() {
        Player player = turn.getPlayer();
        int x = player.move(y);
        turn.moveNotification(null, player.getCurrentPanel(), player);
        if (x==0){ //the player moved all the steps they had left
            turn.setPhase(new LandAtPanelPhase());
        } else if (turn.getPlayer().getCurrentPanel().getPlayers().size()>1){ //there is another player in the panel
            turn.setPhase(new FightDecisionPhase(x));
        }
        else if (turn.getPlayer().getCurrentPanel().getNextPanels().size()>1){ //the player needs to decide a panel
            turn.setPhase(new PanelDecisionPhase(x));
        } else if (turn.getPlayer().getCurrentPanel()==turn.getPlayer().getHome()){
            turn.setPhase(new HomeDecisionPhase(x));
        }

    }
}
