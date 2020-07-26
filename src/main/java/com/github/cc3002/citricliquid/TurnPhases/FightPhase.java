package com.github.cc3002.citricliquid.TurnPhases;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.unit.BattleDecision;
import com.github.cc3002.citricjuice.model.unit.IUnit;
import com.github.cc3002.citricjuice.model.unit.Player;

public class FightPhase extends AbstractPhase {


    @Override
    public void action() {
        Player current = turn.getPlayer();
        IPanel panel = current.getCurrentPanel();
        panel.removePlayer(current);
        Player panelPlayer = panel.getPlayers().iterator().next(); //this is assuming that the panel only has one other player, I don't know what should happen in other cases
        panel.addPlayer(current);                                  // and this is awful, I know, but it guarantees getting a different player
        turn.getController().battle(current, panelPlayer);
        if (current.isDown()){
            turn.end();
        }
        else {
            turn.setPhase(new LandAtPanelPhase());
        }
    }
}
