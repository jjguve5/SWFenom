package pt.feromonas.skywars;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Sign;


public class Game {
	private int state;
	private int arenaID;
	private int maxPlayers;
	private int minPlayers;
	private String printableName;
	public List<SWPlayer> players=new ArrayList<SWPlayer>();
	private Cage[] cages;
	public List<Sign> signs = new ArrayList<Sign>();
	
	
	Game(Arena a) {
		arenaID=a.arenaID;
		maxPlayers=a.maxPlayers;
		minPlayers=a.minPlayers;
		printableName=a.printableName;
		
	}
	
	public void addPlayer(SWPlayer p) {
		//TODO: if player is in party get number of members and check if they can all join, then, add them all
		if(players.size()>=maxPlayers) {
			p.player.sendMessage("A partida est� cheia!");
			return;
		}
		if(p.game!=null) {
			p.player.sendMessage("Voc� j� est� em uma partida!!Hmm, bem estranho, me conta como vc fez isso no discord xD");
			return;
		}
		if(p.inParty && !p.isPartyLeader) {
			p.player.sendMessage("Voc� est� em uma party! Saia da party ou pe�a ao dono da party para entrar no minigame");
			return;
		}
		if(p.inParty && p.isPartyLeader && p.getParty().playersArray.length<=maxPlayers-players.size()) {
			for(int i = 0;i<p.getParty().playersArray.length;i++){
				Location location = new Location(p.getParty().playersArray[i].player.getWorld(),0,69,0);
				p.getParty().playersArray[i].player.teleport(location);
                players.add(p.getParty().playersArray[i]);
			}
		}
		players.add(p);
		Location location = new Location(p.player.getWorld(),24,185,1);
		p.player.teleport(location);
		if(getAliveCount()>=minPlayers) {
			changeState(1);
			for(Sign s : signs) {
				Main.signM.updateSign(s);
			}
		}
		p.game=this;
	}
	
	public void removePlayer(SWPlayer p) {
		players.remove(p);
		p.isAlive=true;
		Location location = new Location(p.player.getWorld(),24,185,1);
		p.player.teleport(location);
		if(getAliveCount()<minPlayers) {
			changeState(0);
			for(Sign s : signs) {
				Main.signM.updateSign(s);
			}
		}
		p.game=null;
	}
	
	public void changeState(int newState) {
		state=newState;
	}
	
	public boolean verifyWin() {
		if(getAliveCount()<=1)
			return true;
		return false;
	}
	
	public int getAliveCount() {
		int count = 0;
		for(SWPlayer player : players) {
			if(player.isAlive) {
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
	
	public String getPrintableName() {
		return printableName;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
}
