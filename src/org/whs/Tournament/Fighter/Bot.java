package org.whs.Tournament.Fighter;

import java.util.Random;

public class Bot extends Fighter {
    private static final int DEFAULT_LEVEL = 3;
    protected static final String BOT_NAME_PREFIX = "CPU";

    protected Random randomizer = new Random();

    public Bot(int id, int level) {
        name = createName(id);
        setLevel(level);
    }

    public Bot (int id) {
        name = createName(id);
        setLevel(Bot.DEFAULT_LEVEL);
    }

    protected String createName(int id) {
        return Bot.BOT_NAME_PREFIX + Integer.toString(id);
    }

    public int getType() {
        return Fighter.FIGHTER_TYPE_BOT;
    }
    public void setLevel(int level) {
        setDifficulty(level);
    }

    public void setDifficulty(int level) {
        difficulty = level*20 + randomizer.nextInt(20) - 10;
    }
}