package com.github.cc3002.citricliquid;

import com.github.cc3002.citricliquid.controller.GameController;
import com.github.cc3002.citricjuice.model.board.*;

import java.util.ArrayList;
import java.util.Objects;

public class Board {
    private ArrayList<Panel> neutralPanels;
    private ArrayList<HomePanel> homePanels;
    private ArrayList<DropPanel> dropPanels;
    private ArrayList<BonusPanel> bonusPanels;
    private ArrayList<EncounterPanel> encounterPanels;
    private ArrayList<BossPanel> bossPanels;
    private final GameController controller;

    public Board(GameController controller){
        super();
        this.controller=controller;
        neutralPanels = new ArrayList<Panel>();
        homePanels = new ArrayList<HomePanel>();
        dropPanels = new ArrayList<DropPanel>();
        bonusPanels = new ArrayList<BonusPanel>();
        encounterPanels = new ArrayList<EncounterPanel>();
        bossPanels = new ArrayList<BossPanel>();

        for (int i=0; i<4; i++){
            HomePanel panel = controller.createHomePanel(i);
            homePanels.add(panel);
        }
        for (int i=0; i<8; i++){
            DropPanel panel = controller.createDropPanel(i);
            dropPanels.add(panel);
        }
        for (int i=0; i<8; i++){
            BonusPanel panel = controller.createBonusPanel(i);
            bonusPanels.add(panel);
        }
        for (int i=0; i<12; i++){
            Panel panel = controller.createNeutralPanel(i);
            neutralPanels.add(panel);
        }
        for (int i=0; i<8; i++){
            EncounterPanel panel = controller.createEncounterPanel(i);
            encounterPanels.add(panel);
        }

        //set home panel positions:
        controller.setPanelPosition(homePanels.get(0),410,470);
        controller.setPanelPosition(homePanels.get(1),480,130);
        controller.setPanelPosition(homePanels.get(2),750,540);
        controller.setPanelPosition(homePanels.get(3),820,200);
        //set neutral panel positions:
        controller.setPanelPosition(neutralPanels.get(0),342,266);
        controller.setPanelPosition(neutralPanels.get(1),410,266);
        controller.setPanelPosition(neutralPanels.get(2),480,402);
        controller.setPanelPosition(neutralPanels.get(3),548,198);
        controller.setPanelPosition(neutralPanels.get(4),548,538);
        controller.setPanelPosition(neutralPanels.get(5),548,606);
        controller.setPanelPosition(neutralPanels.get(6),684,62);
        controller.setPanelPosition(neutralPanels.get(7),684,130);
        controller.setPanelPosition(neutralPanels.get(8),684,470);
        controller.setPanelPosition(neutralPanels.get(9),750,266);
        controller.setPanelPosition(neutralPanels.get(10),820,402);
        controller.setPanelPosition(neutralPanels.get(11),882,402);
        //set drop panel positions:
        controller.setPanelPosition(dropPanels.get(0),342,470);
        controller.setPanelPosition(dropPanels.get(1),480,62);
        controller.setPanelPosition(dropPanels.get(2),480,334);
        controller.setPanelPosition(dropPanels.get(3),616,198);
        controller.setPanelPosition(dropPanels.get(4),616,470);
        controller.setPanelPosition(dropPanels.get(5),750,334);
        controller.setPanelPosition(dropPanels.get(6),750,606);
        controller.setPanelPosition(dropPanels.get(7),882,198);
        //set bonus panel positions:
        controller.setPanelPosition(bonusPanels.get(0),342,402);
        controller.setPanelPosition(bonusPanels.get(1),480,266);
        controller.setPanelPosition(bonusPanels.get(2),548,62);
        controller.setPanelPosition(bonusPanels.get(3),548,470);
        controller.setPanelPosition(bonusPanels.get(4),684,198);
        controller.setPanelPosition(bonusPanels.get(5),684,606);
        controller.setPanelPosition(bonusPanels.get(6),750,402);
        controller.setPanelPosition(bonusPanels.get(7),882,266);
        //set encounter panel positions:
        controller.setPanelPosition(encounterPanels.get(0),342,334);
        controller.setPanelPosition(encounterPanels.get(1),480,198);
        controller.setPanelPosition(encounterPanels.get(2),480,470);
        controller.setPanelPosition(encounterPanels.get(3),616,62);
        controller.setPanelPosition(encounterPanels.get(4),616,606);
        controller.setPanelPosition(encounterPanels.get(5),750,198);
        controller.setPanelPosition(encounterPanels.get(6),750,470);
        controller.setPanelPosition(encounterPanels.get(7),882,334);



        controller.setNextPanel(homePanels.get(0), encounterPanels.get(2));
        controller.setNextPanel(homePanels.get(1), encounterPanels.get(1));
        controller.setNextPanel(homePanels.get(2), encounterPanels.get(6));
        controller.setNextPanel(homePanels.get(3), encounterPanels.get(5));

        controller.setNextPanel(dropPanels.get(0), homePanels.get(0));
        controller.setNextPanel(dropPanels.get(1), homePanels.get(1));
        controller.setNextPanel(dropPanels.get(2), neutralPanels.get(2));
        controller.setNextPanel(dropPanels.get(3), neutralPanels.get(3));
        controller.setNextPanel(dropPanels.get(4), neutralPanels.get(8));
        controller.setNextPanel(dropPanels.get(5), neutralPanels.get(9));
        controller.setNextPanel(dropPanels.get(6), homePanels.get(2));
        controller.setNextPanel(dropPanels.get(7), homePanels.get(3));

        controller.setNextPanel(bonusPanels.get(0), dropPanels.get(0));
        controller.setNextPanel(bonusPanels.get(1), neutralPanels.get(1));
        controller.setNextPanel(bonusPanels.get(1), dropPanels.get(2));
        controller.setNextPanel(bonusPanels.get(2), dropPanels.get(1));
        controller.setNextPanel(bonusPanels.get(3), dropPanels.get(4));
        controller.setNextPanel(bonusPanels.get(3), neutralPanels.get(4));
        controller.setNextPanel(bonusPanels.get(4), neutralPanels.get(7));
        controller.setNextPanel(bonusPanels.get(4), dropPanels.get(3));
        controller.setNextPanel(bonusPanels.get(5), dropPanels.get(6));
        controller.setNextPanel(bonusPanels.get(6), dropPanels.get(5));
        controller.setNextPanel(bonusPanels.get(6), neutralPanels.get(10));
        controller.setNextPanel(bonusPanels.get(7), dropPanels.get(7));

        controller.setNextPanel(neutralPanels.get(0), encounterPanels.get(0));
        controller.setNextPanel(neutralPanels.get(1), neutralPanels.get(0));
        controller.setNextPanel(neutralPanels.get(2), encounterPanels.get(2));
        controller.setNextPanel(neutralPanels.get(3), encounterPanels.get(1));
        controller.setNextPanel(neutralPanels.get(4), neutralPanels.get(5));
        controller.setNextPanel(neutralPanels.get(5), encounterPanels.get(4));
        controller.setNextPanel(neutralPanels.get(6), encounterPanels.get(3));
        controller.setNextPanel(neutralPanels.get(7), neutralPanels.get(6));
        controller.setNextPanel(neutralPanels.get(8), encounterPanels.get(6));
        controller.setNextPanel(neutralPanels.get(9), encounterPanels.get(5));
        controller.setNextPanel(neutralPanels.get(10), neutralPanels.get(11));
        controller.setNextPanel(neutralPanels.get(11), encounterPanels.get(7));

        controller.setNextPanel(encounterPanels.get(0), bonusPanels.get(0));
        controller.setNextPanel(encounterPanels.get(1), bonusPanels.get(1));
        controller.setNextPanel(encounterPanels.get(2), bonusPanels.get(3));
        controller.setNextPanel(encounterPanels.get(3), bonusPanels.get(2));
        controller.setNextPanel(encounterPanels.get(4), bonusPanels.get(5));
        controller.setNextPanel(encounterPanels.get(5), bonusPanels.get(4));
        controller.setNextPanel(encounterPanels.get(6), bonusPanels.get(6));
        controller.setNextPanel(encounterPanels.get(7), bonusPanels.get(7));
    }

    /**
     * Checks if another object equals this board
     * @param o the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(controller, board.controller);
    }

    /**
     * Returns a copy of this boards home panels
     */
    public ArrayList<HomePanel> getHomePanels() {
        return new ArrayList<HomePanel>(homePanels);
    }
}
