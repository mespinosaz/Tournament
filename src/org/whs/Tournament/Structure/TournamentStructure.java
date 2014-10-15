package org.whs.Tournament.Structure;

import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Util.ConsoleApplication;

import java.util.ArrayList;

abstract public class TournamentStructure extends ConsoleApplication {
    public static final String ROUND_MESSAGE = "Round";

    abstract public void resolve();
    abstract public Fighter getWinner();
    abstract protected void addParticipants(ArrayList<Fighter> participants);

    public void revealWinner() {
        System.out.print("\n\n");
        String winnerName = getWinner().getName().toUpperCase();
        consoleOutput.slowText("The winner is........................... " + winnerName + "!!!!!!");
    }

    protected void printRoundMessage(int round) {
        String msg = getRoundMessage(round);
        consoleOutput.title(msg);
    }

    protected String getRoundMessage(int round) {
        return TournamentStructure.ROUND_MESSAGE + " " + round;
    }
}