package com.github.cc3002.citricliquid.TurnPhases;

import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {
    Turn turn;
    Player player;
    Player p2;
    Player p3;
    Player p4;
    int i=0;
    GameController controller = new GameController();

    @BeforeEach
    public void setUp(){
        player = controller.createPlayer("p1",4,1,0,0,new Panel(0));
        p2 = controller.createPlayer("p2",4,1,0,0,new Panel(0));
        p3 = controller.createPlayer("p3",4,1,0,0,new Panel(0));
        p4 = controller.createPlayer("p4",4,1,0,0,new Panel(0));
        turn = new Turn(controller);
    }

    @Test
    public void testTurnConstructor(){
        assertEquals(turn, turn);
        assertNotEquals(turn, new Object());
        assertFalse(turn.equals(null));
        assertNotSame(new Turn(controller), turn);
        assertEquals(new Turn(controller), turn);//uwu
        FirstPhase expected = new FirstPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
    }

    @Test
    public void testPhase(){
        AbstractPhase expected;
        turn.setPhase(new FirstPhase());
        expected = new FirstPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new RecoveryPhase());
        expected = new RecoveryPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new StarsPhase());
        expected = new StarsPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new MovePhase(4));
        expected = new MovePhase(4);
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new FightDecisionPhase(3));
        expected = new FightDecisionPhase(3);
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new HomeDecisionPhase(3));
        expected = new HomeDecisionPhase(3);
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new PanelDecisionPhase(3));
        expected = new PanelDecisionPhase(3);
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
        turn.setPhase(new LandAtPanelPhase());
        expected = new LandAtPanelPhase();
        expected.setTurn(turn);
        assertEquals(expected, turn.getPhase());
    }

    @Test
    public void testPlayer(){
        assertEquals(player, turn.getPlayer());
    }

    @Test
    public void getChapter(){
        assertEquals(1, turn.getChapter());
        controller.endTurn();
        assertEquals(p2, turn.getPlayer());
        assertEquals(1, turn.getChapter());
        controller.endTurn();
        controller.endTurn();
        controller.endTurn();
        controller.endTurn();
        assertEquals(2,turn.getChapter());
    }

}