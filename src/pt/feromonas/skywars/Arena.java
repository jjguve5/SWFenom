package pt.feromonas.skywars;

public class Arena {
	public int arenaID;
	public int maxPlayers;
	public int minPlayers;
	public String printableName;
	
	Arena(int a, int max, int min, String print){
		arenaID=a;
		maxPlayers=max;
		minPlayers=min;
		printableName=print;
	}
}
