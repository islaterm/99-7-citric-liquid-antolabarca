package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.units.Boss;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.Wild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class UnitsTest {
  private final static String PLAYER_NAME = "Suguri";
  private Player suguri;
  private Boss testBoss;
  private Wild testWild;


  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    testBoss = new Boss(7, 2,1,-1);
    testWild = new Wild(5, 1,0,-1);
  }

  @Test
  public void PlayerConstructorTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    assertEquals(suguri,suguri);
    assertEquals(expectedSuguri, suguri);
    assertNotSame(expectedSuguri, suguri);
  }

  @Test
  public void BossConstructorTest(){
    final var expectedBoss = new Boss(7,2,1,-1);
    assertEquals(testBoss,testBoss);
    assertEquals(expectedBoss, testBoss);
    assertNotSame(expectedBoss, testBoss);
  }

  @Test
  public void WildConstructorTest(){
    final var expectedWild = new Wild(5,1,0,-1);
    assertEquals(testWild, testWild);
    assertEquals(expectedWild, testWild);
    assertNotSame(expectedWild, testWild);
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    assertNotEquals(suguri, o);
    assertEquals(suguri, suguri);
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void hitPointsTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    suguri.setCurrentHP(2);
    assertEquals(2, suguri.getCurrentHP());
    suguri.setCurrentHP(-1);
    assertEquals(0, suguri.getCurrentHP());
    suguri.setCurrentHP(5);
    assertEquals(4, suguri.getCurrentHP());
  }

  @Test
  public void normaClearTest() {
    suguri.normaClear();
    assertEquals(2, suguri.getNormaLevel());
  }

  @Test
  public void copyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(expectedSuguri, actualSuguri);
  }

  // region : consistency tests
  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHP() + 1)
                       - 2 * suguri.getMaxHP();
    suguri.setCurrentHP(testHP);
    assertTrue(0 <= suguri.getCurrentHP()
               && suguri.getCurrentHP() <= suguri.getMaxHP(),
               suguri.getCurrentHP() + "is not a valid HP value"
               + System.lineSeparator() + "Test failed with random seed: "
               + testSeed);
  }

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNormaLevel(),
                 "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    assertTrue(roll >= 1 && roll <= 6,
               roll + "is not in [1, 6]" + System.lineSeparator()
               + "Test failed with random seed: " + testSeed);
  }
  // endregion
}