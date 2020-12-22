package pt.feromonas.skywars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArenaManager {
	private int maxArenas;
	private int minArenas;
	List<Arena> arenas=new ArrayList<Arena>();
	List<Game> games=new ArrayList<Game>();
	
	ArenaManager(int max, int min){
		maxArenas = max;
		minArenas = min;
		//TODO:get arenas from config
		for(int i=0;i<minArenas;i++) {
			addGame();
		}
	}
	
	public void addGame(){
		Arena a = getRandomArena();
		Game g = new Game(a);
		games.add(g);
	}
	
	public Game getRandomFreeGame(){
		return null;
	}
	
	public void removeGame(Game g) {
		games.remove(g);
	}
	
	public Arena getRandomArena() {
		return arenas.get(new Random().nextInt(arenas.size()));
	}
}
