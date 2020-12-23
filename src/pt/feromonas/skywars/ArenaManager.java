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
		Arena arenatest = new Arena(1,12,2,"Algas");
		arenas.add(arenatest);
		arenatest = new Arena(1,12,2,"Lago");
		arenas.add(arenatest);
		arenatest = new Arena(1,5,2,"Hawaii");
		arenas.add(arenatest);
		arenatest = new Arena(1,8,2,"Feriado");
		arenas.add(arenatest);
		arenatest = new Arena(1,24,2,"Piscina");
		arenas.add(arenatest);
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
		return games.get(new Random().nextInt(games.size()));
	}
	
	public void removeGame(Game g) {
		games.remove(g);
	}
	
	public Arena getRandomArena() {
		return arenas.get(new Random().nextInt(arenas.size()));
	}
}
