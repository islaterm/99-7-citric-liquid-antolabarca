package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricliquid.gui.choicehandlers.FightChoiceHandler;
import com.github.cc3002.citricliquid.gui.choicehandlers.HomeChoiceHandler;
import javafx.util.Pair;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

import static com.github.cc3002.citricjuice.model.NormaGoal.STARS;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class Player extends AbstractUnit {
  private int normaLevel;
  private int requiredRoll;
  private NormaGoal normaGoal;
  private IPanel currentPanel;
  private HomePanel home;
  private PropertyChangeSupport playerWins = new PropertyChangeSupport(this);
  private PropertyChangeSupport playerNorma4 = new PropertyChangeSupport(this);
  private PropertyChangeSupport playerMoves = new PropertyChangeSupport(this);
  private PropertyChangeSupport panelDecisionProperty = new PropertyChangeSupport(this);
  private PropertyChangeSupport homePanelProperty = new PropertyChangeSupport(this);
  private PropertyChangeSupport fightChoiceProperty = new PropertyChangeSupport(this);
  private BattleDecision battleDecision = null;
  private FightDecision fightDecision = null;
  private IPanel panelDecision = null;
  private HomeDecision homeDecision = null;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def, final int evd) {
    super(name, hp, atk, def, evd);
    this.normaLevel = 1;
    this.normaGoal = STARS;
  }

  /**
   * Checks if an object equals this one
   * @param o the object
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    final Player player = (Player) o;
    return getMaxHP() == player.getMaxHP() &&
            getAtk() == player.getAtk() &&
            getDef() == player.getDef() &&
            getEvd() == player.getEvd() &&
            getNormaLevel() == player.getNormaLevel() &&
            getStars() == player.getStars() &&
            getCurrentHP() == player.getCurrentHP() &&
            getName().equals(player.getName());
  }


  /**
   * Returns the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Returns the player's norma goal
   */
  public NormaGoal getNormaGoal(){ return normaGoal; }

  /**
   * Adds a listener to this players win property change
   * @param listener the listener
   */
  public void addPlayerWinsListener(PropertyChangeListener listener) {
    playerWins.addPropertyChangeListener(listener);
  }

  /**
   * Adds a listener to this players norma4 property change
   * @param listener the listener
   */
  public void addPlayerNorma4Listener(PropertyChangeListener listener){
    playerNorma4.addPropertyChangeListener(listener);
  }

  /**
   * Returns the panel where the player is
   */
  public IPanel getCurrentPanel() { return currentPanel;}

  /**
   * Changes the panel where the player is
   * @param newPanel
   *      the players new panel
   */
  public void changePanel(IPanel newPanel) {
    this.currentPanel = newPanel;
  }

  /**
   * This player activates the panel where she is
   */
  public void activatePanel(){ currentPanel.activatedBy(this); }

  /**
   * Gets the player's home panel
   */
  public HomePanel getHome(){return home;}

  /**
   * Sets a home panel to be this player's home
   * @param homePanel
   *      the home panel
   */
  public void setHome(HomePanel homePanel){this.home = homePanel;}

  /**
   * Checks if the player has reached their norma goal
   */
  public boolean checkNorma(){
    int [] stars_goal = {-1, 10, 30, 70, 120, 200}; /*amount of stars needed to get norma i+1*/
    int [] wins_goal = {-1, -1, 2, 5, 9, 14}; /*amount of wins needed to get norma i+1*/
    /* the arrays have -1 in the position 0, as norma levels start at 1. wins has a -1 in position 1 as the first goal is always stars */
    if (this.getNormaGoal()==STARS){return this.getStars()>= stars_goal[this.getNormaLevel()];}
    else {return this.getWins()>= wins_goal[this.getNormaLevel()];}
    /*this is horrible, I'm sorry*/
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   * If the player wins (norma reaches 6), this calls the player.isWinner method
   */
  public void normaClear() {
    normaLevel++;
    if (normaLevel==6){ this.isWinner(); }
    if (normaLevel==4){ this.hasNorma4();}
  }

  /**
   * Signals the observers that this player has won
   */
  public void isWinner(){
    this.playerWins.firePropertyChange("winner", null, this);
  }

  /**
   * Signals the observers that this player has norma 4
   */
  public void hasNorma4() {this.playerNorma4.firePropertyChange("norma 4", null, this);}

  /**
   * Sets the players new norma goal
   * @param goal
   *      the new norma goal for the player
   */
  public void setNormaGoal(NormaGoal goal) {
    this.normaGoal=goal;
  }


  /**
   * Sets the current player's atk to a given amount
   */
  public void setAtk(int new_atk){this.atk=new_atk;}

  /**
   * Sets the current player's def to a given amount
   */
  public void setDef(int new_def){this.def=new_def;}

  /**
   * Sets the current player's evd to a given amount
   */
  public void setEvd(int new_evd){this.evd=new_evd;}

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(name, maxHP, atk, def, evd);
  }

  /**
   * This player is defeated by another unit
   * @param winner
   *      the unit that defeated this player
   */
  @Override
  public void defeatedBy(IUnit winner) {
    winner.defeatPlayer(this);
  }

  /**
   * This player defeats another player, increases this player's wins and stars, reduces the loosing player's stars
   * @param player
   *      the player that lost
   */
  @Override
  public void defeatPlayer(Player player) {
    this.increaseWinsBy(2);
    int star = player.getStars()/2;
    this.increaseStarsBy(star);
    player.reduceStarsBy(star);
  }

  /**
   * This player defeats a boss unit, increases this player's wins and stars, reduces the boss units stars
   * @param boss
   *      the defeated boss
   */
  @Override
  public void defeatBoss(BossUnit boss) {
    this.increaseWinsBy(3);
    int star = boss.getStars();
    this.increaseStarsBy(star);
    boss.reduceStarsBy(star);
  }

  /**
   * This player defeats a wild unit, increases this player's wins and stars, reduces the wild units stars
   * @param wild
   *      the defeated wild unit
   */
  @Override
  public void defeatWild(WildUnit wild) {
    this.increaseWinsBy(1);
    int star = wild.getStars();
    this.increaseStarsBy(star);
    wild.reduceStarsBy(star);
  }

  /**
   * Returns the dice amount required to recover
   */
  public int getRequiredRoll() {
        return requiredRoll;
    }

  /**
   * The player dies, their required roll to recover is set to 6
   */
  @Override
  public void dies() {
    setRequiredRoll(6);
  }

  /**
   * Gets the player's decision in a battle. In the future, this should be done with player interaction.
   */
  @Override
  public BattleDecision getBattleDecision() { return battleDecision; }

  /**
   * Moves this player according to the rules, returns the "moves" that were left in case the
   * player stopped to make a decision
   */
  public int move(int x){
    for (int i=0; i<x; i++){
      if(getCurrentPanel().getNextPanels().size()==1) {
        IPanel old = getCurrentPanel();
        old.removePlayer(this);
        IPanel newPanel = old.getNextPanels().iterator().next();
        this.changePanel(newPanel);
        newPanel.addPlayer(this);
        if(getCurrentPanel().equals(getHome())){
          homePanelNotification();
          return x-i-1;
        }
        if (getCurrentPanel().getPlayers().size()>1){
          fightChoiceNotification();
          return x-i-1;
        }
      } else {
        choosePanelNotification(getCurrentPanel().getNextPanels());
        return x-i; }
    }
    return 0;
  }

  /**
   * Sends a notification to the interface that the player runs into another player
   */
  private void fightChoiceNotification() {
    this.fightChoiceProperty.firePropertyChange("fight choice", null, this);
  }

  /**
   * Sends a notification to the interface that the player is in the home panel
   */
  private void homePanelNotification() {
    this.homePanelProperty.firePropertyChange("home panel choice", null, this);
  }

  /**
   * Sends a notification to the interface to choose the next panel
   * @param nextPanels the options
   */
  private void choosePanelNotification(Set<IPanel> nextPanels) {
    this.panelDecisionProperty.firePropertyChange("panels choice", null, nextPanels);
  }

  /**
     * Sets the player's decision in case of being in a battle
     */
    public void setBattleDecision(BattleDecision decision) { battleDecision = decision;}

    /**
     * Gets this players decision in case of running into an enemy
     */
    public FightDecision getFightDecision() {return fightDecision; }

    /**
     * Sets this players decision in case of running into an enemy
     */
    public void setFightDecision(FightDecision decision){fightDecision = decision;}

    /**
     * Returns this player Panel decision if there is more than one next panel
     */
    public IPanel getPanelDecision() {return panelDecision; }

    /**
     * Sets this players panel decision if there is more than one next panel
     */
    public void setPanelDecision(IPanel panel) { panelDecision = panel;}

    /**
   * Sets this player's required dice amount to recover
   * @param i the amount needed
   */
  public void setRequiredRoll(int i) {
    requiredRoll=i;
  }


  /**
   * Sets this players decision to stop at their home panel
   * true if they choose to stop, false if they choose to continue moving
   */
  public void setHomeDecision(HomeDecision decision){ this.homeDecision = decision;}

  /**
   * Returns the players decision to stop at their home panel
   * (true if they want to stop, false if they choose to continue)
   */
  public HomeDecision getHomeDecision() { return homeDecision;  }



  /**
   * Adds a listener to this player
   * @param listener the listener that is added
   */
  public void addPanelChoiceListener(PropertyChangeListener listener) {
    panelDecisionProperty.addPropertyChangeListener(listener);
  }

  /**
   * Adds a listener to this player
   * @param listener the listener that is added
   */
  public void addHomeChoiceListener(PropertyChangeListener listener) {
    homePanelProperty.addPropertyChangeListener(listener);
  }

  /**
   * Adds a listener to this player
   * @param listener the listener that is added
   */
  public void addFightChoiceListener(PropertyChangeListener listener) {
    fightChoiceProperty.addPropertyChangeListener(listener);
  }

  /**
   * Sets this players icon path
   * @param path the path to the icon
   */
  public void setIcon(String path){this.icon = path;}
}
