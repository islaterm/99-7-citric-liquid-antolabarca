package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.IEnemy;
import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class PanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private HomePanel testHomePanel;
  private Panel testNeutralPanel;
  private BonusPanel testBonusPanel;
  private DropPanel testDropPanel;
  private EncounterPanel testEncounterPanel;
  private BossPanel testBossPanel;
  private Player suguri;
  private long testSeed;
  protected EnemyTestHandler enemyTestHandler = new EnemyTestHandler(this);
  protected IEnemy attacker;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel(1);
    testBossPanel = new BossPanel(1);
    testDropPanel = new DropPanel(1);
    testEncounterPanel = new EncounterPanel(1);
    testHomePanel = new HomePanel(1);
    testNeutralPanel = new Panel(1);
    testSeed = new Random().nextLong();
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  /**
   * Sets the latest attacker
   */
  public void setAttacker(IEnemy attacker){this.attacker=attacker;}

  @Test
  public void equalsTest() {
    assertNotEquals(testNeutralPanel, new Object());
    assertEquals(testNeutralPanel, testNeutralPanel);
    assertEquals(testNeutralPanel, new Panel(1));
    assertNotEquals(testNeutralPanel, testDropPanel);
    assertNotEquals(testDropPanel, testNeutralPanel);
    assertNotEquals(testBonusPanel,testDropPanel);
    assertNotEquals(new Panel(0), new DropPanel(0));
    assertNotEquals(new Panel(2),new DropPanel(2));
    assertNotEquals(new DropPanel(1), new Panel(1));
    assertNotEquals(new DropPanel(0), new Panel(0));
    assertNotEquals(new BonusPanel(0), new DropPanel(0));
  }

  @Test
  public void PanelConstructorTest() {
    assertEquals(testNeutralPanel, testNeutralPanel);
    assertNotEquals(testNeutralPanel, new Object());
    assertFalse(testNeutralPanel.equals(null));
    assertNotSame(new Panel(1), testNeutralPanel);
    assertEquals(new Panel(1), testNeutralPanel);
  }

  @Test
  public void BonusPanelConstructorTest() {
    assertEquals(testBonusPanel, testBonusPanel);
    assertNotEquals(testBonusPanel, new Object());
    assertFalse(testBonusPanel.equals(null));
    assertNotSame(new BonusPanel(1), testBonusPanel);
    assertEquals(new BonusPanel(1), testBonusPanel);
  }

  @Test
  public void BossPanelConstructorTest() {
    assertEquals(testBossPanel, testBossPanel);
    assertNotEquals(testBossPanel, new Object());
    assertFalse(testBossPanel.equals(null));
    assertNotSame(new BossPanel(1), testBossPanel);
    assertEquals(new BossPanel(1), testBossPanel);
  }

  @Test
  public void DropPanelConstructorTest() {
    assertEquals(testDropPanel, testDropPanel);
    assertNotEquals(testDropPanel, new Object());
    assertFalse(testDropPanel.equals(null));
    assertNotSame(new DropPanel(1), testDropPanel);
    assertEquals(new DropPanel(1), testDropPanel);
  }

  @Test
  public void EncounterPanelConstructorTest() {
    assertEquals(testEncounterPanel, testEncounterPanel);
    assertNotEquals(testEncounterPanel, new Object());
    assertFalse(testEncounterPanel.equals(null));
    assertNotSame(new EncounterPanel(1), testEncounterPanel);
    assertEquals(new EncounterPanel(1), testEncounterPanel);
  }

  @Test
  public void HomePanelConstructorTest() {
    assertEquals(testHomePanel, testHomePanel);
    assertNotEquals(testHomePanel, new Object());
    assertFalse(testHomePanel.equals(null));
    assertNotSame(new HomePanel(1), testHomePanel);
    assertEquals(new HomePanel(1), testHomePanel);
  }


  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new Panel(2);
    final var expectedPanel2 = new Panel(3);

    testNeutralPanel.addNextPanel(testNeutralPanel);
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    assertTrue(testNeutralPanel.getNextPanelsList().isEmpty());

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());
    assertEquals(1, testNeutralPanel.getNextPanelsList().size());
    assertTrue(testNeutralPanel.getNextPanels().contains(expectedPanel1));
    assertTrue(testNeutralPanel.getNextPanelsList().contains(expectedPanel1));

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());
    assertEquals(2, testNeutralPanel.getNextPanelsList().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());
    assertEquals(2, testNeutralPanel.getNextPanelsList().size());

    assertEquals(Set.of(expectedPanel1, expectedPanel2), testNeutralPanel.getNextPanels());
    assertTrue(testNeutralPanel.getNextPanelsList().contains(expectedPanel1) && testNeutralPanel.getNextPanelsList().contains(expectedPanel2));
  }

  @Test
  public void playersTest() {
    Player sugurint = new Player("not suguri", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    assertEquals(0, testNeutralPanel.getPlayers().size());
    assertEquals(0, testNeutralPanel.getPlayersList().size());
    testNeutralPanel.addPlayer(suguri);
    assertEquals(1, testNeutralPanel.getPlayers().size());
    assertEquals(1, testNeutralPanel.getPlayersList().size());
    assertTrue(testNeutralPanel.getPlayers().contains(suguri));
    assertTrue(testNeutralPanel.getPlayersList().contains(suguri));
    testNeutralPanel.addPlayer(sugurint);
    assertEquals(2, testNeutralPanel.getPlayers().size());
    assertEquals(2, testNeutralPanel.getPlayersList().size());
    testNeutralPanel.addPlayer(sugurint);
    assertEquals(2, testNeutralPanel.getPlayers().size());
    assertEquals(2, testNeutralPanel.getPlayersList().size());
    assertEquals(Set.of(suguri, sugurint), testNeutralPanel.getPlayers());
    assertTrue(testNeutralPanel.getPlayersList().contains(suguri));
    assertTrue(testNeutralPanel.getPlayersList().contains(sugurint));

    testNeutralPanel.removePlayer(suguri);
    assertEquals(1, testNeutralPanel.getPlayers().size());
    assertEquals(1, testNeutralPanel.getPlayersList().size());
    assertFalse(testNeutralPanel.getPlayers().contains(suguri));
    assertFalse(testNeutralPanel.getPlayersList().contains(suguri));
    assertTrue(testNeutralPanel.getPlayers().contains(sugurint));
    assertTrue(testNeutralPanel.getPlayersList().contains(sugurint));
    testNeutralPanel.removePlayer(suguri);
    assertEquals(1, testNeutralPanel.getPlayers().size());
    assertEquals(1, testNeutralPanel.getPlayersList().size());
    assertFalse(testNeutralPanel.getPlayers().contains(suguri));
    assertFalse(testNeutralPanel.getPlayersList().contains(suguri));
    assertTrue(testNeutralPanel.getPlayers().contains(sugurint));
    assertTrue(testNeutralPanel.getPlayersList().contains(sugurint));
    testNeutralPanel.removePlayer(sugurint);
    assertEquals(0, testNeutralPanel.getPlayers().size());
    assertEquals(0, testNeutralPanel.getPlayersList().size());
  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.activatedBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    suguri.setCurrentHP(1);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getCurrentHP());
  }

  @Test
  public void encounterPanelTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    testEncounterPanel.addEncounterPanelListener(enemyTestHandler);
    testEncounterPanel.activatedBy(suguri);
    assertEquals(testEncounterPanel.getUnit(), attacker);
  }

  @Test
  public void bossPanelTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    testBossPanel.addBossPanelListener(enemyTestHandler);
    testBossPanel.activatedBy(suguri);
    assertEquals(testBossPanel.getUnit(), attacker);
  }

  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),"Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatedBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),"Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
  // endregion
}