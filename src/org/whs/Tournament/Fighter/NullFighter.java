package org.whs.Tournament.Fighter;

public class NullFighter extends Fighter {
    public NullFighter() {
        this.name = "";
        this.difficulty = 0;
    }

    public int getType() {
        return Fighter.FIGHTER_TYPE_EMPTY;
    }
}