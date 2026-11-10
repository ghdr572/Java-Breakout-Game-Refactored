package com.zetcode;

public class Brick extends Sprite {

    private boolean destroyed;

    public Brick(int x, int y) {
        
        initBrick(x, y);
    }
    
    private void initBrick(int x, int y) {
        
        this.x = x;
        this.y = y;
        
        destroyed = false;

        loadImage();
        getImageDimensions();
    }
    
    private void loadImage() {
        // Flyweight: get the shared image instead of loading a new copy
       image = ImageFactory.getImage("Java-Breakout-Game-Refactored-main/src/resources/brick.png");
    }

    boolean isDestroyed() {
        
        return destroyed;
    }

    void setDestroyed(boolean val) {
        
        destroyed = val;
    }
}
