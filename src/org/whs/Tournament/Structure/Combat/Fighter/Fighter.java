package org.whs.Tournament.Structure.Combat.Fighter;

import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Fighter {
	public static final int DEFAULT_DIFFICULTY = 75;

	public static final int FIGHTER_TYPE_EMPTY = 0;
	public static final int FIGHTER_TYPE_HUMAN = 1;
	public static final int FIGHTER_TYPE_BOT = 2;

	private String name;
	private int difficulty;
	private int type;
	private int wins;
	private int loses;

	public Fighter(String name) {
		this.name = name;
		this.difficulty = Fighter.DEFAULT_DIFFICULTY;
		this.type = Fighter.FIGHTER_TYPE_HUMAN;
	}

	public Fighter(int id, int difficulty) {
		this.name = "CPU" + Integer.toString(id);
		Random rand = new Random();
		this.difficulty = difficulty*20 + rand.nextInt(20) - 10;
		this.type = Fighter.FIGHTER_TYPE_BOT;
	}

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
