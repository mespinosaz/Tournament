import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class CombatTree {
	Fighter fighter = new Fighter();
	CombatTree c1 = null;
	CombatTree c2 = null;
	int label = 0;
	public CombatTree() {
	}

	public CombatTree(int nivel, int l) {
		if (nivel > 0) {
			c1 = new CombatTree(nivel-1,l);
			c2 = new CombatTree(nivel-1,l + (int) Math.pow(2,nivel)/2);
		} else {
			label = l;
		}
	}
	
	public CombatTree initialize(ArrayList<Fighter> flist) {
		int index=0;
		if (isLeaf()) return null;
		if (c1.isLeaf() && c2.isLeaf()) {
			index = (c1.label-1)/2;
			c1.fighter = flist.get(index);
			c2.fighter = flist.get(index + flist.size()/2);
		} else {
			c1 = c1.initialize(flist);
			c2 = c2.initialize(flist);
		}

		return this;
	}
	
	public int addNode(CombatTree c) {
		if (c1 == null) {
			c1 = c;
			return 1;
		} 
		if (c2 == null) {
			c2 = c;
			return 2;
		}
		return -1;			
	}

	public void addNode(CombatTree c, int i) {
		switch(i) {
			case 1: c1 = c; break;
			case 2: c2 = c; break;
		}
	}
	
	public CombatTree reverse() {
		CombatTree aux = c1;
		if (!isLeaf()) {
			c1 = c2;
			c2 = aux;
			c1.reverse();
			c2.reverse();
		}
		return this;
		
	}
	
	public boolean isLeaf() {
		if (c1 == null & c2 == null) return true;
		return false;
	}
	
	public Fighter returnFighter() { //Deprecated
		Fighter a1, a2;
		if (c1.isLeaf()) a1 = c1.fighter;
		else a1 = c1.returnFighter();
		if (c2.isLeaf()) a2 = c2.fighter;
		else a2 = c2.returnFighter();
		return solveCombat(a1,a2);
	}


	public void solveLeafCombats() {
		if (c1.isLeaf() && c2.isLeaf()) {
			fighter = solveCombat(c1.fighter,c2.fighter);
			c1 = null;
			c2 = null;
		} else {
			c1.solveLeafCombats();
			c2.solveLeafCombats();
		}
	}

	public Fighter solveCombat(Fighter f1, Fighter f2) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int x=0;
		if (f1.type == 0) return f2;
		if (f2.type == 0) return f1;
		if (f1.type == 2 && f2.type == 2) return autoCombat(f1,f2);
		try {
			do {
				 System.out.print(f1.name + "(" + f1.wfactor + ") vs. " + f2.name + "(" + f2.wfactor + ")\n[ 1. " + f1.name + " | 2. " + f2.name + " | 3.Auto ]: ");
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
		if (f1.type == 0) return f2;
                if (f2.type == 0) return f1;
		int wftotal = f1.wfactor + f2.wfactor;
		Random rand = new Random();
		int x = rand.nextInt(wftotal);
		if (x < f1.wfactor) return f1;
		return f2;
	}
}
