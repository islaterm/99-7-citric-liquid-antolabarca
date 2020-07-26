package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.board.IPanel;

import java.util.Random;

public abstract class AbstractUnit implements IUnit{
    protected final String name;
    protected final Random random;
    protected final int maxHP;
    protected int currentHP;
    protected int wins;
    protected int stars;
    protected int atk;
    protected int def;
    protected int evd;
    protected String icon;

    /**
     * Creates a new Unit.
     *
     * @param hp
     *     the initial (and max) hit points of the unit.
     * @param atk
     *     the base damage the unit does.
     * @param def
     *     the base defense of the unit.
     * @param evd
     *     the base evasion of the unit.
     */
    public AbstractUnit(final String name, final int hp, final int atk, final int def, final int evd){
        this.name=name;
        this.maxHP=hp;
        this.currentHP=hp;
        this.atk=atk;
        this.def=def;
        this.evd=evd;
        this.wins=0;
        this.stars=0;
        this.random = new Random();
    }

    /**
     * Checks if another object equals this unit
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUnit unit = (AbstractUnit) o;
        return maxHP == unit.maxHP &&
                currentHP == unit.currentHP &&
                wins == unit.wins &&
                stars == unit.stars &&
                atk == unit.atk &&
                def == unit.def &&
                evd == unit.evd;
    }

    /**
     * Returns the unit's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this units's star count.
     */
    public int getStars() {
        return stars;
    }

    /**
     * Increases this unit's star count by an amount.
     */
    public void increaseStarsBy(int amount) {
        stars += amount;
    }

    /**
     * Reduces this unit's star count by a given amount.
     * <p>
     * The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(int amount) {
        stars = Math.max(0, stars - amount);
    }

    /**
     * Returns this unit's amount of wins
     */
    public int getWins(){return wins;}

    /**
     * Increases this unit's win count by a given amount
     */
    public void increaseWinsBy(int amount) {wins += amount;}

    /**
     * Set's the seed for this unit's random number generator.
     * <p>
     * The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }

    /**
     * Returns the unit's max hit points.
     */
    public int getMaxHP() { return maxHP; }

    /**
     * Returns the current unit's attack points.
     */
    public int getAtk() { return atk; }

    /**
     * Returns the current unit's defense points.
     */
    public int getDef() { return def; }

    /**
     * Returns the current unit's evasion points.
     */
    public int getEvd() { return evd; }

    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHP() { return currentHP; }

    /**
     * Returns true if the character is down (has 0 HitPoints)
     */
    public boolean isDown() { return getCurrentHP()==0; }

    /**
     * Sets the current unit's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    public void setCurrentHP(int newHP) {
        this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }

    /**
     * Returns a copy of this unit.
     */
    public abstract IUnit copy();

    /**
     * This unit gets attacked by another one, returns the amount of the attack
     * @param attacker
     *      the unit that attacks
     */
    public int attackedBy(IUnit attacker){
        int roll = attacker.roll();
        int attack = roll + attacker.getAtk();
        return attack;
    }

    /**
     * This unit defends from an attack from another unit
     * Sets the new HP of the unit
     * @param attack
     *          the amount of the attack
     */
    public void defendsFrom(int attack){
        int roll = this.roll();
        int defense = roll + this.getDef();
        int dmg = Math.max(1, (attack-defense));
        this.setCurrentHP(this.getCurrentHP()-dmg);
    }

    /**
     * This unit attempts to evade an attack from another unit.
     * Sets the new HP if evading is unsuccessful
     * @param attack
     *         the amount of the attack
     */
    public void evades(int attack){
        int roll = this.roll();
        int evade = roll + this.getEvd();
        if (attack > evade){
            this.setCurrentHP(this.getCurrentHP()-attack);
        }
    }

    /**
     * A battle round with another unit, this unit attacks
     * @param unit2 the unit being attacked
     * @param decision unit2's decision on how to respond
     */
    public void battleRound(IUnit unit2, BattleDecision decision){
        int attack1 = unit2.attackedBy(this);
        if(decision==BattleDecision.DEFEND){
            unit2.defendsFrom(attack1);
        }else {this.evades(attack1);}
    }

    /**
     * This unit is defeated by another
     * @param winner
     *      the unit that won
     */
    public abstract void defeatedBy(IUnit winner);

    /**
     * This unit defeats a Player unit
     * @param player
     *      the player that was defeated
     */
    public  abstract void defeatPlayer(Player player);

    /**
     * This unit defeats a Boss unit
     * @param boss
     *      the boss unit that was defeated
     */
    public abstract void defeatBoss(BossUnit boss);

    /**
     * This unit defeats a Wild unit
     * @param wild
     *      the wild unit that was defeated
     */
    public abstract void defeatWild(WildUnit wild);

    /**
     * Returns the path to this units icon
     */
    public String getIcon(){ return icon;}

}
