package com.zetcode;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private Timer timer;
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean inGame = true;
    // Facade used to coordinate game cycle logic and collisions
    private GameControllerFacade controller;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        // استخدام الأبعاد من GameConfig
        setPreferredSize(new Dimension(
                GameConfig.getInstance().getWidth(),
                GameConfig.getInstance().getHeight()
        ));

        gameInit();
    }

    private void gameInit() {
        // استخدام عدد الطوب والـ Period من الـ Singleton
        bricks = new Brick[GameConfig.getInstance().getNumberOfBricks()];

        ball = (Ball) createGameObject("BALL", 0, 0);
        paddle = (Paddle) createGameObject("PADDLE", 0, 0);

        int k = 0;

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 6; j++) {

                bricks[k] = (Brick) createGameObject(
                   "BRICK",
                   j * 40 + 30,
                   i * 10 + 50
                );
                k++;
            }
        }

        // Initialize Facade with game entities
        controller = new GameControllerFacade(ball, paddle, bricks);

        timer = new Timer(GameConfig.getInstance().getPeriod(), new GameCycle());
        timer.start();
    }

    // Factory Method: Board creates its game objects through this method
    // instead of calling "new" directly in gameInit()
    protected Sprite createGameObject(String type, int x, int y) {

        if (type.equalsIgnoreCase("BALL")) {
            return new Ball();

        } else if (type.equalsIgnoreCase("PADDLE")) {
            return new Paddle();

        } else if (type.equalsIgnoreCase("BRICK")) {
            return new Brick(x, y);
        }

        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {

            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {

        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < GameConfig.getInstance().getNumberOfBricks(); i++) {

            if (!bricks[i].isDestroyed()) {

                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
                        bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {

        var font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);
        String message = controller.getStatusMessage();

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (GameConfig.getInstance().getWidth() - fontMetrics.stringWidth(message)) / 2,
                GameConfig.getInstance().getHeight() / 2);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            paddle.keyPressed(e);
        }
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {

        controller.updateMotion();
        if (!controller.checkCollisions()) {
            stopGame();
        }
        repaint();
    }

    private void stopGame() {

        inGame = false;
        timer.stop();
    }
}
