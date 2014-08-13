import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Combat {
	Fighter f1,f2;
	public Combat(Fighter f, Fighter g) {
		f1 = f;
		f2 = g;
	}
	public Fighter solveCombat() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int x=0;
		if (f1.type == 0) return f2;
		if (f2.type == 0) return f1;
		if (f1.type == 2 && f2.type == 2) return autoCombat();
		try {
			do {
				 System.out.print("\n-----------------------\n" + f1.name + "(" + f1.wfactor + ") vs. " + f2.name + "(" + f2.wfactor + ")\n-----------------------\n[ 1. " + f1.name + " | 2. " + f2.name + " | 3.Auto ]: ");
				switch(Integer.parseInt(in.readLine())) {
					case 1: return f1;
					case 2: return f2;
					case 3: return autoCombat();
				}
			}
			while (true);
		} catch (IOException e)  {
			e.printStackTrace();
		}
		return null;
	}
	
	public Fighter autoCombat() {
		if (f1.type == 0) return f2;
      if (f2.type == 0) return f1;
		int wftotal = f1.wfactor + f2.wfactor;
		Random rand = new Random();
		int x = rand.nextInt(wftotal);
		if (x < f1.wfactor) return f1;
		return f2;
	}
}

