package com.zetcode;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

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
        
        var ii = new ImageIcon("src/resources/paddle.png");
        image = ii.getImage();        
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
