package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.NormaGoal;

import com.github.cc3002.citricjuice.model.board.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
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
  private final static String BOSS_NAME = "Boss";
  private final static String WILD_NAME = "Wild";
  private Player suguri;
  private BossUnit testBossUnit;
  private WildUnit testWildUnit;


  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    testBossUnit = new BossUnit(BOSS_NAME, 7, 2,1,-1);
    testWildUnit = new WildUnit(WILD_NAME, 5, 1,0,-1);
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
    final var expectedBoss = new BossUnit(BOSS_NAME,7,2,1,-1);
    assertEquals(testBossUnit, testBossUnit);
    assertEquals(expectedBoss, testBossUnit);
    assertNotSame(expectedBoss, testBossUnit);
  }

  @Test
  public void WildConstructorTest(){
    final var expectedWild = new WildUnit(WILD_NAME,5,1,0,-1);
    assertEquals(testWildUnit, testWildUnit);
    assertEquals(expectedWild, testWildUnit);
    assertNotSame(expectedWild, testWildUnit);
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    assertNotEquals(suguri, o);
    assertEquals(suguri, suguri);
    assertNotEquals(testBossUnit, testWildUnit);
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    assertEquals(expectedSuguri, suguri);
    final var unexpected=new WildUnit(BOSS_NAME, 7,2,1,-1);
    assertNotEquals(unexpected, testBossUnit);
  }

  @Test
  public void getNameTest(){
    assertEquals(PLAYER_NAME, suguri.getName());
  }

  @Test
  public void getStatsTest(){
    assertEquals(4, suguri.getMaxHP());
    assertEquals(1, suguri.getAtk());
    assertEquals(-1, suguri.getDef());
    assertEquals(2, suguri.getEvd());
    assertEquals(4, suguri.getCurrentHP());
  }

  @Test
  public void PlayerSetStatsTest(){
    assertEquals(1, suguri.getAtk());
    suguri.setAtk(2);
    assertEquals(2, suguri.getAtk());
    assertEquals(-1, suguri.getDef());
    suguri.setDef(0);
    assertEquals(0, suguri.getDef());
    assertEquals(2, suguri.getEvd());
    suguri.setEvd(1);
    assertEquals(1, suguri.getEvd());
  }

  @Test
  public void StarsTest() {
    assertEquals(0, suguri.getStars());
    suguri.increaseStarsBy(10);
    assertEquals(10, suguri.getStars());
    suguri.reduceStarsBy(4);
    assertEquals(6, suguri.getStars());
    suguri.reduceStarsBy(8);
    assertEquals(0, suguri.getStars());
  }

  @Test
  public void WinsTest(){
    assertEquals(0, suguri.getWins());
    suguri.increaseWinsBy(2);
    assertEquals(2, suguri.getWins());
  }

  @Test
  public void HitPointsTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    suguri.setCurrentHP(2);
    assertEquals(2, suguri.getCurrentHP());
    assertFalse(suguri.isDown());
    suguri.setCurrentHP(-1);
    assertEquals(0, suguri.getCurrentHP());
    assertTrue(suguri.isDown());
    suguri.setCurrentHP(5);
    assertEquals(4, suguri.getCurrentHP());
    assertFalse(suguri.isDown());
  }

  @Test
  public void normaTest() {
    assertEquals(NormaGoal.STARS, suguri.getNormaGoal());
    assertEquals(1, suguri.getNormaLevel());
    suguri.increaseStarsBy(12);
    assertTrue(suguri.checkNorma());
    suguri.normaClear();
    assertEquals(2, suguri.getNormaLevel());
    suguri.setNormaGoal(NormaGoal.WINS);
    assertEquals(NormaGoal.WINS, suguri.getNormaGoal());
    suguri.increaseWinsBy(3);
    assertTrue(suguri.checkNorma());
    suguri.normaClear();
    assertEquals(3, suguri.getNormaLevel());
  }

  @Test
  public void PlayerCopyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(suguri, actualSuguri);
  }

  @Test
  public void BossCopyTest() {
    final var expectedBoss = new BossUnit(BOSS_NAME,7,2,1,-1);
    final var actualBoss = testBossUnit.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedBoss, actualBoss);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(testBossUnit, actualBoss);
  }

  @Test
  public void WildCopyTest() {
    final var expectedWild = new WildUnit(WILD_NAME,5,1,0,-1);
    final var actualWild = testWildUnit.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedWild, actualWild);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(testWildUnit, actualWild);
  }

  @Test
  public void PlayerPanelTest(){
    Panel testNeutralPanel = new Panel(0);
    DropPanel testDropPanel = new DropPanel(1);
    final var expectedSuguri = suguri.copy();
    suguri.changePanel(testNeutralPanel);
    assertEquals(testNeutralPanel, suguri.getCurrentPanel());
    assertEquals(expectedSuguri, suguri);
    suguri.changePanel(testDropPanel);
    assertEquals(testDropPanel, suguri.getCurrentPanel());
  }

  @Test
  public void PlayerHomeTest(){
    HomePanel expectedHome = new HomePanel(0);
    suguri.setHome(expectedHome);
    assertEquals(expectedHome, suguri.getHome());
  }

  @Test
  public void WildDefeatedByWildTest(){
    WildUnit newWild = new WildUnit("defeated wild", 3, 1, 0, -1);
    newWild.increaseStarsBy(80);
    newWild.defeatedBy(testWildUnit);
    assertEquals(40, testWildUnit.getStars());
    assertEquals(40, newWild.getStars());
    assertEquals(1, testWildUnit.getWins());
  }

  @Test
  public void WildDefeatedByBossTest(){
    WildUnit newWild = new WildUnit("defeated wild", 3, 1, 0, -1);
    newWild.increaseStarsBy(80);
    newWild.defeatedBy(testBossUnit);
    assertEquals(40, testBossUnit.getStars());
    assertEquals(40, newWild.getStars());
    assertEquals(1, testBossUnit.getWins());
  }

  @Test
  public void WildDefeatedByPlayerTest(){
    WildUnit newWild = new WildUnit("defeated wild", 3, 1, 0, -1);
    newWild.increaseStarsBy(80);
    newWild.defeatedBy(suguri);
    assertEquals(80, suguri.getStars());
    assertEquals(0, newWild.getStars());
    assertEquals(1, suguri.getWins());
  }

  @Test
  public void BossDefeatedByWildTest(){
    BossUnit newBoss = new BossUnit("defeated boss", 5, 1, 2, 1);
    newBoss.increaseStarsBy(80);
    newBoss.defeatedBy(testWildUnit);
    assertEquals(40, testWildUnit.getStars());
    assertEquals(40, newBoss.getStars());
    assertEquals(3, testWildUnit.getWins());
  }

  @Test
  public void BossDefeatedByBossTest(){
    BossUnit newBoss = new BossUnit("defeated boss", 5, 1, 2, 1);
    newBoss.increaseStarsBy(80);
    newBoss.defeatedBy(testBossUnit);
    assertEquals(40, testBossUnit.getStars());
    assertEquals(40, newBoss.getStars());
    assertEquals(3, testBossUnit.getWins());
  }

  @Test
  public void BossDefeatedByPlayerTest(){
    BossUnit newBoss = new BossUnit("defeated boss", 5, 1, 2, 1);
    newBoss.increaseStarsBy(80);
    newBoss.defeatedBy(suguri);
    assertEquals(80, suguri.getStars());
    assertEquals(0, newBoss.getStars());
    assertEquals(3, suguri.getWins());
  }

  @Test
  public void PlayerDefeatedByWildTest(){
    Player newPlayer = new Player("defeated player", 5, 1, 0, 1);
    newPlayer.increaseStarsBy(80);
    newPlayer.defeatedBy(testWildUnit);
    assertEquals(40, testWildUnit.getStars());
    assertEquals(40, newPlayer.getStars());
    assertEquals(2, testWildUnit.getWins());
  }

  @Test
  public void PlayerDefeatedByBossTest(){
    Player newPlayer = new Player("defeated player", 5, 1, 0, 1);
    newPlayer.increaseStarsBy(80);
    newPlayer.defeatedBy(testBossUnit);
    assertEquals(40, testBossUnit.getStars());
    assertEquals(40, newPlayer.getStars());
    assertEquals(2, testBossUnit.getWins());
  }

  @Test
  public void PlayerDefeatedByPlayerTest(){
    Player newPlayer = new Player("defeated player", 5, 1, 0, 1);
    newPlayer.increaseStarsBy(80);
    newPlayer.defeatedBy(suguri);
    assertEquals(40, suguri.getStars());
    assertEquals(40, newPlayer.getStars());
    assertEquals(2, suguri.getWins());
  }

  @Test
  public void testEnemyPanel(){
    BossPanel bossPanel = new BossPanel(1);
    EncounterPanel encounterPanel = new EncounterPanel(2);
    testBossUnit.setPanel(bossPanel);
    testWildUnit.setPanel(encounterPanel);
    assertEquals(bossPanel, testBossUnit.getPanel());
    assertEquals(encounterPanel, testWildUnit.getPanel());
  }

  @Test
  public void testMovePlayer(){
    List<IPanel> panels = List.of(new Panel(0), new HomePanel(1), new Panel(2), new Panel(3), new Panel(4));
    panels.get(0).addNextPanel(panels.get(1));
    panels.get(1).addNextPanel(panels.get(2));
    panels.get(2).addNextPanel(panels.get(3));
    suguri.changePanel(panels.get(0));
    panels.get(0).addPlayer(suguri);
    int n= suguri.move(2); //suguri moves normally
    assertEquals(panels.get(2), suguri.getCurrentPanel());
    assertEquals(0,n);
    suguri.setHome((HomePanel) panels.get(1));
    suguri.changePanel(panels.get(0));
    n= suguri.move(2); //suguri moves but her home panel is in the way
    assertEquals(panels.get(1), suguri.getCurrentPanel());
    assertEquals(1, n);
    n = suguri.move(1); //suguri moves normally
    assertEquals(panels.get(2), suguri.getCurrentPanel());
    assertEquals(0, n);
    panels.get(2).addNextPanel(panels.get(4));
    n = suguri.move(2); //suguri moves but there are 2 panel options
    assertEquals(panels.get(2), suguri.getCurrentPanel());
    assertEquals(2, n);
    suguri.changePanel(panels.get(0));
    panels.get(0).addPlayer(suguri);
    panels.get(1).addPlayer(new Player("sugurint", 4, 1, 3, 0));
    n= suguri.move(2); //suguri moves but there is another player in the next panel
    assertEquals(panels.get(1), suguri.getCurrentPanel());
    assertEquals(1, n);
  }

  @Test
  public void testRequiredRoll(){
    suguri.dies();
    assertEquals(6, suguri.getRequiredRoll());
    suguri.setRequiredRoll(3);
    assertEquals(3, suguri.getRequiredRoll());
  }

  @Test
  public void testWildDies(){
    EncounterPanel panel = new EncounterPanel(1);
    panel.setWild(testWildUnit);
    testWildUnit.dies();
    WildUnit expected = testWildUnit.copy();
    assertEquals(expected, panel.getUnit());
    assertEquals(testWildUnit.getMaxHP(), panel.getUnit().getCurrentHP());
  }

  @Test
  public void testBossDies(){
    BossPanel panel = new BossPanel(1);
    panel.setBoss(testBossUnit);
    testBossUnit.dies();
    BossUnit expected = testBossUnit.copy();
    assertEquals(expected, panel.getUnit());
    assertEquals(expected.getMaxHP(), panel.getUnit().getCurrentHP());
  }

  @Test
  public void testPlayerDecisions(){
    suguri.setFightDecision(FightDecision.ENGAGE);
    assertEquals(FightDecision.ENGAGE,suguri.getFightDecision());
    suguri.setFightDecision(FightDecision.IGNORE);
    assertEquals(FightDecision.IGNORE, suguri.getFightDecision());
    suguri.setBattleDecision(BattleDecision.DEFEND);
    assertEquals(BattleDecision.DEFEND,suguri.getBattleDecision());
    suguri.setBattleDecision(BattleDecision.EVADE);
    assertEquals(BattleDecision.EVADE, suguri.getBattleDecision());
    IPanel expected = new Panel(3);
    suguri.setPanelDecision(expected);
    assertEquals(expected, suguri.getPanelDecision());
    suguri.setHomeDecision(HomeDecision.STOP);
    assertEquals(HomeDecision.STOP, suguri.getHomeDecision());
    suguri.setHomeDecision(HomeDecision.KEEPMOVING);
    assertEquals(HomeDecision.KEEPMOVING, suguri.getHomeDecision());
  }


  // region : consistency tests

  @RepeatedTest(50)
  public void testEnemyDecision(){
    BattleDecision enemyDecision1 = testBossUnit.getBattleDecision();
    BattleDecision enemyDecision2 = testWildUnit.getBattleDecision();
    assertTrue(enemyDecision1==BattleDecision.DEFEND || enemyDecision1==BattleDecision.EVADE);
    assertTrue(enemyDecision2==BattleDecision.DEFEND || enemyDecision2==BattleDecision.EVADE);
  }

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

  @RepeatedTest(100)
  public void AttackTest() {
    long testSeed = new Random().nextLong();
    testBossUnit.setSeed(testSeed);
    final int bossAtk = suguri.attackedBy(testBossUnit);
    assertTrue(bossAtk>=3 && bossAtk <= 8, bossAtk + "is not in [3, 8]" + System.lineSeparator()
            + "Boss attacking test failed with random seed: "+ testSeed);
    long testSeed1 = new Random().nextLong();
    testWildUnit.setSeed(testSeed1);
    final int wildAtk = testBossUnit.attackedBy(testWildUnit);
    assertTrue(wildAtk>=2 && wildAtk <=7, wildAtk + "is not in [2,7]" + System.lineSeparator()+
            "Wild attacking test failed with random seed: "+ testSeed1);
    long testSeed2 = new Random().nextLong();
    suguri.setSeed(testSeed2);
    final int playerAtk = testWildUnit.attackedBy(suguri);
    assertTrue(playerAtk >= 2 && playerAtk <= 7, playerAtk + "is not in [2,7]" + System.lineSeparator()+
            "Suguri attacking test failed with random seed: "+testSeed2);
  }

  @RepeatedTest(100)
  public void defendsTest(){
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final long testSeed1 = new Random().nextLong();
    testBossUnit.setSeed(testSeed1);
    int attack = suguri.attackedBy(testBossUnit);
    suguri.defendsFrom(attack);
    int a = testBossUnit.getAtk();
    int suguri_damage= suguri.getMaxHP() - suguri.getCurrentHP();
    int def = suguri.getDef();
    assertTrue((suguri.isDown() || suguri_damage==1 || (suguri_damage <= attack-def && suguri_damage >= 1+def)),
            suguri_damage + " is not in [1,"+ (5+a) +"]. defends" +
                    "test failed with random seeds: "+testSeed + ", " + testSeed1);
  }

  @RepeatedTest(100)
  public void evadesTest(){
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final long testSeed1 = new Random().nextLong();
    testWildUnit.setSeed(testSeed1);
    int attack = testWildUnit.attackedBy(suguri);
    testWildUnit.evades(attack);
    int testWild_damage = testWildUnit.getMaxHP() - testWildUnit.getCurrentHP();
    assertTrue((testWild_damage==0 || testWild_damage==attack || testWildUnit.isDown()), testWild_damage +
            "is not in [0," + attack+6 + "]. evades test failed with random seeds: "+ testSeed + ", "+ testSeed1);
  }

  @RepeatedTest(200)
  public void testBattleRound(){
    suguri.battleRound(testBossUnit, BattleDecision.EVADE);
    int dmg = testBossUnit.getMaxHP() - testBossUnit.getCurrentHP();
    assertTrue(testBossUnit.isDown() || dmg ==0 || (1+suguri.getAtk()<=dmg && dmg<= 6+suguri.getAtk()));
    testWildUnit.battleRound(suguri, BattleDecision.DEFEND);
    dmg = suguri.getMaxHP() - suguri.getCurrentHP();
    int def = suguri.getDef();
    int atk = testWildUnit.getAtk();
    assertTrue(suguri.isDown() || dmg==1 || (1<= dmg+def && dmg+def <= 6+atk));
  }

  // endregion

  //region: activate panel tests

  @Test
  public void activateNeutralPanelTest() {
    Panel testNeutralPanel = new Panel(0);
    suguri.changePanel(testNeutralPanel);
    final var expectedSuguri = suguri.copy();
    suguri.activatePanel();
    assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void homePanelTest() {
    HomePanel testHomePanel = new HomePanel(0);
    suguri.changePanel(testHomePanel);
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    suguri.activatePanel();
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    suguri.setCurrentHP(1);
    suguri.activatePanel();
    assertEquals(2, suguri.getCurrentHP());
  }

  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    long testSeed = new Random().nextLong();
    BonusPanel testBonusPanel = new BonusPanel(0);
    suguri.changePanel(testBonusPanel);
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      suguri.activatePanel();
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),"Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    long testSeed = new Random().nextLong();
    DropPanel testDropPanel= new DropPanel(2);
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    suguri.changePanel(testDropPanel);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      suguri.activatePanel();
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),"Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
}
