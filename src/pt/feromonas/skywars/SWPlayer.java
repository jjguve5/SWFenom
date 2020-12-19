package pt.feromonas.skywars;

import org.bukkit.entity.Player;

public class SWPlayer {
	public boolean inParty;
	public boolean isPartyLeader;
	public Player player;
	public boolean isAlive;
	private Party party;
	
	public Party getParty() {
		return party;
	}
}
