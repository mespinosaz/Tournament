package org.whs.Tournament.Structure;

import org.whs.Tournament.Fighter.Fighter;
import org.whs.Tournament.Structure.Combat.Combat;
import org.whs.Tournament.Util.UserInputReader;

import java.util.ArrayList;
import java.util.Collections;

public class League extends TournamentStructure {
    public static final int EVEN_ROUND = 0;
    public static final int ODD_ROUND = 1;
    public static final String WINS_MESSAGE = "Wins";
    public static final String NUMBER_OF_ROUNDS_MESSAGE = "Number of rounds";

	private ArrayList<Fighter> fighters;
    private ArrayList<Combat> combats;
	private Fighter winner;
	private int numberOfRounds;

	public League(ArrayList<Fighter> participants) {
		addParticipants(participants);
        initialize();
	}

    protected void addParticipants(ArrayList<Fighter> participants) {
        fighters = participants;
    }

    private void initialize() {
        setupCombats();
        captureNumberOfRounds();
    }

    private void setupCombats() {
        int numberOfPlayersNotConfronted;
        combats = new ArrayList<Combat>();
        for (int i=0;i<fighters.size();i++) {
            numberOfPlayersNotConfronted = fighters.size()-i-1;
            for(int j=0;j<numberOfPlayersNotConfronted;j++) {
                addCombat(fighters.get(j),fighters.get(j+i+1),j%2);
            }
        }
    }

    private void addCombat(Fighter fighterOne, Fighter fighterTwo, int roundType) {
        Combat newCombat = new Combat(fighterOne,fighterTwo);
        if (roundType == League.ODD_ROUND) {
            newCombat.reverse();
        }
        combats.add(newCombat);
    }

    private void captureNumberOfRounds() {
        UserInputReader input = new UserInputReader(League.NUMBER_OF_ROUNDS_MESSAGE);
        input.setMessagePrepend(": ");
        numberOfRounds = input.captureInteger();
    }

	public void resolve() {
		for(int round=1;round<=numberOfRounds;round++) {
            Collections.shuffle(combats);
			printRoundMessage(round);
			for(int combatIndex=0;combatIndex<combats.size();combatIndex++) {
                resolveCombatFromTheList(combatIndex);
            }
		}
		sortFightersByWins();
		winner = fighters.get(0);
		drawClassification();
	}

    private void resolveCombatFromTheList(int combatIndex) {
        Combat theCombat = combats.get(combatIndex);
        Fighter theWinner = theCombat.resolve();
        theWinner.addWin();
    }

	public Fighter getWinner() {
		return winner;
	}

    private void drawClassification() {
        consoleOutput.title(League.WINS_MESSAGE);
        for(int n=0;n<fighters.size();n++) {
            drawFighterPointsAtPosition(n);
        }
        consoleOutput.horitzontalLine(8);
    }

    private void drawFighterPointsAtPosition(int position) {
        Fighter theFighter = getFighterAtPosition(position);
        String msg = Integer.toString(position+1)
            + ". "
            + theFighter.getName()
            + " - "
            + theFighter.getWins();

        consoleOutput.print(msg);
    }

    private Fighter getFighterAtPosition(int index) {
        return fighters.get(index);
    }

    private void sortFightersByWins() {
        winSort(fighters,0,fighters.size()-1);
        Collections.reverse(fighters);
    }

    // This needs to be removed
	public void winSort(ArrayList<Fighter> f, int first, int last) {
    	int i=first;
		int j=last;
    	int pivote=(f.get(last).getWins()+f.get(first).getWins())/2;
    	Fighter aux;

    	do{
    		while(f.get(i).getWins()<pivote) i++;
    		while(f.get(j).getWins()>pivote) j--;

    		if (i<=j){
    			aux=f.get(j);
				f.set(j,f.get(i));
    			f.set(i,aux);
    			i++;
    			j--;
    		}

    	} while (i<=j);

    	if(first<j) winSort(f,first, j);
    	if(last>i) winSort(f,i, last);
   }
}

