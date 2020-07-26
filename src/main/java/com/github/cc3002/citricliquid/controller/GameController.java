package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricjuice.model.unit.*;
import com.github.cc3002.citricliquid.Board;
import com.github.cc3002.citricliquid.gui.node.MovableNode;
import javafx.util.Pair;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class GameController {
    protected int game_turn;
    public ArrayList<Player> players;
    public Set<IPanel> panelsSet = new HashSet<>();
    protected boolean gameIsWon = false;
    protected Player winner;
    protected boolean norma4 = false;
    protected boolean bossDefeated = false;
    protected WinHandler winHandler = new WinHandler(this);
    protected Norma4Handler norma4Handler = new Norma4Handler(this);
    protected BossDefeatHandler bossDefeatHandler = new BossDefeatHandler(this);
    protected BossBattleHandler bossBattleHandler = new BossBattleHandler(this);
    protected WildBattleHandler wildBattleHandler = new WildBattleHandler(this);
    protected Board board;
    private Map<IPanel, Pair<Integer, Integer>> panelPositions = new HashMap<>();
    private Map<Player, MovableNode> sprites = new HashMap<>();
    private PropertyChangeSupport BattleStartProperty = new PropertyChangeSupport(this);

    public GameController() {
        super();
        this.game_turn = 0;
        this.players = new ArrayList<Player>();
        gameIsWon = false;
        winner = null;
    }

    /**
     * Checks if an object equals this one
     *
     * @param o the object that is checked
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameController that = (GameController) o;
        return game_turn == that.game_turn &&
                gameIsWon == that.gameIsWon &&
                norma4 == that.norma4 &&
                bossDefeated == that.bossDefeated &&
                players.equals(that.players) &&
                Objects.equals(panelsSet, that.panelsSet) &&
                Objects.equals(winner, that.winner) &&
                Objects.equals(board, that.board);
    }

    /**
     * Sets this controllers board
     */
    public void setBoard() {
        this.board = new Board(this);
    }

    /**
     * Gets this controllers board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Creates a new bonus panel and adds it to the panelsSet
     *
     * @param id the id of the panel
     * @return the new panel
     */
    public BonusPanel createBonusPanel(int id) {
        BonusPanel panel = new BonusPanel(id);
        panelsSet.add(panel);
        return panel;
    }

    /**
     * Creates a new boss panel and adds it to the panelsSet
     *
     * @param id the id of the panel
     * @return the new panel
     */
    public BossPanel createBossPanel(int id) {
        BossPanel panel = new BossPanel(id);
        panelsSet.add(panel);
        panel.addBossPanelListener(bossBattleHandler);
        panel.setBoss(new BossUnit("flying castle", 5, 1, 1, 1)); //for now
        return panel;
    }

    /**
     * Creates a new drop panel and adds it to the panelsSet
     *
     * @param id the id of the panel
     * @return the new panel
     */
    public DropPanel createDropPanel(int id) {
        DropPanel panel = new DropPanel(id);
        panelsSet.add(panel);
        return panel;
    }

    /**
     * Creates a new encounter panel and adds it to the panelsSet
     *
     * @param id the id of the panel
     * @return the new panel
     */
    public EncounterPanel createEncounterPanel(int id) {
        EncounterPanel panel = new EncounterPanel(id);
        panelsSet.add(panel);
        panel.addEncounterPanelListener(wildBattleHandler);
        return panel;
    }

    /**
     * Creates a new home panel and adds it to the panelsSet
     *
     * @param id the id of the panel
     * @return the new panel
     */
    public HomePanel createHomePanel(int id) {
        HomePanel panel = new HomePanel(id);
        panelsSet.add(panel);
        return panel;
    }

    /**
     * Creates a new neutral panel and adds it to the panelsSet
     *
     * @param id the id of the panel
     * @return the new panel
     */
    public Panel createNeutralPanel(int id) {
        Panel panel = new Panel(id);
        panelsSet.add(panel);
        return panel;
    }

    /**
     * Creates a new player, and adds it to the playersList. It also adds the player
     * to a specific panel
     *
     * @param name      the name of the player
     * @param hitPoints the max (and initial) HP value of the player
     * @param attack    the initial atk value of the player
     * @param defense   the initial def value of the player
     * @param evasion   the initial evd value of the player
     * @param panel     the IPanel where the player will be added
     * @return the new player
     */
    public Player createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
        Player player = new Player(name, hitPoints, attack, defense, evasion);
        panel.addPlayer(player);
        players.add(player);
        player.addPlayerWinsListener(winHandler);
        player.addPlayerNorma4Listener(norma4Handler);
        return player;
    }

    /**
     * Returns a list of the players in the game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Creates a new wild unit
     *
     * @param name      the name
     * @param hitPoints the initial and max HP value of the unit
     * @param attack    the atk value of the unit
     * @param defense   the def value of the unit
     * @param evasion   the evd value of the unit
     * @return the new wild unit
     */
    public WildUnit createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new WildUnit(name, hitPoints, attack, defense, evasion);
    }

    /**
     * Creates a new boss unit
     *
     * @param name      the name
     * @param hitPoints the initial and max HP value of the unit
     * @param attack    the atk value of the unit
     * @param defense   the def value of the unit
     * @param evasion   the evd value of the unit
     * @return the new boss unit
     */
    public BossUnit createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        BossUnit boss = new BossUnit(name, hitPoints, attack, defense, evasion);
        boss.addBossListener(bossDefeatHandler);
        return boss;
    }

    /**
     * Sets the turn owner's new norma goal
     *
     * @param goal the type of goal
     */
    public void setCurrPlayerNormaGoal(NormaGoal goal) {
        Player player = getTurnOwner();
        player.setNormaGoal(goal);
    }

    /**
     * Returns the player who's turn it currently is
     */
    public Player getTurnOwner() {
        int current = game_turn % 4;
        return players.get(current);
    }

    /**
     * Returns the current turn of the game
     */
    public int getGameTurn() {
        return game_turn;
    }

    /**
     * Returns the current chapter of the game
     */
    public int getChapter() {
        int n_players = 4;
        int c = game_turn / n_players;
        return c + 1; /* 1 is added due to the values of c starting from 0, but game chapters start from 1*/
    }

    /**
     * Adds a panel to another one's nextPanels
     *
     * @param panel  the original panel
     * @param panel1 the panel that will be added as nextPanel
     */
    public void setNextPanel(IPanel panel, IPanel panel1) {
        panel.addNextPanel(panel1);
    }

    /**
     * Returns a set of the panels in the game
     */
    public Set<IPanel> getPanels() {
        return panelsSet;
    }

    /**
     * Returns a list of the panels in the game
     */
    public ArrayList<IPanel> getPanelsList() {
        var panels = new ArrayList<IPanel>();
        panels.addAll(panelsSet);
        return panels;
    }


    /**
     * Rolls a dice and moves the turn owner player according to the movement rules,
     * stopping where the player needs to make a decision. Activates the panel where the player
     * stops
     */
    public void movePlayer() {
        Player player = getTurnOwner();
        int roll = player.roll();
        int y = player.move(roll);
        player.activatePanel();
    }

    /**
     * Returns the panel where a player is
     * @param unit the player
     */
    public IPanel getPlayerPanel(Player unit) {
        return unit.getCurrentPanel();
    }

    /**
     * Sets a player's home panel
     *
     * @param unit  the player
     * @param panel the home panel
     */
    public void setPlayerHome(Player unit, HomePanel panel) {
        unit.setHome(panel);
    }


    /**
     * Battle between unit1 and unit2
     *
     * @param unit1
     * @param unit2
     */
    public void battle(IUnit unit1, IUnit unit2) {
        if (!unit2.isDown()) {
            battleNotification(unit1, unit2);
            while (unit1.getBattleDecision()==null || unit2.getBattleDecision()==null){
                ;
            }
            BattleDecision decision2 = unit2.getBattleDecision();
            unit1.battleRound(unit2, decision2);
            if (unit2.isDown()) {
                unit2.defeatedBy(unit1);
                unit2.dies();
            } else {
                BattleDecision decision1 = unit1.getBattleDecision();
                unit2.battleRound(unit1, decision1);
                if (unit1.isDown()) {
                    unit1.defeatedBy(unit2);
                    unit1.dies();
                }
            }
            unit1.setBattleDecision(null);
            unit2.setBattleDecision(null);
        }
    }

    /**
     * Adds a listener to this controllers battle
     * @param listener the listener that is added
     */
    public void addStartBattleListener(PropertyChangeListener listener) {
        this.BattleStartProperty.addPropertyChangeListener(listener);
    }

    /**
     * Notifies the interface to display a battle between 2 units
     * @param unit1
     * @param unit2
     */
    private void battleNotification(IUnit unit1, IUnit unit2) {
        this.BattleStartProperty.firePropertyChange("battle starts", null, new Pair<IUnit, IUnit>(unit1, unit2));
    }

    /**
     * Player wins, changes the gameiswon property to true, and sets the winner player
     *
     * @param player the winning player
     */
    public void playerWins(Player player) {
        gameIsWon = true;
        winner = player;
    }


    /**
     * Returns the winner player
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Returns true if the game has been won
     */
    public boolean isGameWon() {
        return gameIsWon;
    }

    /**
     * Player reaches norma 4, changes the norma4 property to true
     */
    public void playerReachedNorma4(Player norma4Player) {
        norma4 = true;
    }

    /**
     * Returns true if a player has reached norma 4
     */
    public boolean isNorma4() {
        return norma4;
    }


    /**
     * Boss has been defeated, changes the bossDefeated property to true
     */
    public void bossDefeated(BossUnit boss) {
        bossDefeated = true;
    }

    /**
     * Returns the boss defeated property
     */
    public boolean isBossDefeated() {
        return bossDefeated;
    }

    /**
     * Ends the current turn
     */
    public void endTurn() {
        game_turn++;
        Player player = getTurnOwner();
        //notification to interface :(
    }


    /**
     * Sets a player position to the position of the panel where she is
     *
     * @param p the player
     */
    public Pair<Integer, Integer> getPanelPosition(IPanel p) {
        Pair<Integer, Integer> position = panelPositions.get(p);
        return position;
    }

    /**
     * Sets a panels position
     *
     * @param panel the panel
     * @param x     the x coordenate
     * @param y     the y coordenate
     */
    public void setPanelPosition(IPanel panel, int x, int y) {
        panelPositions.put(panel, new Pair<>(x, y));
    }

    /**
     * Returns a players sprite
     *
     * @param p the player
     */
    public MovableNode getPlayerSprite(Player p) {
        return sprites.get(p);
    }

    /**
     * Sets a players sprite
     *
     * @param player the player
     * @param sprite the sprite
     */
    public void setPlayerSprite(Player player, MovableNode sprite) {
        sprites.put(player, sprite);
    }

    /**
     * Sets a players image path
     * @param player
     * @param path
     */
    public void setPlayerImg(Player player, String path) {
        player.setIcon(path);
    }
}
