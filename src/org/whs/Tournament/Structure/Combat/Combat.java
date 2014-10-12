package org.whs.Tournament.Structure.Combat;

import org.whs.Tournament.Structure.Combat.Fighter.Fighter;

import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;


public class Combat
{
	private Fighter fighter1;
	private Fighter fighter2;

	public Combat(Fighter fighter1, Fighter fighter2)
	{
		this.fighter1 = fighter1;
		this.fighter2 = fighter2;
	}

	public Fighter solveCombat()
	{
		if (this.fighter1.getType() == Fighter.FIGHTER_TYPE_EMPTY) {
			return this.fighter2;
		}

		if (this.fighter2.getType() == Fighter.FIGHTER_TYPE_EMPTY) {
			return this.fighter1;
		}
		if (this.fighter1.getType() == Fighter.FIGHTER_TYPE_BOT
			&& this.fighter2.getType() == Fighter.FIGHTER_TYPE_BOT) {
			return autoCombat();
		}

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			do {

				System.out.print("\n-----------------------\n"
					+ this.fighter1.getName() + "(" + String.valueOf(this.fighter1.getDifficulty())
					+ ") vs. "
					+ this.fighter2.getName() + "(" + String.valueOf(this.fighter2.getDifficulty())
					+ ")\n-----------------------\n[ 1. " +  this.fighter1.getName() + " | 2. " + this.fighter2.getName() + " | 3.Auto ]: ");

				switch(Integer.parseInt(in.readLine())) {
					case 1: return this.fighter1;
					case 2: return this.fighter2;
					case 3: return autoCombat();
				}
			}
			while (true);
		} catch (IOException e)  {
			e.printStackTrace();
		}
		return null;
	}

	public Fighter autoCombat() {
		if (this.fighter1.getType() == 0) return this.fighter2;
      	if (this.fighter2.getType() == 0) return this.fighter1;
		int totalDifficulty = this.fighter1.getDifficulty() + this.fighter2.getDifficulty();
		Random rand = new Random();
		int result = rand.nextInt(totalDifficulty);
		if (result < this.fighter1.getDifficulty()) return this.fighter1;
		return this.fighter2;
	}
}

