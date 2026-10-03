package com.zetcode;

public class GameConfig {

    // 1. متغير ستاتيك يحفظ النسخة الوحيدة في الذاكرة
    private static GameConfig instance;

    // ثوابت اللعبة
    private final int width = 300;
    private final int height = 400;
    private final int bottomEdge = 390;
    private final int numberOfBricks = 30;
    private final int initPaddleX = 200;
    private final int initPaddleY = 360;
    private final int initBallX = 230;
    private final int initBallY = 355;
    private final int period = 10;

    // 2. Private Constructor لمنع إنشاء كائنات جديدة من الخارج
    private GameConfig() {}

    // 3. Public Static Method للوصول للنسخة الوحيدة
    public static synchronized GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    // Getters للوصول للقيم
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getBottomEdge() { return bottomEdge; }
    public int getNumberOfBricks() { return numberOfBricks; }
    public int getInitPaddleX() { return initPaddleX; }
    public int getInitPaddleY() { return initPaddleY; }
    public int getInitBallX() { return initBallX; }
    public int getInitBallY() { return initBallY; }
    public int getPeriod() { return period; }
}