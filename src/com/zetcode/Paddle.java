package com.zetcode;

import java.awt.event.KeyEvent;

public class Paddle extends Sprite  {

    private int dx;

    public Paddle() {
        
        initPaddle();        
    }
    
    private void initPaddle() {

        loadImage();
        getImageDimensions();

        resetState();
    }
    
    private void loadImage() {
        // Flyweight: get the shared image instead of loading a new copy
       image = ImageFactory.getImage("Java-Breakout-Game-Refactored-main/src/resources/paddle.png");        
    }    

   void move() {

        x += dx;

        if (x <= 0) {

            x = 0;
        }

        // استبدال Commons.WIDTH
        if (x >= GameConfig.getInstance().getWidth() - imageWidth) {

            x = GameConfig.getInstance().getWidth() - imageWidth;
        }
    }

    void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 1;
        }
    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }

    private void resetState() {

        // استبدال ثوابت الموقع بالـ Singleton
        x = GameConfig.getInstance().getInitPaddleX();
        y = GameConfig.getInstance().getInitPaddleY();
    }
}
