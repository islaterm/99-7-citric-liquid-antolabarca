package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;
import org.jetbrains.annotations.NotNull;

public class HomePanel extends Panel{

  public HomePanel(int id){
    super(id);
  }

  /**
   * Restores a player's HP in 1.
   */
  public void activatedBy(final @NotNull Player player) {
    player.setCurrentHP(player.getCurrentHP() + 1);
    sendMsg(player.getName()+"'s HP is now "+player.getCurrentHP());
    if (player.checkNorma()){
      player.normaClear();
      sendMsg(player.getName()+"'s norma level is now "+player.getNormaLevel()+"!");
    }
  }

}
