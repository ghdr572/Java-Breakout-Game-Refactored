package com.zetcode;

public class Ball extends Sprite {

    private int xdir;
    private int ydir;

    public Ball() {

        initBall();
    }

    private void initBall() {
        
        xdir = 1;
        ydir = -1;

        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() {
        // Flyweight: get the shared image instead of loading a new copy
       image = ImageFactory.getImage("Java-Breakout-Game-Refactored-main/src/resources/ball.png");
    }

   void move() {

        x += xdir;
        y += ydir;

        if (x == 0) {

            setXDir(1);
        }

        // استبدال Commons.WIDTH بـ GameConfig.getInstance().getWidth()
        if (x == GameConfig.getInstance().getWidth() - imageWidth) {

            System.out.println(imageWidth);
            setXDir(-1);
        }

        if (y == 0) {

            setYDir(1);
        }
    }

    private void resetState() {

        // استبدال ثوابت الموقع بالـ Singleton
        x = GameConfig.getInstance().getInitBallX();
        y = GameConfig.getInstance().getInitBallY();
    }
    void setXDir(int x) {

        xdir = x;
    }

    void setYDir(int y) {

        ydir = y;
    }

    int getYDir() {

        return ydir;
    }
}
