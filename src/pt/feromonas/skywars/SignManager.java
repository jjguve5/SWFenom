package pt.feromonas.skywars;

import java.util.HashMap;

import org.bukkit.block.Sign;

public class SignManager {
	public HashMap<Sign, SignObject> signs = new HashMap<Sign, SignObject>();
	
	SignManager(){
		//TODO:get all signs from signs.yml 
	}
	
	public void addSign(Sign s) {
		//TODO: add the sign to the signs.yml and sings array
	}
	
	public void removeSign(Sign s) {
		//TODO: remove the sign from signs.yml and sings array
	}
}
