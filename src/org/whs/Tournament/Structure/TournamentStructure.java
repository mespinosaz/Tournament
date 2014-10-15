package org.whs.Tournament.Structure;

import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Util.ConsoleApplication;

import java.util.ArrayList;

abstract public class TournamentStructure extends ConsoleApplication {
    abstract public void resolve();
    abstract public Fighter getWinner();
    abstract protected void addParticipants(ArrayList<Fighter> participants);

    public void revealWinner() {
        System.out.print("\n\n");
        String winnerName = getWinner().getName().toUpperCase();
        consoleOutput.slowText("The winner is........................... " + winnerName + "!!!!!!");
    }
}