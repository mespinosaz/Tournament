package org.whs.Tournament.Structure;

import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Fighter.FighterCollection;
import org.whs.Tournament.Fighter.HumanFighter;
import org.whs.Tournament.Fighter.NullFighter;
import org.whs.Tournament.Structure.Combat.Combat;

import java.util.Random;

public class CombatTree extends TournamentStructure {
	public static final String FINAL_ROUND_MESSAGE = "Final Round";

	private Fighter fighter = new NullFighter();
	private int maxDepth = 0;
	private CombatTree leftLeaf = null;
	private CombatTree rightLeaf = null;
	private int label;

	public CombatTree(FighterCollection participants) {
		setupTree(participants.size());
		addParticipants(participants);
	}

	private CombatTree(int depth, int horitzonalPosition) {
		initialize(depth,horitzonalPosition);
	}

	private void setupTree(int numberOfLeafs) {
		maxDepth = computeDepth(numberOfLeafs);
		initialize(maxDepth,1);
	}

	private void initialize(int depth, int horitzonalPosition) {
		maxDepth = depth;
		if (depth > 0) {
			setupLeafs(depth,horitzonalPosition);
		} else {
			label = horitzonalPosition;
		}
	}

	private void setupLeafs(int depth, int horitzonalPosition) {
		leftLeaf = new CombatTree(depth-1,horitzonalPosition);
		rightLeaf = new CombatTree(depth-1,horitzonalPosition + (int) Math.pow(2,depth)/2);
	}

	private int computeDepth(int numberOfNodes) {
		return (int)Math.ceil( Math.log(numberOfNodes) / Math.log(2) );
	}

	protected void addParticipants(FighterCollection participants) {
		participants = normalizeParticipants(participants);
		addNodes(participants);
		reverseTree();
	}

	private FighterCollection normalizeParticipants(FighterCollection participants) {
		participants.ensureCapacity((int)Math.pow(2,maxDepth));
		participants = fillSpotsWithEmptyValues(participants);

		return participants;
	}

	private FighterCollection fillSpotsWithEmptyValues(FighterCollection participants) {
		int numberOfParticipants = participants.size();
		for (int i=0; i < ((int)Math.pow(2,maxDepth) - numberOfParticipants); i++) {
			participants.add(new NullFighter());
		}

		return participants;
	}

	private CombatTree addNodes(FighterCollection participants) {
		if (isLeaf()) return null;
		if (childrenAreLeafs()) {
			setupLeafsFighters(participants);
		} else {
			propagateParticipants(participants);
		}

		return this;
	}

	private void setupLeafsFighters(FighterCollection participants) {
		int index = (leftLeaf.label-1)/2;
		Fighter leftFighter = participants.get(index);
		Fighter rightFighter = participants.get(index + participants.size()/2);
		leftLeaf.setFighter(leftFighter);
		rightLeaf.setFighter(rightFighter);
	}

	public void setFighter(Fighter theFighter) {
		fighter = theFighter;
	}

	private void propagateParticipants(FighterCollection participants) {
		leftLeaf = leftLeaf.addNodes(participants);
		rightLeaf = rightLeaf.addNodes(participants);
	}

	private boolean childrenAreLeafs() {
		return leftLeaf.isLeaf() && rightLeaf.isLeaf();
	}

	private CombatTree reverseTree() {
		if (!isLeaf()) {
			switchLeafs();
			propagateReverseTree();
		}
		return this;

	}

	private void switchLeafs() {
		CombatTree aux = leftLeaf;
		leftLeaf = rightLeaf;
		rightLeaf = aux;
	}

	private void propagateReverseTree() {
		leftLeaf.reverseTree();
		rightLeaf.reverseTree();
	}

	public boolean isLeaf() {
		return (leftLeaf == null & rightLeaf == null);
	}

	public void resolve() {
		int round = 1;
		while (noOneWon()) {
			resolveRound(round);
			round++;
		}
	}

	private void resolveRound(int round) {
		printRoundMessage(round);
		solveLeafCombats();
	}

	protected String getRoundMessage(int round) {
		if (childrenAreLeafs()) {
			return CombatTree.FINAL_ROUND_MESSAGE;
		}
		return TournamentStructure.ROUND_MESSAGE + " " + round;
	}

	private boolean noOneWon() {
		return fighter instanceof NullFighter;
	}

	public Fighter getWinner() {
		return fighter;
	}

	private void solveLeafCombats() {
		if (childrenAreLeafs()) {
			fighter = solveCurrentNodeCombat();
			unsetLeafs();
		} else {
			solveCurrentLeafsCombat();
		}
	}

	private void unsetLeafs() {
		leftLeaf = null;
		rightLeaf = null;
	}

	private Fighter solveCurrentNodeCombat() {
		Combat combat = new Combat(leftLeaf.getFighter(), rightLeaf.getFighter());
		return combat.resolve();
	}

	private void solveCurrentLeafsCombat() {
		leftLeaf.solveLeafCombats();
		rightLeaf.solveLeafCombats();
	}

	public Fighter getFighter() {
		return fighter;
	}
}
