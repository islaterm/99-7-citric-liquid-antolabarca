package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;
import org.jetbrains.annotations.NotNull;

public class BonusPanel extends Panel {

  public BonusPanel(int id){super(id);}

  /**
   * Increases the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  public void activatedBy(final @NotNull Player player) {
    int stars = player.roll() * Math.min(player.getNormaLevel(), 3);
    player.increaseStarsBy(stars);
    sendMsg(player.getName()+" earned "+stars+" stars :)");
  }
}
