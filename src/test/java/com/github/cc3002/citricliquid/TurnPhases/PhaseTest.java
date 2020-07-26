package com.github.cc3002.citricliquid.TurnPhases;

import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricjuice.model.unit.BattleDecision;
import com.github.cc3002.citricjuice.model.unit.FightDecision;
import com.github.cc3002.citricjuice.model.unit.HomeDecision;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.TurnPhases.*;
import com.github.cc3002.citricliquid.controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PhaseTest {
    Turn turn;
    Player player;
    Player p2;
    Player p3;
    Player p4;
    GameController controller;
    int i = 0;
    FirstPhase first;
    LandAtPanelPhase landAtPanel;
    MovePhase move;
    RecoveryPhase recovery;
    StarsPhase stars;
    FightPhase fight;
    FightDecisionPhase fightDecision;
    HomeDecisionPhase homeDecision;
    PanelDecisionPhase panelDecision;


    @BeforeEach
    public void setUp() {
        controller = new GameController();
        player = controller.createPlayer("p1",4,1,0,0,new Panel(0));
        p2 = controller.createPlayer("p2",4,1,0,0,new Panel(0));
        p3 = controller.createPlayer("p3",4,1,0,0,new Panel(0));
        p4 = controller.createPlayer("p4",4,1,0,0,new Panel(0));
        turn = new Turn(controller);
        first = new FirstPhase();
        first.setTurn(turn);
        landAtPanel = new LandAtPanelPhase();
        landAtPanel.setTurn(turn);
        move = new MovePhase(4);
        move.setTurn(turn);
        recovery = new RecoveryPhase();
        recovery.setTurn(turn);
        stars = new StarsPhase();
        stars.setTurn(turn);
        fight = new FightPhase();
        fight.setTurn(turn);
        fightDecision = new FightDecisionPhase(3);
        fightDecision.setTurn(turn);
        homeDecision = new HomeDecisionPhase(3);
        homeDecision.setTurn(turn);
        panelDecision = new PanelDecisionPhase(3);
        panelDecision.setTurn(turn);
    }

    @Test
    public void testFirstPhaseConstructor() {
        assertEquals(first, first);
        assertNotEquals(first, new Object());
        assertFalse(first.equals(null));
        assertNotSame(new FirstPhase(), first);
        assertEquals(new FirstPhase(), first);
    }

    @Test
    public void testLandAtPanelPhaseConstructor() {
        assertEquals(landAtPanel, landAtPanel);
        assertNotEquals(landAtPanel, new Object());
        assertFalse(landAtPanel.equals(null));
        assertNotSame(new LandAtPanelPhase(), landAtPanel);
        assertEquals(new LandAtPanelPhase(), landAtPanel);
    }

    @Test
    public void testMovePhaseConstructor() {
        assertEquals(move, move);
        assertNotEquals(move, new Object());
        assertFalse(move.equals(null));
        assertNotSame(new MovePhase(4), move);
        assertEquals(new MovePhase(4), move);
    }

    @Test
    public void testRecoveryPhaseConstructor() {
        assertEquals(recovery, recovery);
        assertNotEquals(recovery, new Object());
        assertFalse(recovery.equals(null));
        assertNotSame(new RecoveryPhase(), recovery);
        assertEquals(new RecoveryPhase(), recovery);
    }

    @Test
    public void testStarsPhaseConstructor() {
        assertEquals(stars, stars);
        assertNotEquals(stars, new Object());
        assertFalse(stars.equals(null));
        assertNotSame(new StarsPhase(), stars);
        assertEquals(new StarsPhase(), stars);
    }

    @Test
    public void testFightPhaseConstructor(){
        assertEquals(fight, fight);
        assertNotEquals(fight, new Object());
        assertFalse(fight.equals(null));
        assertNotSame(new FightPhase(), fight);
        assertEquals(new FightPhase(), fight);
    }

    @Test
    public void testFightDecisionPhaseConstructor() {
        assertEquals(fightDecision, fightDecision);
        assertNotEquals(fightDecision, new Object());
        assertFalse(fightDecision.equals(null));
        assertNotSame(new FightDecisionPhase(3), fightDecision);
        assertEquals(new FightDecisionPhase(3), fightDecision);
    }

    @Test
    public void testHomeDecisionPhaseConstructor() {
        assertEquals(homeDecision, homeDecision);
        assertNotEquals(homeDecision, new Object());
        assertFalse(homeDecision.equals(null));
        assertNotSame(new HomeDecisionPhase(3), homeDecision);
        assertEquals(new HomeDecisionPhase(3), homeDecision);
    }

    @Test
    public void testPanelDecisionPhaseConstructor() {
        assertEquals(panelDecision, panelDecision);
        assertNotEquals(panelDecision, new Object());
        assertFalse(panelDecision.equals(null));
        assertNotSame(new PanelDecisionPhase(3), panelDecision);
        assertEquals(new PanelDecisionPhase(3), panelDecision);
    }

    @Test
    public void testSetTurn(){
        assertEquals(turn, first.getTurn());
        assertEquals(turn, landAtPanel.getTurn());
        assertEquals(turn, move.getTurn());
        assertEquals(turn, recovery.getTurn());
        assertEquals(turn, stars.getTurn());
        assertEquals(turn, fight.getTurn());
        assertEquals(turn, fightDecision.getTurn());
        assertEquals(turn, homeDecision.getTurn());
        assertEquals(turn, panelDecision.getTurn());
    }

    @Test
    public void testFirstPhaseAction(){
        player.setCurrentHP(3);
        first.action();
        assertEquals(new StarsPhase(), turn.getPhase());
        player.setCurrentHP(0);
        first.action();
        assertEquals(new RecoveryPhase(), turn.getPhase());
    }

    @Test
    public void testLandAtPanelPhaseAction(){
        IPanel panel= new Panel(1);
        player.changePanel(panel);
        landAtPanel.action();
        //the panel being activated is tested specifically in the panels
        //and I don't know if there is an assert to check that a specific method was called
        //and the turn ends, so the next phase doesn't exist
    }

    @Test
    public void testMovePhasesAction(){
        //normal movement
        IPanel panel= new Panel(0);
        IPanel panel1 = new Panel(1);
        panel.addNextPanel(panel1);
        panel1.addNextPanel(panel);
        player.changePanel(panel);
        move.action();
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(panel, player.getCurrentPanel());

        //2 next panels
        move = new MovePhase(1);
        turn.setPhase(move);
        IPanel panel2= new Panel(2);
        IPanel panel3 = new Panel(3);
        panel.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        move.action();
        assertEquals(new PanelDecisionPhase(1), turn.getPhase());
        player.setPanelDecision(panel2);
        turn.getPhase().action();
        assertEquals(panel2, player.getCurrentPanel());
        turn.getPhase().action();
        assertEquals(null,player.getPanelDecision());
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(panel2, player.getCurrentPanel());

        //Player in a next panel, decision is IGNORE
        move= new MovePhase(2);
        turn.setPhase(move);
        IPanel panel4 = new Panel(4);
        panel3.addNextPanel(panel4);
        Player player1 = new Player("kai", 2, 1, 1, 0);
        panel3.addPlayer(player1);
        move.action();
        assertEquals(new FightDecisionPhase(1), turn.getPhase());
        player.setFightDecision(FightDecision.IGNORE);
        turn.getPhase().action();
        assertEquals(null,player.getFightDecision());
        assertEquals(panel3, player.getCurrentPanel());
        assertEquals(new MovePhase(1), turn.getPhase());
        turn.getPhase().action();
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(panel4, player.getCurrentPanel());

        //player in a next panel, decision is ENGAGE
        move= new MovePhase(2);
        turn.setPhase(move);
        player.changePanel(panel2);
        move.action();
        assertEquals(new FightDecisionPhase(1), turn.getPhase());
        player.setFightDecision(FightDecision.ENGAGE);
        turn.getPhase().action();
        assertEquals(null,player.getFightDecision());
        assertEquals(panel3, player.getCurrentPanel());

        //Player comes across home panel, chooses to stop
        move = new MovePhase(2);
        turn.setPhase(move);
        IPanel panel5 = new Panel(5);
        HomePanel home = new HomePanel(6);
        player.setHome(home);
        IPanel panel6 = new Panel(6);
        panel5.addNextPanel(home);
        home.addNextPanel(panel6);
        panel5.addPlayer(player);
        move.action();
        assertEquals(new HomeDecisionPhase(1), turn.getPhase());
        assertEquals(home, player.getCurrentPanel());
        player.setHomeDecision(HomeDecision.STOP);
        turn.getPhase().action();
        assertEquals(home, player.getCurrentPanel());
        assertEquals(null,player.getHomeDecision());
        assertEquals(new LandAtPanelPhase(), turn.getPhase());

        //player comes across home panel, decides to keep moving
        panel5.addPlayer(player);
        move.action();
        assertEquals(home, player.getCurrentPanel());
        assertEquals(new HomeDecisionPhase(1), turn.getPhase());
        player.setHomeDecision(HomeDecision.KEEPMOVING);
        turn.getPhase().action();
        assertEquals(null,player.getHomeDecision());
        assertEquals(new MovePhase(1), turn.getPhase());
        assertEquals(home, player.getCurrentPanel());
        turn.getPhase().action();
        assertEquals(new LandAtPanelPhase(), turn.getPhase());
        assertEquals(panel6, player.getCurrentPanel());
    }

    @Test
    public void testRecoveryPhaseAction(){
        player.setRequiredRoll(4);
        recovery.action();
        assertTrue((turn.getPhase().equals(new StarsPhase()) && player.getCurrentHP()==player.getMaxHP()) || player.getRequiredRoll()==3);
    }

    @RepeatedTest(100)
    public void testStarsPhaseAction(){
        assertEquals(0, player.getStars());
        long random = new Random().nextLong();
        final var testRandom = new Random(random);
        player.setSeed(random);
        stars.action();
        final int roll = testRandom.nextInt(6) + 1;
        assertEquals(turn.getChapter()/5 + 1, player.getStars());
        assertEquals(new MovePhase(roll), turn.getPhase());
    }

    @RepeatedTest(100)
    public void testFightPhaseAction(){
        assertFalse(player.isDown());
        IPanel panel = new Panel(1);
        Player other=new Player("marc", 5, 1, 0, 0);
        panel.addPlayer(player);
        panel.addPlayer(other);
        player.setBattleDecision(BattleDecision.DEFEND);
        other.setBattleDecision(BattleDecision.EVADE);
        fight.action();
        assertTrue(player.isDown() || turn.getPhase().equals(new LandAtPanelPhase()));
    }
}