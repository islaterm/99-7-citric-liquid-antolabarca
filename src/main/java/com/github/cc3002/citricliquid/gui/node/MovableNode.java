package com.github.cc3002.citricliquid.gui.node;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class represents an object that can be moved inside the game.
 *
 * @author Ignacio Slater Muñoz
 * @version 3.0b9
 * @since 3.0
 */
public class MovableNode {
    private final int imgHeight;
    private final int imgWidth;
    private ImageView sprite;
    private int xPos;
    private int yPos;
    private int moveDistance;

    /**
     * Creates a new movable component from an image.
     */
    public MovableNode(final @NotNull Scene scene, final int xPos, final int yPos,
                       final int imgHeight, final int imgWidth, final String spritePath)
            throws FileNotFoundException {
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
        this.xPos = xPos;
        this.yPos = yPos;
        this.moveDistance = 68;
        addSprite(spritePath);
        scene.setOnKeyPressed(this::setKeyboardTriggers);
        startAnimator();
    }

    private void addSprite(final String spritePath) throws FileNotFoundException {
        FileInputStream spriteImage = new FileInputStream(spritePath);
        sprite = new ImageView(new Image(spriteImage));
        sprite.setX(xPos);
        sprite.setY(yPos);
        sprite.setFitWidth(imgWidth);
        sprite.setFitHeight(imgHeight);
    }

    private void startAnimator() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                sprite.setX(xPos);
                sprite.setY(yPos);
            }
        };
        timer.start();
    }

    /**
     * Moves the component down.
     */
    public void moveDown(int verticalDistance) {
        move(0, verticalDistance);
    }

    /**
     * Moves the component up.
     */
    public void moveUp(int verticalDistance) {
        move(0, -verticalDistance);
    }

    /**
     * Moves the component down.
     */
    public void moveRight(int horizontalDistance) {
        move(horizontalDistance, 0);
    }

    /**
     * Moves the component up.
     */
    public void moveLeft(int horizontalDistance) {
        move(-horizontalDistance, 0);
    }

    /**
     * Moves the component to a new position
     *
     * @param horizontalDistance
     *     the horizontal movement
     * @param verticalDistance
     *     the vertical movement
     */
    private void move(int horizontalDistance, int verticalDistance) {
        xPos += horizontalDistance;
        yPos += verticalDistance;
    }

    /**
     * Moves the component to a specific position
     * @param x
     *          the new x coordenate
     * @param y
     *          the new y coordenate
     */
    public void moveTo(int x, int y){
        xPos = x;
        yPos = y;
    }

    public ImageView getNode() {
        return sprite;
    }

    private void setKeyboardTriggers(@NotNull KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                moveUp(moveDistance);
                break;
            case DOWN:
                moveDown(moveDistance);
                break;
            case RIGHT:
                moveRight(moveDistance);
                break;
            case LEFT:
                moveLeft(moveDistance);
                break;
            default:
                break;
        }
    }
}
