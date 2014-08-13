import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class League {
	ArrayList<Fighter> fighters;
	Fighter winner;
	int rounds = 1;

	public League(ArrayList<Fighter> flist) {
		fighters = flist;
	}

	public void start() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Combat> clist = new ArrayList<Combat>();
		for (int i=0;i<fighters.size();i++) {
			for(int j=0;j<(fighters.size()-i-1);j++) {
				switch(j%2) {
					case 0:
						clist.add(new Combat(fighters.get(j),fighters.get(j+i+1)));
						break;
					case 1:
						clist.add(new Combat(fighters.get(j+i+1),fighters.get(j)));
						break;
				}
			}
		}
		try {
			System.out.print("Numero de rondas: ");
			rounds = Integer.parseInt(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0;i<rounds;i++) {
			System.out.println("\n----------");
			System.out.println("Ronda" + Integer.toString(i+1));
			System.out.print("----------");
			Collections.shuffle(clist);
			for(int j=0;j<clist.size();j++) fighters.get(fighters.indexOf(clist.get(j).solveCombat())).wins++;
		}
		winSort(fighters,0,fighters.size()-1);
		Collections.reverse(fighters);
		winner = fighters.get(0);
		System.out.println("\n----\nWins\n----");
		for(int n=0;n<fighters.size();n++) System.out.println(Integer.toString(n+1) + ". " + fighters.get(n).name + " - " + fighters.get(n).wins);
		System.out.println("----------");
	}

	public Fighter getWinner() {
		return winner;
	}

	public void winSort(ArrayList<Fighter> f, int first, int last){
    	int i=first;
		int j=last;
    	int pivote=(f.get(last).wins+f.get(first).wins)/2;
    	Fighter aux;
 
    	do{
    		while(f.get(i).wins<pivote) i++;    		
    		while(f.get(j).wins>pivote) j--;
 
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

