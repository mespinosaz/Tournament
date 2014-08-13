import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Fighter {
	String name;
	int wfactor, type;
	int wins = 0;
	int lose = 0;
	
	public Fighter(String n) {
		name = n;
		wfactor = 75;
		type = 1;
	}

	public Fighter(int id, int difficulty) {
		name = "CPU" + Integer.toString(id);
		Random rand = new Random();
		wfactor = difficulty*20 + rand.nextInt(20) - 10;
		type = 2;
	}
	
	public Fighter() {
		type = 0;
		name = "";
		wfactor = 0;
	}
	public String getDifficulty() {
		String out = new String();
		if (type == 1) out = "Humano";
		else {
			switch((wfactor+10)/20) {
				case 1: out = "Muy fácil"; break;
				case 2: out = "Fácil"; break;
				case 3: out = "Normal"; break;
				case 4: out = "Difícil"; break;
				default: case 5: out = "Muy difícil"; break;
			}
			out += "(" + wfactor + ")";
		}
		return out;
	}	

	public void print () {
		System.out.println("Nombre:\t " + name );
		System.out.println("Dificultad:\t " + getDifficulty() + "\n");
	}
	
}
