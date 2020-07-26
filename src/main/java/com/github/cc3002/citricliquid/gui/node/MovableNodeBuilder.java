package com.github.cc3002.citricliquid.gui.node;

import com.github.cc3002.citricjuice.model.unit.Player;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class MovableNodeBuilder {
    private final Scene scene;
    private int xPos;
    private int yPos;
    private int height;
    private int width;
    private String imagePath;

    public MovableNodeBuilder(Scene scene) {
        this.scene = scene;
    }

    public MovableNodeBuilder setPosition(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        return this;
    }

    public MovableNodeBuilder setSize(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }

    public MovableNodeBuilder setImagePath(String path) {
        this.imagePath = path;
        return this;
    }

    public MovableNode build() throws FileNotFoundException {
        return new MovableNode(scene, xPos, yPos, height, width, imagePath);
    }
}
