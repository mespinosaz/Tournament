package org.whs.Tournament.Structure.Combat;

import org.whs.Tournament.Fighter.Fighter;

import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;


public class Combat
{
	private Fighter fighter1;
	private Fighter fighter2;

	public Combat(Fighter newFighter1, Fighter newFighter2)
	{
		fighter1 = newFighter1;
		fighter2 = newFighter2;
	}

	public Fighter resolve()
	{
		if (fighter1.getType() == Fighter.FIGHTER_TYPE_EMPTY) {
			return fighter2;
		}

		if (fighter2.getType() == Fighter.FIGHTER_TYPE_EMPTY) {
			return fighter1;
		}
		if (fighter1.getType() == Fighter.FIGHTER_TYPE_BOT
			&& fighter2.getType() == Fighter.FIGHTER_TYPE_BOT) {
			return autoCombat();
		}

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			do {

				System.out.print("\n-----------------------\n"
					+ fighter1.getName() + "(" + String.valueOf(fighter1.getDifficulty())
					+ ") vs. "
					+ fighter2.getName() + "(" + String.valueOf(fighter2.getDifficulty())
					+ ")\n-----------------------\n[ 1. " +  fighter1.getName() + " | 2. " + fighter2.getName() + " | 3.Auto ]: ");

				switch(Integer.parseInt(in.readLine())) {
					case 1: return fighter1;
					case 2: return fighter2;
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
		if (fighter1.getType() == 0) return fighter2;
      	if (fighter2.getType() == 0) return fighter1;
		int totalDifficulty = fighter1.getDifficulty() + fighter2.getDifficulty();
		Random rand = new Random();
		int result = rand.nextInt(totalDifficulty);
		if (result < fighter1.getDifficulty()) return fighter1;
		return fighter2;
	}
}

