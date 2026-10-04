package com.zetcode;

public class GameObjectFactory {

    // Factory Method for creating game objects
     public Sprite createGameObject(String type, int x, int y) {

        if (type.equalsIgnoreCase("BALL")) {
            return new Ball();

        } else if (type.equalsIgnoreCase("PADDLE")) {
            return new Paddle();

        } else if (type.equalsIgnoreCase("BRICK")) {
            return new Brick(x, y);
        }

        return null;
    }
    
}
