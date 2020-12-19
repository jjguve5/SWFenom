package pt.feromonas.skywars;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Location;

public class Game {
	private int state;
	private int arenaID;
	private int maxPlayers;
	private int minPlayers;
	private SWPlayer[] players;
	private Cage[] cages;
	
	Game(int arenaID, int maxPlayers, int minPlayers) {
		
	}
	
	public void addPlayer(SWPlayer p) {
		//TODO: if player is in party get number of members and check if they can all join, then, add them all
		if(p.inParty && !p.isPartyLeader) {
			p.player.sendMessage("Você está em uma party! Saia da party ou peça ao dono da party para entrar no minigame");
			return;
		}
		if(p.inParty && p.isPartyLeader && p.getParty().playersArray.length<=maxPlayers-players.length) {
			for(int i = 0;i<p.getParty().playersArray.length;i++){
				players = (SWPlayer[]) ArrayUtils.add(players, p.getParty().playersArray[i]);
				Location location = new Location(p.getParty().playersArray[i].player.getWorld(),0,69,0);
                ArrayUtils.add(players, p.getParty().playersArray[i].player.teleport(location));
			}
		}
		players = (SWPlayer[]) ArrayUtils.add(players, p);
		Location location = new Location(p.player.getWorld(),0,69,0);
		p.player.teleport(location);
	}
	
	public void removePlayer(SWPlayer p) {
		int index=-1;
		for(int i=0;i<players.length;i++) {
			if(players[i]==p) {
				index=i;
			}
		}
		if(index!=-1) {
			ArrayUtils.remove(players, index);
		}
		
	}
	
	public void changeState(int newState) {
		
	}
	
	public boolean verifyWin() {
		if(getAliveCount()<=1)
			return true;
		return false;
	}
	
	public int getAliveCount() {
		int count = 0;
		for(int i=0;i<players.length;i++) {
			if(players[i].isAlive) {
				count++;
			}
		}
		return count;
	}
	
	public int getArenaID() {
		return arenaID;
	}
	
	public int getState() {
		return state;
	}
}
