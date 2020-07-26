package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.unit.BattleDecision;
import com.github.cc3002.citricjuice.model.unit.IUnit;
import com.github.cc3002.citricliquid.Game;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BattleInterface {
    private static IUnit unit1;
    private static IUnit unit2;
    private static Set<Button> unit1buttons = new HashSet<>();
    private static Set<Button> unit2buttons = new HashSet<>();
    static Stage stage = new Stage();
    Game game;

    public BattleInterface(IUnit unit1, IUnit unit2, Game game) throws FileNotFoundException {
        super();
        this.unit1 = unit1;
        this.unit2 = unit2;
        this.game = game;

        showBattle();
    }


    /**
     * Shows a new window for this battle
     * @throws FileNotFoundException
     */
    public static void showBattle() throws FileNotFoundException {
        stage.setTitle("Battle between "+unit1.getName()+" and "+unit2.getName());
        Group root = new Group();
        int width = 720;
        int height = 500;
        Scene scene = new Scene(root, width, height);

        var unit1Img = new ImageView(new Image(new FileInputStream(unit1.getIcon())));
        unit1Img.setX(100);
        unit1Img.setY(45);
        root.getChildren().add(unit1Img);
        String s1 = unit1.getName()+"\nHP="+unit1.getCurrentHP()+"\nATK="+unit1.getAtk()+"\nDEF="+unit1.getDef()+"\nEVD="+unit1.getEvd();
        Label unit1Label = new Label(s1);
        unit1Label.setLayoutX(100);
        unit1Label.setLayoutY(200);
        root.getChildren().add(unit1Label);

        var unit2Img = new ImageView(new Image(new FileInputStream(unit2.getIcon())));
        unit2Img.setX(550);
        unit2Img.setY(45);
        root.getChildren().add(unit2Img);
        String s2 = unit2.getName()+"\nHP="+unit2.getCurrentHP()+"\nATK="+unit2.getAtk()+"\nDEF="+unit2.getDef()+"\nEVD="+unit2.getEvd();
        Label unit2Label = new Label(s2);
        unit2Label.setLayoutX(550);
        unit2Label.setLayoutY(200);
        root.getChildren().add(unit2Label);

        root.getChildren().add(unit1EvadeButton());
        root.getChildren().add(unit2EvadeButton());
        root.getChildren().add(unit1DefendButton());
        root.getChildren().add(unit2DefendButton());

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates a button for unit2Defend
     */
    private static Node unit2DefendButton() {
        Button b = new Button(unit2.getName()+" defends");
        b.setLayoutY(420);
        b.setLayoutX(600);
        b.setOnAction(BattleInterface::unit2Defend);
        unit2buttons.add(b);
        return b;
    }

    /**
     * Sets unit 2's battle decision to defend
     * @param actionEvent
     */
    private static void unit2Defend(ActionEvent actionEvent) {
        unit2.setBattleDecision(BattleDecision.DEFEND);
        Iterator iterator = unit2buttons.iterator();
        while (iterator.hasNext()){
            Button next = (Button) iterator.next();
            next.setVisible(false);
        }
    }

    /**
     * Creates a button for unit1defend
     * @return
     */
    private static Node unit1DefendButton() {
        Button b = new Button(unit1.getName()+" defends");
        b.setLayoutY(420);
        b.setLayoutX(130);
        b.setOnAction(BattleInterface::unit1Defend);
        unit1buttons.add(b);
        return b;
    }

    /**
     * Sets unit1s battle decision to defend
     * @param actionEvent
     */
    private static void unit1Defend(ActionEvent actionEvent) {
        unit1.setBattleDecision(BattleDecision.DEFEND);
        Iterator iterator = unit1buttons.iterator();
        while (iterator.hasNext()){
            Button next = (Button) iterator.next();
            next.setVisible(false);
        }
    }

    /**
     * Creates a button for unit2evade
     */
    private static Node unit2EvadeButton() {
        Button b = new Button(unit2.getName()+" evades");
        b.setLayoutY(400);
        b.setLayoutX(600);
        b.setOnAction(BattleInterface::unit2Evade);
        unit2buttons.add(b);
        return b;
    }

    /**
     * Sets unit2s battle decision to evade
     * @param actionEvent
     */
    private static void unit2Evade(ActionEvent actionEvent) {
        unit2.setBattleDecision(BattleDecision.EVADE);
        Iterator iterator = unit2buttons.iterator();
        while (iterator.hasNext()){
            Button next = (Button) iterator.next();
            next.setVisible(false);
        }
    }

    /**
     * creates a button for unit1evade
     */
    private static Node unit1EvadeButton() {
        Button b = new Button(unit1.getName()+" evades");
        b.setLayoutY(400);
        b.setLayoutX(130);
        b.setOnAction(BattleInterface::unit1Evade);
        unit1buttons.add(b);
        return b;
    }

    /**
     * Sets unit1s battle decision to evade
     * @param actionEvent
     */
    private static void unit1Evade(ActionEvent actionEvent) {
        unit1.setBattleDecision(BattleDecision.EVADE);
        Iterator iterator = unit1buttons.iterator();
        while (iterator.hasNext()){
            Button next = (Button) iterator.next();
            next.setVisible(false);
        }
    }

}
