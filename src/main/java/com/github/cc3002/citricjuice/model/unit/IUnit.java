package com.github.cc3002.citricjuice.model.unit;

public interface IUnit {
    int getStars();
    void increaseStarsBy(int amount);
    void reduceStarsBy(int amount);
    int getWins();
    void increaseWinsBy(int amount);
    void setSeed(final long seed);
    int roll();
    String getName();
    int getMaxHP();
    int getAtk();
    int getDef();
    int getEvd();
    int getCurrentHP();
    boolean isDown();
    void setCurrentHP(int newHP);
    IUnit copy();
    int attackedBy(IUnit attacker);
    void defendsFrom(IUnit attacker);
    void evades(IUnit attacker);
    void defeatedBy(IUnit winner);
    void defeatPlayer(Player player);
    void defeatBoss(BossUnit boss);
    void defeatWild(WildUnit wild);
}
