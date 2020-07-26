package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricliquid.Game;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessagePopUp {
    String msg;

    public MessagePopUp(String s){
        super();
        this.msg = s;
        show();
    }

    /**
     * Shows the message as a popup window
     */
    private void show() {
        Stage stage = new Stage();
        Group root = new Group();
        int width = 300;
        int height = 100;
        Scene scene = new Scene(root, width, height);
        Text text = new Text(msg);
        text.setX(15);
        text.setY(15);
        text.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
        root.getChildren().add(text);
        root.getChildren().add(okButton());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates a button for ok
     */
    private Node okButton() {
        Button b = new Button("OK");
        b.setLayoutX(80);
        b.setLayoutY(45);
        b.setOnAction(MessagePopUp::ok);
        return b;
    }

    /**
     * Closes the window
     * @param actionEvent
     */
    private static void ok(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
