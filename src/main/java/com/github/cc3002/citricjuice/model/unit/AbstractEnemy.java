package com.github.cc3002.citricjuice.model.unit;


import com.github.cc3002.citricjuice.model.board.IEnemyPanel;

public abstract class AbstractEnemy extends AbstractUnit implements IEnemy{
    IEnemyPanel panel;

    /**
     * Creates a new Unit.
     *
     * @param name
     * @param hp   the initial (and max) hit points of the unit.
     * @param atk  the base damage the unit does.
     * @param def  the base defense of the unit.
     * @param evd
     */
    public AbstractEnemy(String name, int hp, int atk, int def, int evd) {
        super(name, hp, atk, def, evd);
        this.icon = "src/resources/enemy.png";
    }



    /**
     * Sets this enemy's panel
     * @param panel the panel to be set
     */
    public void setPanel(IEnemyPanel panel){ this.panel=panel; }

    /**
     * Returns this enemy's panel
     */
    public IEnemyPanel getPanel(){ return panel;}

    /**
     * Chooses a random response to being attacked
     */
    public BattleDecision getBattleDecision(){
        int rand = (int) Math.round(Math.random());
        BattleDecision battleDecisions[] = {BattleDecision.DEFEND, BattleDecision.EVADE};
        return battleDecisions[rand];
    }

    /**
     * Does nothing, this is sort of a null pattern to use unit.setBattleDecision in the controller/interface without
     * having to use a lot of ifs
     */
    public void setBattleDecision(BattleDecision decision){ }
}
