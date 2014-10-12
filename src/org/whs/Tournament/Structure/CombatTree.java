package org.whs.Tournament.Structure;

import org.whs.Tournament.Fighter.Fighter;

import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class CombatTree extends TournamentStructure {
	public Fighter fighter = new Fighter();
	int maxDepth = 0;
	public CombatTree leftLeaf = null;
	public CombatTree rightLeaf = null;
	int label = 0;

	public CombatTree() {

	}

	public CombatTree(int depth) {
		initialize(depth,1);
	}

	public CombatTree(int depth, int horitzonalPosition) {
		initialize(depth,horitzonalPosition);
	}

	private void initialize (int depth, int horitzonalPosition) {
		maxDepth = depth;
		if (depth > 0) {
			leftLeaf = new CombatTree(depth-1,horitzonalPosition);
			rightLeaf = new CombatTree(depth-1,horitzonalPosition + (int) Math.pow(2,depth)/2);
		} else {
			label = horitzonalPosition;
		}
	}

	public static int computeDepth(int numberOfNodes) {
		return (int)Math.ceil( Math.log(numberOfNodes) / Math.log(2) );
	}

	public void addParticipants(ArrayList<Fighter> participants) {
		participants.ensureCapacity((int)Math.pow(2,maxDepth));
		int numberOfParticipants = participants.size();
		for (int i=0; i < ((int)Math.pow(2,maxDepth) - numberOfParticipants); i++) {
			participants.add(new Fighter());
		}
		addNodes(participants);
		reverse();
	}

	public CombatTree addNodes(ArrayList<Fighter> flist) {
		int index=0;
		if (isLeaf()) return null;
		if (leftLeaf.isLeaf() && rightLeaf.isLeaf()) {
			index = (leftLeaf.label-1)/2;
			leftLeaf.fighter = flist.get(index);
			rightLeaf.fighter = flist.get(index + flist.size()/2);
		} else {
			leftLeaf = leftLeaf.addNodes(flist);
			rightLeaf = rightLeaf.addNodes(flist);
		}

		return this;
	}

	public int addNode(CombatTree c) {
		if (leftLeaf == null) {
			leftLeaf = c;
			return 1;
		}
		if (rightLeaf == null) {
			rightLeaf = c;
			return 2;
		}
		return -1;
	}

	public void addNode(CombatTree c, int i) {
		switch(i) {
			case 1: leftLeaf = c; break;
			case 2: rightLeaf = c; break;
		}
	}

	public CombatTree reverse() {
		CombatTree aux = leftLeaf;
		if (!isLeaf()) {
			leftLeaf = rightLeaf;
			rightLeaf = aux;
			leftLeaf.reverse();
			rightLeaf.reverse();
		}
		return this;

	}

	public boolean isLeaf() {
		if (leftLeaf == null & rightLeaf == null) return true;
		return false;
	}

	public Fighter returnFighter() { //Deprecated
		Fighter a1, a2;
		if (leftLeaf.isLeaf()) a1 = leftLeaf.fighter;
		else a1 = leftLeaf.returnFighter();
		if (rightLeaf.isLeaf()) a2 = rightLeaf.fighter;
		else a2 = rightLeaf.returnFighter();
		return solveCombat(a1,a2);
	}

	public void resolve() {
		int round = 1;
		while (this.fighter.getType() == 0) {
			String msg = "";
			if (this.leftLeaf.isLeaf() && this.rightLeaf.isLeaf()) msg = "Final Round";
			else msg = "Round " + round;
			this.consoleOutput.title(msg);
			this.solveLeafCombats();
			round++;
		}
	}

	public Fighter getWinner() {
		return this.fighter;
	}

	public void solveLeafCombats() {
		if (leftLeaf.isLeaf() && rightLeaf.isLeaf()) {
			fighter = solveCombat(leftLeaf.fighter,rightLeaf.fighter);
			leftLeaf = null;
			rightLeaf = null;
		} else {
			leftLeaf.solveLeafCombats();
			rightLeaf.solveLeafCombats();
		}
	}

	public Fighter solveCombat(Fighter f1, Fighter f2) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int x=0;
		if (f1.getType() == 0) return f2;
		if (f2.getType() == 0) return f1;
		if (f1.getType() == 2 && f2.getType() == 2) return autoCombat(f1,f2);
		try {
			do {
				System.out.print("\n-----------------------\n"
					+ f1.getName() + "(" + String.valueOf(f1.getDifficulty())
					+ ") vs. "
					+ f2.getName() + "(" + String.valueOf(f2.getDifficulty())
					+ ")\n-----------------------\n[ 1. " +  f1.getName() + " | 2. " + f2.getName() + " | 3.Auto ]: ");

				switch(Integer.parseInt(in.readLine())) {
					case 1: return f1;
					case 2: return f2;
					case 3: return autoCombat(f1,f2);
				}
			}
			while (true);
		} catch (IOException e)  {
			e.printStackTrace();
		}
		return null;
	}

	public Fighter autoCombat(Fighter f1, Fighter f2) {
		if (f1.getType() == 0) return f2;
      	if (f2.getType() == 0) return f1;
		int totalDifficulty = f1.getDifficulty() + f2.getDifficulty();
		Random rand = new Random();
		int result = rand.nextInt(totalDifficulty);
		if (result < f1.getDifficulty()) return f1;
		return f2;
	}
}
