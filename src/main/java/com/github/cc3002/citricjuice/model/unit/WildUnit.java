package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.board.EncounterPanel;

public class WildUnit extends AbstractEnemy{

    /**
     * Creates a new Wild unit.
     *
     * @param name
     *  the name of the wild unit
     * @param hp
     *     the initial (and max) hit points of the wild unit.
     * @param atk
     *     the base damage the wild unit does.
     * @param def
     *     the base defense of the wild unit.
     * @param evd
     *     the base evasion of the wild unit.
     */
    public WildUnit(String name, int hp, int atk, int def, int evd){
        super(name, hp,atk,def,evd);
    }

    /**
     * Creates a copy of this Wild unit
     */
    public WildUnit copy(){ return new WildUnit(name, maxHP, atk, def, evd);}

    /**
     * This wild unit is defeated by another unit
     * @param winner
     *      the unit that defeated this one
     */
    @Override
    public void defeatedBy(IUnit winner) {
        winner.defeatWild(this);
    }

    /**
     * This wild unit defeats a player, increases this unit's wins and stars, reduces the players stars
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
     * This wild unit defeats a boss unit, increases this unit's wins and stars, reduces the boss units stars
     * @param boss
     *      the defeated boss
     */
    @Override
    public void defeatBoss(BossUnit boss) {
        this.increaseWinsBy(3);
        int star = boss.getStars()/2;
        this.increaseStarsBy(star);
        boss.reduceStarsBy(star);
    }

    /**
     * This wild unit defeats another wild unit, increases this unit's wins and stars, reduces the loosing wild unit's stars
     * @param wild
     *      the wild unit that lost
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
        WildUnit newWild = this.copy();
        EncounterPanel panel = (EncounterPanel) this.panel;
        panel.setWild(newWild);
    }

}
