package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.unit.FightDecision;
import com.github.cc3002.citricjuice.model.unit.HomeDecision;
import com.github.cc3002.citricjuice.model.unit.IUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.Board;
import com.github.cc3002.citricliquid.Game;
import com.github.cc3002.citricliquid.controller.GameController;
import com.github.cc3002.citricliquid.gui.choicehandlers.FightChoiceHandler;
import com.github.cc3002.citricliquid.gui.choicehandlers.HomeChoiceHandler;
import com.github.cc3002.citricliquid.gui.choicehandlers.PanelChoiceHandler;
import com.github.cc3002.citricliquid.gui.node.MovableNode;
import com.github.cc3002.citricliquid.gui.node.MovableNodeBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class CitricLiquid extends Application {
    private static Game game = new Game();
    private GameController controller = game.getController();
    private static Credits credits = new Credits();
    private static Map<IUnit, String> battleIcons= new HashMap<>();
    private static Map<IPanel, Button> panelButtons = new HashMap<>();
    private static Map<Button, IPanel> buttonPanels = new HashMap<>();
    private static Set<Button> homeDecisionButtons = new HashSet<>();
    private static Set<Button> fightChoiceButtons = new HashSet<>();
    private static Map<Player, Label> playerTexts = new HashMap<>();
    private PlayerMovementHandler playerMovementHandler = new PlayerMovementHandler(this);
    private PanelChoiceHandler panelChoiceHandler = new PanelChoiceHandler(this);
    private HomeChoiceHandler homeChoiceHandler = new HomeChoiceHandler(this);
    private FightChoiceHandler fightChoiceHandler = new FightChoiceHandler(this);
    private StartBattleHandler startBattleHandler = new StartBattleHandler(this);
    private DisplayMessageHandler displayMessageHandler = new DisplayMessageHandler(this);
    private static final String RESOURCE_PATH = "src/resources/";

    /**
     * Displays the initial state of the game
     * @param mainStage the stage
     * @throws FileNotFoundException
     */
    @Override
    public void start(@NotNull Stage mainStage) throws FileNotFoundException {
        mainStage.setTitle("99.7% Citric Liquid");
        Group root = new Group();
        int width = 1280;
        int height = 720;
        Scene scene = new Scene(root, width, height);
        var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "board.png")));
        root.getChildren().add(background);

        controller.setBoard();
        Board board = controller.getBoard();
        game.setUp();
        controller.addStartBattleListener(this.startBattleHandler);
        game.getTurn().addMsgListener(displayMessageHandler);
        game.getTurn().addPlayerMovementListener(playerMovementHandler);
        List<IPanel> panels = game.getController().getPanelsList();
        for (int i=0; i<panels.size();i++){
            panels.get(i).addMsgListener(displayMessageHandler);
        }

        ArrayList<Player> players = controller.getPlayers();
        for (int i = 0; i < 4; i++) {
            players.get(i).addPanelChoiceListener(panelChoiceHandler);
            players.get(i).addHomeChoiceListener(homeChoiceHandler);
            players.get(i).addFightChoiceListener(fightChoiceHandler);
        }
        Player suguri = players.get(0);
        Player kai = players.get(1);
        Player qp = players.get(2);
        Player marc = players.get(3);


        var sprite0 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite0.png")
                .setPosition(410, 470)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite0.getNode());
        controller.setPlayerSprite(suguri, sprite0);
        controller.setPlayerImg(suguri, RESOURCE_PATH + "player0.png");

        var sprite1 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite1.png")
                .setPosition(480, 130)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite1.getNode());
        controller.setPlayerSprite(kai, sprite1);
        controller.setPlayerImg(kai, RESOURCE_PATH + "player1.png");

        var sprite2 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite2.png")
                .setPosition(750, 540)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite2.getNode());
        controller.setPlayerSprite(qp, sprite2);
        controller.setPlayerImg(qp, RESOURCE_PATH + "player2.png");

        var sprite3 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite3.png")
                .setPosition(820, 200)
                .setSize(50, 50)
                .build();
        root.getChildren().add(sprite3.getNode());
        controller.setPlayerSprite(marc, sprite3);
        controller.setPlayerImg(marc, RESOURCE_PATH + "player3.png");

        root.getChildren().add(creditsButton());

        List<IPanel> panelList = controller.getPanelsList();
        for(int i=0; i<panelList.size();i++){
            root.getChildren().add(panelChoiceButton(panelList.get(i)));
        }

        for (int i=0; i<4; i++){
            int yPos = 150 + i*125;
            root.getChildren().add(playerText(players.get(i), yPos));
        }

        root.getChildren().add(stopAtHomeButton());
        root.getChildren().add(movePastHomeButton());

        root.getChildren().add(fightPlayerButton());
        root.getChildren().add(ignorePlayerButton());

        root.getChildren().add(actionButton());

        mainStage.setScene(scene);
        mainStage.show();

        startBattle(suguri, marc);

    }

    /**
     * Creates a label that displays a players stats on screen
     * @param player the player
     * @param yPos the vertical position to display the stats
     */
    private Node playerText(Player player, int yPos) {
        String text = player.getName()+"\nHP: "+player.getCurrentHP()+"/"+player.getMaxHP()+"\n"
                +"Current norma level: "+ player.getNormaLevel()+"\nCurrent stars: "+player.getStars()+
                "\nCurrent wins: "+player.getWins()+"\nGoal: "+player.getNormaGoal();
        Label t = new Label(text);
        t.setLayoutX(60);
        t.setLayoutY(yPos);
        playerTexts.put(player, t);
        return t;
    }

    /**
     * Generates a Turn Action button, that will keep the game moving
     */
    private Node actionButton() {
        Button b = new Button("Perform turn action");
        b.setLayoutX(1000);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::turnAction);
        return b;
    }

    /**
     * Makes the game advance by calling the turn's phase's action
     * @param actionEvent the pressing of the button
     */
    private static void turnAction(ActionEvent actionEvent) {
        game.getTurn().getPhase().action();
        updatePlayerLabels();
    }

    /**
     * Updates the players stat labels onscreen
     */
    private static void updatePlayerLabels() {
        Set<Player> players= playerTexts.keySet();
        Iterator iterator = players.iterator();
        while (iterator.hasNext()){
            Player player = (Player)iterator.next();
            updateLabel(player);
        }
    }

    /**
     * Updates a specific players stats label onscreen
     * @param player the player
     */
    private static void updateLabel(Player player) {
        String text = player.getName()+"\nHP: "+player.getCurrentHP()+"/"+player.getMaxHP()+"\n"
                +"Current norma level: "+ player.getNormaLevel()+"\nCurrent stars: "+player.getStars()+
                "\nCurrent wins: "+player.getWins()+"\nGoal: "+player.getNormaGoal();
        Label t = playerTexts.get(player);
        t.setText(text);
    }


    /**
     * Creates a button for ignorePlayer()
     */
    private Node ignorePlayerButton() {
        Button b = new Button("Ignore");
        b.setLayoutX(630);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::ignorePlayer);
        fightChoiceButtons.add(b);
        b.setVisible(false);
        return b;
    }

    /**
     * Sets the current players fight decision to IGNORE
     * @param actionEvent the pressing of the button
     */
    private static void ignorePlayer(ActionEvent actionEvent) {
        game.getController().getTurnOwner().setFightDecision(FightDecision.IGNORE);
        Iterator iterator = fightChoiceButtons.iterator();
        while (iterator.hasNext()){ //disable buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }

    /**
     * Creates a button for fightPlayer
     */
    private Node fightPlayerButton() {
        Button b = new Button("Fight!");
        b.setLayoutX(570);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::fightPlayer);
        fightChoiceButtons.add(b);
        b.setVisible(false);
        return b;
    }

    /**
     * Sets the turn owners fight decision to FIGHT
     * @param actionEvent the pressing of the button
     */
    private static void fightPlayer(ActionEvent actionEvent) {
        game.getController().getTurnOwner().setFightDecision(FightDecision.ENGAGE);
        Iterator iterator = fightChoiceButtons.iterator();
        while (iterator.hasNext()){ //disable buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }

    /**
     * creates a button for stopAtHome
     */
    private Node stopAtHomeButton() {
        Button b = new Button("Stop");
        b.setLayoutX(570);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::stopAtHome);
        homeDecisionButtons.add(b);
        b.setVisible(false);
        return b;
    }

    /**
     * sets the turn owners decision to stop at home
     * @param actionEvent the pressing of the button
     */
    private static void stopAtHome(ActionEvent actionEvent) {
        game.getController().getTurnOwner().setHomeDecision(HomeDecision.STOP);
        Iterator iterator = homeDecisionButtons.iterator();
        while (iterator.hasNext()){ //disable buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }

    /**
     * creates a button for keepMovingHome
     */
    private Node movePastHomeButton() {
        Button b = new Button("Keep Moving");
        b.setLayoutX(630);
        b.setLayoutY(350);
        b.setOnAction(CitricLiquid::keepMovingHome);
        homeDecisionButtons.add(b);
        b.setVisible(false);
        return b;
    }

    /**
     * Sets the turn owners decision to move past their home
     * @param actionEvent the pressing of the button
     */
    private static void keepMovingHome(ActionEvent actionEvent) {
        game.getController().getTurnOwner().setHomeDecision(HomeDecision.KEEPMOVING);
        Iterator iterator = homeDecisionButtons.iterator();
        while (iterator.hasNext()){ //disable buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }

    /**
     * Creates a button to choose to move to a panel
     * @param panel the panel
     */
    private Node panelChoiceButton(IPanel panel) {
        Button b = new Button();
        buttonPanels.put(b,panel);
        panelButtons.put(panel,b);
        Pair<Integer,Integer> position = game.getController().getPanelPosition(panel);
        b.setLayoutX(position.getKey()+15);
        b.setLayoutY(position.getValue()+15);
        b.setOnAction(CitricLiquid::panelChosen);
        b.setVisible(false);
        return b;
    }

    /**
     * Sets the panel decision to this buttons panel
     * @param actionEvent the button being pressed
     */
    private static void panelChosen(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        IPanel panel = buttonPanels.get(b);
        game.getController().getTurnOwner().setPanelDecision(panel);
        System.out.println(game.getController().getTurnOwner().getPanelDecision());
        Set<Button> buttonSet = buttonPanels.keySet();
        Iterator iterator = buttonSet.iterator();
        while (iterator.hasNext()){ //disable all the panel buttons
            Button next = (Button)iterator.next(); next.setVisible(false);
        }
    }


    /**
     * Moves a sprite in the gui
     * @param player the player that moves
     * @param panel the panel the sprite should move to
     */
    public void moveSprite(Player player, IPanel panel) {
        MovableNode sprite = game.getController().getPlayerSprite(player);
        Pair<Integer, Integer> position = game.getController().getPanelPosition(panel);
        sprite.moveTo(position.getKey(), position.getValue());
    }

    /**
     * Makes a button to display credits
     */
    private Node creditsButton() {
        Button b = credits.getButton();
        return b;
    }

    /**
     * Enables the buttons corresponding to a set of panels
     * @param panels the panels
     */
    public void enablePanelButtons(Set<IPanel> panels) {
        Iterator iterator = panels.iterator();
        while (iterator.hasNext()){ //enable all the panel buttons
            IPanel next = (IPanel) iterator.next();
            Button b = panelButtons.get(next);
            b.setVisible(true);
        }
    }

    /**
     * Enables the buttons to make a stopping at home choice
     */
    public void enableHomeButtons() {
        Iterator iterator = homeDecisionButtons.iterator();
        while (iterator.hasNext()){
            Button next = (Button) iterator.next();
            next.setVisible(true);
        }
    }

    /**
     * Enables the buttons to make a fighting or ignoring another player choice
     */
    public void enableFightChoiceButtons() {
        Iterator iterator = fightChoiceButtons.iterator();
        while (iterator.hasNext()){
            Button next = (Button) iterator.next();
            next.setVisible(true);
        }
    }

    /**
     * Creates a battle interface for a battle between 2 units
     * @param unit1 the 1st unit
     * @param unit2 the 2nd unit
     * @throws FileNotFoundException
     */
    public void startBattle(IUnit unit1, IUnit unit2) throws FileNotFoundException {
        BattleInterface battle = new BattleInterface(unit1, unit2, this.game);
    }

    /**
     * Creates a popup for a message
     * @param msg the message to be displayed
     */
    public void displayMsg(String msg) {
        MessagePopUp message = new MessagePopUp(msg);
    }
}
