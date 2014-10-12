package org.whs.Tournament.Fighter;

import java.util.Random;

public class Fighter {
	public static final int DEFAULT_DIFFICULTY = 75;

	public static final int FIGHTER_TYPE_EMPTY = 0;
	public static final int FIGHTER_TYPE_HUMAN = 1;
	public static final int FIGHTER_TYPE_BOT = 2;

	protected String name;
	protected int difficulty;
	protected int type;
	protected int wins;
	protected int loses;

	public Fighter() {
		this.type = Fighter.FIGHTER_TYPE_EMPTY;
		this.name = "";
		this.difficulty = 0;
	}



	public String getName() {
		return this.name;
	}

	public int getType() {
		return this.type;
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
		System.out.println("Nombre:\t " + this.name );
		System.out.println("Dificultad:\t " + String.valueOf(getDifficulty()) + "\n");
	}

}
