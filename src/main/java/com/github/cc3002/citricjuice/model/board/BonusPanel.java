package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

public class BonusPanel extends Panel {

  public BonusPanel(){
    super();
  }

  /**
   * Increases the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  public void activatedBy(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }
}