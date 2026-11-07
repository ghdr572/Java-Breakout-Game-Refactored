package com.zetcode;

import java.awt.Point;

// Facade: provides a unified interface to handle object movement and collision logic
public class GameControllerFacade {

    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private String statusMessage = "Game Over";

    public GameControllerFacade(Ball ball, Paddle paddle, Brick[] bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void updateMotion() {
        ball.move();
        paddle.move();
    }

    // Handles all collision checks and returns false if the game has ended
    public boolean checkCollisions() {
        // استخدام القاع السفلي من الـ Singleton
        if (ball.getRect().getMaxY() > GameConfig.getInstance().getBottomEdge()) {
            return false;
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {

                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {

                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {

                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {

                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {

                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        for (int i = 0, j = 0; i < GameConfig.getInstance().getNumberOfBricks(); i++) {

            if (bricks[i].isDestroyed()) {

                j++;
            }

            if (j == GameConfig.getInstance().getNumberOfBricks()) {

                statusMessage = "Victory";
                return false;
            }
        }

        for (int i = 0; i < GameConfig.getInstance().getNumberOfBricks(); i++) {

            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {

                    if (bricks[i].getRect().contains(pointRight)) {

                        ball.setXDir(-1);
                    } else if (bricks[i].getRect().contains(pointLeft)) {

                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {

                        ball.setYDir(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {

                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
                }
            }
        }

        return true;
    }
}