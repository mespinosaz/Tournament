package org.whs.Tournament.Fighter;

public class HumanFighter extends Fighter {
    public HumanFighter(String fighterName) {
        name = fighterName;
        difficulty = Fighter.DEFAULT_DIFFICULTY;
    }

    public int getType() {
        return Fighter.FIGHTER_TYPE_HUMAN;
    }
}