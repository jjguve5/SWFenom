package pt.feromonas.skywars;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;

public class SignManager {
	public HashMap<Sign, SignObject> signs = new HashMap<Sign, SignObject>();
	private ArenaManager arenaM;
	
	SignManager(ArenaManager am){
		//TODO:get all signs from signs.yml 
		arenaM=am;
	}
	
	public void addSign(Sign s, Player p) {
		//TODO: add the sign to the signs.yml and sings array
		updateSign(s);
		Game g = arenaM.getRandomFreeGame();
		if(g==null) {
			//TODO: if no game is found search again in x seconds
			p.sendMessage("Nenhum jogo disponivel!");
		}
		g.signs.add(s);
		p.sendMessage("Placa Adicionada!");
		SignObject obj = new SignObject(g);
		signs.put(s,obj);
		updateSign(s);
	}
	
	public void removeSign(Sign s) {
		//TODO: remove the sign from signs.yml and sings array
		signs.remove(s);
	}
	
	public void updateSign(Sign s) {
		int state;
		SignObject signObject = signs.get(s);
		if( signObject==null ||signObject.game==null) {
			state=0;
		} else if(signObject.game.getMaxPlayers()==signObject.game.getAliveCount()) {
			state=4;
		} else if(signObject.game.getState()==1) {
			state=2;
		}  else if(signObject.game.getState()==2) {
			state=3;
		} else {
			state=1;
		}
		Block blockatached = getAttachedBlock((Block)s.getBlock());
		switch(state) {
		case 0:
			s.setLine(0, "�5-------------");
	        s.setLine(1, "�1Procurando");
	        s.setLine(2, "�1Uma Partida");
	        s.setLine(3, "�5-------------");
	        s.update();
	        blockatached.setType(Material.STAINED_GLASS);
	        break;
		case 1:
			s.setLine(0, "�lSkyWars");
	        s.setLine(1, "�2Esperando");
	        s.setLine(2, "�nMapa:"+signObject.game.getPrintableName());
	        s.setLine(3, "�8"+signObject.game.getAliveCount()+"/"+signObject.game.getMaxPlayers());
	        s.update();
	        blockatached.setType(Material.STAINED_GLASS);
	        blockatached.setData((byte)5);
	        break;
		case 2:
			s.setLine(0, "�lSkyWars");
	        s.setLine(1, "�6Come�ando");
	        s.setLine(2, "�nMapa:"+signObject.game.getPrintableName());
	        s.setLine(3, "�8"+signObject.game.getAliveCount()+"/"+signObject.game.getMaxPlayers());
	        s.update();
	        blockatached.setType(Material.STAINED_GLASS);
	        blockatached.setData((byte)4);
	        break;
		case 3:
			s.setLine(0, "�lSkyWars");
	        s.setLine(1, "�4Come�ou");
	        s.setLine(2, "�nMapa:"+signObject.game.getPrintableName());
	        s.setLine(3, "�8"+signObject.game.getAliveCount()+"/"+signObject.game.getMaxPlayers());
	        s.update();
	        blockatached.setType(Material.STAINED_GLASS);
	        blockatached.setData((byte)14);
	        break;
		case 4:
			s.setLine(0, "�lSkyWars");
	        s.setLine(1, "�5Cheio");
	        s.setLine(2, "�nMapa:"+signObject.game.getPrintableName());
	        s.setLine(3, "�8"+signObject.game.getAliveCount()+"/"+signObject.game.getMaxPlayers());
	        s.update();
	        blockatached.setType(Material.STAINED_GLASS);
	        blockatached.setData((byte)10);
	        break;
		}
	}
	
	public static Block getAttachedBlock(Block b) {
        MaterialData m = b.getState().getData();
        BlockFace face = BlockFace.DOWN;
        if (m instanceof Attachable) {
            face = ((Attachable) m).getAttachedFace();
        }
        return b.getRelative(face);
    }
	
}
