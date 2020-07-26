package com.github.cc3002.citricliquid.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Credits {

    public Credits(){
        super();
    }

    /**
     * Creates a button that opens a new window for the games credits
     */
    public Button getButton(){
        Button b = new Button("Credits");
        b.setLayoutX(10);
        b.setLayoutY(680);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage stage = new Stage();
                stage.setTitle("Credits");
                Group root = new Group();
                VBox box = new VBox();
                box.setSpacing(15);
                root.getChildren().add(box);
                int width = 600;
                int height = 200;
                Scene scene = new Scene(root, width, height);
                Label t1 = new Label("Game coded by Antonia Labarca Sanchez for CC3002");
                Label t2 = new Label("Base code by Ignacio Slater Munoz");
                Label t3= new Label("Character icons made by Freepik from www.flaticon.com");
                Label t4 = new Label("Board and panel icons made using Canva elements");
                Label t5= new Label("Background from Fruitbat Factory youtube channel, 100% Orange Juice Poppo March");
                box.getChildren().add(t1);
                box.getChildren().add(t2);
                box.getChildren().add(t3);
                box.getChildren().add(t4);
                box.getChildren().add(t5);
                stage.setScene(scene);
                stage.show();
            }
        });
        return b;
    }
}
