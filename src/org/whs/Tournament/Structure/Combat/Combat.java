package org.whs.Tournament.Structure.Combat;

import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Fighter.HumanFighter;
import org.whs.Tournament.Fighter.NullFighter;
import org.whs.Tournament.Util.ConsoleApplication;
import org.whs.Tournament.Util.UserInputReader;

import java.util.Random;

public class Combat extends ConsoleApplication {
	public static final int COMBAT_SLOT_ONE = 0;
	public static final int COMBAT_SLOT_TWO = 1;

	private Fighter fighterOne;
	private Fighter fighterTwo;

	public Combat(Fighter newfighterOne, Fighter newfighterTwo) {
		fighterOne = newfighterOne;
		fighterTwo = newfighterTwo;
	}

	public void reverse() {
		Fighter tempFighter = fighterOne;
		fighterTwo = fighterOne;
		fighterOne = tempFighter;
	}

	public Fighter resolve() {
		if (fightersAreNotHumanOrOneIsEmpty()) {
			return solveCombatAutomatically();
		}

		return solveCombat();
	}

	private Fighter solveCombat() {
		showCombat();
		return captureWinner();

	}
	private void showCombat() {
		String title = fighterOne.getName()
			+ "("
			+ String.valueOf(fighterOne.getDifficulty())
			+ ") vs. "
			+ fighterTwo.getName()
			+ "("
			+ String.valueOf(fighterTwo.getDifficulty())
			+ ")";
		consoleOutput.title(title);
	}

	private Fighter captureWinner() {
		String options = "[ 1. " +  fighterOne.getName()
			+ " | 2. " + fighterTwo.getName()
			+ " | 3.Auto ]";
		UserInputReader input = new UserInputReader(options);
		int option;
		do {
        	input.setMessagePrepend(": ");
	    	option = input.captureInteger();
			switch(option) {
				case 1: return fighterOne;
				case 2: return fighterTwo;
				case 3: return solveCombatAutomatically();
			}
		}
		while (true);
	}

	private boolean fightersAreNotHumanOrOneIsEmpty() {
		return fightersAreNotHuman() ||
			isAtleastOneFighterEmpty();
	}

	private boolean fightersAreNotHuman() {
		return !(fighterOne instanceof HumanFighter)
			&& !(fighterTwo instanceof HumanFighter);
	}

	private boolean isAtleastOneFighterEmpty() {
		return fighterOne instanceof NullFighter
			|| fighterTwo instanceof NullFighter;
	}

	private Fighter solveCombatAutomatically() {
		if (fighterOne instanceof NullFighter) return fighterTwo;
      	if (fighterTwo instanceof NullFighter) return fighterOne;

		return getWinnerBasedOnDifficulty();
	}

	private Fighter getWinnerBasedOnDifficulty() {
		int totalDifficulty = fighterOne.getDifficulty() + fighterTwo.getDifficulty();
		Random rand = new Random();
		int result = rand.nextInt(totalDifficulty);
		if (result < fighterOne.getDifficulty()) return fighterOne;
		return fighterTwo;
	}
}

