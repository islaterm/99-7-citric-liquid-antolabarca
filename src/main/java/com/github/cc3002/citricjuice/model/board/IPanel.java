package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Set;

public interface IPanel {
    boolean equals(Object o);
    Set<IPanel> getNextPanels();
    ArrayList<IPanel> getNextPanelsList();
    void addNextPanel(final IPanel panel);
    void activatedBy(Player player);
    void addPlayer (Player player);
    Set<Player> getPlayers();
    ArrayList<Player> getPlayersList();
    void removePlayer(Player player);
    void addMsgListener(PropertyChangeListener listener);
    void sendMsg(String s);
}
