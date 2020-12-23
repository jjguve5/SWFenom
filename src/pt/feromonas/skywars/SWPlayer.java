package pt.feromonas.skywars;

import org.bukkit.entity.Player;

public class SWPlayer {
	public boolean inParty;
	public boolean isPartyLeader;
	public Player player;
	public boolean isAlive;
	private Party party;
	public Game game;
	
	SWPlayer(Player p){
		player=p;
		isAlive=true;
	}
	
	public Party getParty() {
		return party;
	}
}
