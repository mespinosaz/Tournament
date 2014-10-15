package org.whs.Tournament.Fighter;

import java.util.Random;

public class RandomBot extends Bot {
    public RandomBot(int id) {
        super(id);
        int botLevel = randomizer.nextInt(5) + 1;
        setLevel(botLevel);
    }
}