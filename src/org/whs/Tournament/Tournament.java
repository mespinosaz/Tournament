package org.whs.Tournament;

import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Fighter.HumanFighter;
import org.whs.Tournament.Fighter.RandomBot;
import org.whs.Tournament.Structure.TournamentStructure;
import org.whs.Tournament.Structure.CombatTree;
import org.whs.Tournament.Structure.League;
import org.whs.Tournament.Util.ConsoleApplication;
import org.whs.Tournament.Util.UserInputReader;

import java.util.ArrayList;
import java.util.Collections;

public class Tournament {
	public static final String NUMBER_OF_PARTICIPANTS_INPUT_MESSAGE = "Number of participants";
	public static final String NUMBER_OF_PLAYERS_INPUT_MESSAGE = "Number of players";
	public static final String NAME_OF_PLAYER_INPUT_MESSAGE = "Name of player";
	public static final int OPTION_COMBAT_TREE = 1;
	public static final int OPTION_LEAGUE = 2;
	public static final String TOURNAMENT_TYPE_INPUT_MESSAGE = "\nType\n"
				+ "----\n"
				+ "1. Tournament Tree\n"
				+ "2. League\n"
				+ "---------\n"
				+ "<1/2>";

	private ArrayList<Fighter> participants = new ArrayList<Fighter>();
	private int numberOfParticipants = 0;
	private int numberOfHumanPlayers = 0;
	private int tournamentType;
	private TournamentStructure tournament;

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

	private void addRandomBots(int numberOfBots) {
		for (int i=1;i<=numberOfBots;i++) {
			addRandomBot(i);
		}
	}

	private void addRandomBot(int id) {
		RandomBot bot = new RandomBot(id);
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
		Fighter newFighter = new HumanFighter(name);
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
			case Tournament.OPTION_COMBAT_TREE:
				setupCombatTree();
				break;
			case Tournament.OPTION_LEAGUE:
				setupLeague();
				break;
		}
	}

	private void setupCombatTree() {
		tournament = new CombatTree(participants);
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
