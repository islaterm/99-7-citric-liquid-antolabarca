package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.board.BossPanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BossUnit extends AbstractEnemy{
    private PropertyChangeSupport defeated = new PropertyChangeSupport(this);

    /**
     * Creates a new Boss.
     *
     * @param  name
     *      the name of the boss
     * @param hp
     *     the initial (and max) hit points of the boss.
     * @param atk
     *     the base damage the boss does.
     * @param def
     *     the base defense of the boss.
     * @param evd
     *     the base evasion of the boss.
     */
    public BossUnit(String name, int hp, int atk, int def, int evd){
        super(name, hp,atk,def,evd);
    }

    /**
     * Creates a copy of this Boss
     */
    public BossUnit copy(){ return new BossUnit(name, maxHP, atk, def, evd);}

    /**
     * This boss is defeated by another unit
     * @param winner
     *      the unit that defeated this one
     */
    @Override
    public void defeatedBy(IUnit winner) {
        this.bossWasDefeated();
        winner.defeatBoss(this);
    }

    /**
     * Signals the observers that this boss was defeated
     */
    public void bossWasDefeated(){
        this.defeated.firePropertyChange("defeated boss", null, this);
    }

    /**
     * Adds a listener
     */
    public void addBossListener(PropertyChangeListener listener){
        defeated.addPropertyChangeListener(listener);
    }

    /**
     * This boss unit defeats a player, increases this unit's wins and stars, reduces the players stars
     * @param player
     *      the defeated player
     */
    @Override
    public void defeatPlayer(Player player) {
        this.increaseWinsBy(2);
        int star = player.getStars()/2;
        this.increaseStarsBy(star);
        player.reduceStarsBy(star);
    }

    /**
     * This boss unit defeats another boss unit, increases this unit's wins and stars, reduces the loosing boss's stars
     * @param boss
     *      the boss that lost
     */
    @Override
    public void defeatBoss(BossUnit boss) {
        this.increaseWinsBy(3);
        int star = boss.getStars()/2;
        this.increaseStarsBy(star);
        boss.reduceStarsBy(star);
    }

    /**
     * This boss unit defeats a wild unit, increases this unit's wins and stars, reduces the wild units stars
     * @param wild
     *      the defeated wild unit
     */
    @Override
    public void defeatWild(WildUnit wild) {
        this.increaseWinsBy(1);
        int star = wild.getStars()/2;
        this.increaseStarsBy(star);
        wild.reduceStarsBy(star);
    }


    /**
     * Creates a copy of this unit and puts it in this unit's panel
     */
    @Override
    public void dies() {
        BossUnit newBoss = this.copy();
        BossPanel panel = (BossPanel) this.panel;
        panel.setBoss(newBoss);
    }
}
