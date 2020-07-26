package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricliquid.controller.*;
import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class HandlerTests {
    GameController controller;
    Player player;
    Panel panel;
    BossUnit boss;
    BossBattleHandler bossBattleHandler;
    BossDefeatHandler bossDefeatHandler;
    Norma4Handler norma4Handler;
    WildBattleHandler wildBattleHandler;
    WinHandler winHandler;

    @BeforeEach
    public void setUp(){
        controller = new GameController();
        panel = controller.createNeutralPanel(1);
        player = controller.createPlayer("suguri", 4, 2, 1, -1, panel);
        boss = new BossUnit("flying castle", 6, 2, 1, 0);
        bossBattleHandler = new BossBattleHandler(controller);
        bossDefeatHandler = new BossDefeatHandler(controller);
        norma4Handler = new Norma4Handler(controller);
        wildBattleHandler = new WildBattleHandler(controller);
        winHandler = new WinHandler(controller);
    }

    @Test
    public void bossBattleHandlerConstructor(){
        assertEquals(bossBattleHandler, bossBattleHandler);
        assertNotEquals(bossBattleHandler, new Object());
        assertFalse(bossBattleHandler.equals(null));
        assertNotSame(new BossBattleHandler(controller), bossBattleHandler);
        assertEquals(new BossBattleHandler(controller), bossBattleHandler);
    }

    @Test
    public void bossDefeatHandlerConstructor(){
        assertEquals(bossDefeatHandler, bossDefeatHandler);
        assertNotEquals(bossDefeatHandler, new Object());
        assertFalse(bossDefeatHandler.equals(null));
        assertNotSame(new BossDefeatHandler(controller), bossDefeatHandler);
        assertEquals(new BossDefeatHandler(controller), bossDefeatHandler);
    }

    @Test
    public void norma4HandlerConstructor(){
        assertEquals(norma4Handler, norma4Handler);
        assertNotEquals(norma4Handler, new Object());
        assertFalse(norma4Handler.equals(null));
        assertNotSame(new Norma4Handler(controller), norma4Handler);
        assertEquals(new Norma4Handler(controller), norma4Handler);
    }

    @Test
    public void wildBattleHandlerConstructor(){
        assertEquals(wildBattleHandler, wildBattleHandler);
        assertNotEquals(wildBattleHandler, new Object());
        assertFalse(wildBattleHandler.equals(null));
        assertNotSame(new WildBattleHandler(controller), wildBattleHandler);
        assertEquals(new WildBattleHandler(controller), wildBattleHandler);
    }

    @Test
    public void winHandlerConstructor(){
        assertEquals(winHandler, winHandler);
        assertNotEquals(winHandler, new Object());
        assertFalse(winHandler.equals(null));
        assertNotSame(new WinHandler(controller), winHandler);
        assertEquals(new WinHandler(controller), winHandler);
    }

    //I'm not really sure how to test the battle property change :(
    @Test
    public void bossDefeatTest(){
        bossDefeatHandler.propertyChange(new PropertyChangeEvent(boss, "boss defeat", null, boss));
        assertTrue(controller.isBossDefeated());
    }


    @Test
    public void norma4Test(){
        norma4Handler.propertyChange(new PropertyChangeEvent(player, "norma 4", null, player));
        assertTrue(controller.isNorma4());
    }

    @Test
    public void winTest(){
        winHandler.propertyChange(new PropertyChangeEvent(player, "player wins", null, player));
        assertEquals(player, controller.getWinner());
        assertTrue(controller.isGameWon());
    }

}
