package org.whs.Tournament.Fighter;

import java.util.Random;

abstract public class Fighter {
	public static final int DEFAULT_DIFFICULTY = 75;

	public static final int FIGHTER_TYPE_EMPTY = 0;
	public static final int FIGHTER_TYPE_HUMAN = 1;
	public static final int FIGHTER_TYPE_BOT = 2;

	public static final String FIGHTER_NAME = "Name";
	public static final String FIGHTER_DIFFICULTY = "Difficulty";

	protected String name;
	protected int difficulty;
	protected int wins;
	protected int loses;

	public String getName() {
		return this.name;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public int getWins() {
		return this.wins;
	}

	public int getLoses() {
		return this.loses;
	}

	public void addWin() {
		this.wins++;
	}

	public void addLose() {
		this.loses++;
	}

	public void print() {
		System.out.println(Fighter.FIGHTER_NAME + ":\t " + this.name );
		System.out.println(Fighter.FIGHTER_DIFFICULTY + ":\t " + String.valueOf(getDifficulty()) + "\n");
	}

	abstract public int getType();

}
