package org.whs.Tournament;

import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Structure.TournamentStructure;
import org.whs.Tournament.Structure.CombatTree;
import org.whs.Tournament.Structure.League;
import org.whs.Tournament.Util.ConsoleApplication;
import org.whs.Tournament.Util.UserInputReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Tournament {
	public static final String NUMBER_OF_PARTICIPANTS_INPUT_MESSAGE = "Number of participants";
	public static final String NUMBER_OF_PLAYERS_INPUT_MESSAGE = "Number of players";
	public static final String NAME_OF_PLAYER_INPUT_MESSAGE = "Name of player";
	public static final String TOURNAMENT_TYPE_INPUT_MESSAGE = "\nTipo\n"
				+ "----\n"
				+ "1. Torneo\n"
				+ "2. Liga\n"
				+ "---------\n"
				+ "<1/2>";

	private ArrayList<Fighter> participants = new ArrayList<Fighter>();
	int numberOfParticipants = 0;
	int numberOfHumanPlayers = 0;
	int tournamentType;
	TournamentStructure tournament;

	public static void main(String[] args) {
		new Tournament();
	}
	public Tournament() {
		setupParticipants();
		setupTournament();
		resolveTournament();
	}

	private void setupParticipants() {
		processUserInput();
		fillSpotsLeftWithBots();
		Collections.shuffle(participants);
	}

	private void processUserInput() {
		processNumberOfParticipants();
		processNumberOfHumanPlayers();
		processNameOfHumanPlayers();
	}

	private void fillSpotsLeftWithBots() {
		int spotsLeft = numberOfParticipants-numberOfHumanPlayers;
		if (spotsLeft <= 0) {
			return;
		}
		addRandomBots(spotsLeft);
	}

	private void addRandomBots(int spotsLeft) {
		for (int i=1;i<=spotsLeft;i++) {
			addRandomBot(i);
		}
	}

	private void addRandomBot(int id) {
		Random rand = new Random();
		int botLevel = rand.nextInt(5) + 1;
		Fighter bot = new Fighter(id,botLevel);
		addParticipant(bot);
	}

	private void processNumberOfParticipants() {
		captureNumberOfParticipants();
		participants = new ArrayList<Fighter>(numberOfParticipants);
	}

	private void captureNumberOfParticipants() {
		UserInputReader input = getUserInput(Tournament.NUMBER_OF_PARTICIPANTS_INPUT_MESSAGE);
		numberOfParticipants = input.captureInteger();
	}

	private void captureNumberOfHumanPlayers() {
		UserInputReader input = getUserInput(Tournament.NUMBER_OF_PLAYERS_INPUT_MESSAGE);
		numberOfHumanPlayers = input.captureInteger();
	}
	private void processNumberOfHumanPlayers() {
		do {
			captureNumberOfHumanPlayers();
		} while(numberOfParticipants < numberOfHumanPlayers);
	}

	private void processNameOfHumanPlayers() {
		for (int i=1;i<=numberOfHumanPlayers;i++) {
			addNewPlayer(i);
		}
	}

	private void addNewPlayer(int id) {
		UserInputReader input = getUserInput(
			Tournament.NAME_OF_PLAYER_INPUT_MESSAGE
			+ " "
			+ Integer.toString(id)
		);
		String name = input.captureString();
		Fighter newFighter = new Fighter(name);
		addParticipant(newFighter);
	}

	private void addParticipant(Fighter fighter) {
		participants.add(fighter);
	}

	private UserInputReader getUserInput(String message) {
		UserInputReader input = new UserInputReader(message);
		input.setMessagePrepend(": ");

		return input;
	}

	private void setupTournament() {
		processTournamentType();
		switch(tournamentType) {
			case 1:
				setupCombatTree();
				break;
			case 2:
				setupLeague();
				break;
		}
	}

	private void setupCombatTree() {
		int depth = CombatTree.computeDepth(participants.size());
		tournament = new CombatTree(depth);
		tournament.addParticipants(participants);
	}

	private void setupLeague() {
		tournament = new League(participants);
	}

	private void processTournamentType() {
		do {
			captureTournamentType();
		} while (tournamentType > 2 || tournamentType < 1);
	}

	private void captureTournamentType() {
		UserInputReader input = getUserInput(Tournament.TOURNAMENT_TYPE_INPUT_MESSAGE);
		tournamentType = input.captureInteger();
	}

	private void resolveTournament() {
		tournament.resolve();
		tournament.revealWinner();
	}
}
