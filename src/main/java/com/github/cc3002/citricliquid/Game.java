package com.github.cc3002.citricliquid;

import com.github.cc3002.citricliquid.TurnPhases.Turn;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.controller.GameController;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Game {
    GameController controller = new GameController();
    Turn turn;
    Board board = new Board(controller);

    public Game(){
        super();
    }

    /**
     * Returns this game's controller
     */
    public GameController getController() {
        return controller;
    }

    /**
     * Returns the games turn
     */
    public Turn getTurn(){ return turn;}

    public void setUp(){
        Player suguri = controller.createPlayer("Suguri", 4,1,-1,2, board.getHomePanels().get(0));
        controller.setPlayerHome(suguri, board.getHomePanels().get(0));
        Player kai = controller.createPlayer("Kai", 5,1,0,0,board.getHomePanels().get(1));
        controller.setPlayerHome(kai, board.getHomePanels().get(1));
        Player qp = controller.createPlayer("QP", 5,0,0,0,board.getHomePanels().get(2));
        controller.setPlayerHome(qp, board.getHomePanels().get(2));
        Player marc = controller.createPlayer("Marc", 4,1,1,-1,board.getHomePanels().get(3));
        controller.setPlayerHome(marc, board.getHomePanels().get(3));
        turn = new Turn(controller);
    }

}
