import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Tournament {
	ArrayList<Fighter> flist = new ArrayList<Fighter>();
	int n;
	int depth;
	CombatTree ct;
	League l;
	Fighter winner;
	int option;
	public static void main(String[] args) {
		new Tournament();
	}
	public Tournament() {
		init();
		start();
		begin();
	}
	
	public void init() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int h=0,d;
		String name;
		try {
			System.out.print("Numero de participantes: ");
			n = Integer.parseInt(in.readLine());
			flist = new ArrayList<Fighter>(n);
			System.out.print("Numero de jugadores(<=" + n + "): ");
   	   h = Integer.parseInt(in.readLine());
			for (int i=0;i<h;i++) {
				System.out.print("Nombre de jugador " + Integer.toString(i+1) + ": ");
				name = in.readLine();
				flist.add(new Fighter(name));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ((n-h) > 0) {
			Random rand = new Random();
			for (int i=0;i<(n-h);i++) {
				d = rand.nextInt(5) + 1;
				flist.add(new Fighter(i+1,d));
			}
		}
		Collections.shuffle(flist);
	}

	public void start() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			do {
				System.out.println("\nTipo");
				System.out.println("----");
				System.out.print("1. Torneo\n2. Liga\n---------\n<1/2>: ");
				option = Integer.parseInt(in.readLine());
			} while (option > 2 || option < 1);
			switch(option) {
				case 1:
					depth = (int) Math.ceil(Math.log(n)/Math.log(2));
					ct = new CombatTree(depth,1);
					flist.ensureCapacity((int)Math.pow(2,depth));
					for (int i=0; i < ((int)Math.pow(2,depth) - n); i++) flist.add(new Fighter());
					ct.initialize(flist);
					ct.reverse();
					break;
				case 2:
					l = new League(flist);
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void begin() {
		int round = 1;
		String msg = "";
		switch (option) {
			case 1:
				while (ct.fighter.type == 0) {
					if (ct.c1.isLeaf() && ct.c2.isLeaf()) msg = "Final Round";
					else msg = "Round " + round;
					titulo(msg);		
					ct.solveLeafCombats();
					round++;
				}
				winner = ct.fighter;
				break;
			case 2:
				l.start();
				winner = l.getWinner();
				break;
		}
		System.out.print("\n\n");
		slowText("The winner is........................... " + winner.name.toUpperCase() + "!!!!!!");
	}

	public void titulo(String text) {
		barra(text.length());
                System.out.println(text);
		barra(text.length());
	}

	public void barra(int length) {
		for (int i =0 ; i < length; i++) System.out.print("-");
		System.out.print("\n");
	}
	public void slowText(String text) {
		String[] x = text.split("");
		try {
			for (int i=0; i< x.length; i++) {
				Thread.sleep(50);
				System.out.print(x[i]);
			}
		} catch (InterruptedException e)  {
         e.printStackTrace();
      }
		System.out.print("\n");
	}
}
