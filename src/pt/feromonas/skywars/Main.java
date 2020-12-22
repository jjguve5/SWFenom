package pt.feromonas.skywars;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	String prefix = "[FenomSkyWars]";
	private ArenaManager arenaM;
	private SignManager signM;
	private SettingsManager settings;
	
	@Override
	public void onEnable() {
		System.out.print(prefix+" Foi ativado");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		arenaM=new ArenaManager(100);
		signM=new SignManager(arenaM);
		settings = new SettingsManager(this, "signs");
		settings.setupFile();
		settings.saveFile();
		settings.reloadFile();
	}
	
	public void onDisable() {
		System.out.print(prefix+" Foi desativado");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("sw")) {
			createMenu(player);
		}
		
		return false;
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		if(event.getInventory().getName()!="Swag Inventory")
			return;
		if(event.getCurrentItem().getItemMeta().getDisplayName().contains("this is a display name!")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		if (ev.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (ev.getClickedBlock() == null) {
            return;
        }
		Block block = ev.getClickedBlock();
		if(ev.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) ev.getClickedBlock().getState();
                if(sign.getLine(0).equals("sw") && ev.getPlayer().hasPermission("skywars.sign.create")) {
                	signM.addSign(sign, ev.getPlayer());
                	return;
                }
                if(!sign.getLine(0).equals("§lSkywars"))
                	return;
                SignObject clickedSignObject = signM.signs.get(sign);
                if(clickedSignObject == null) {
                	ev.getPlayer().sendMessage("Arena não encontrada!");
                	ev.getPlayer().getWorld().playSound(ev.getPlayer().getLocation(), Sound.ENTITY_GHAST_HURT, 1, 1);
                	return;
                }
            }
        }
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		Location location = new Location(ev.getPlayer().getWorld(),-2,186,5);
		ev.getPlayer().teleport(location);
	}
	
	public void createMenu(Player player) {
		Inventory inv = Bukkit.getServer().createInventory(null, 9*5, "Swag Inventory");
		
		ItemStack item1 = new ItemStack(Material.COMPASS);
		
		ItemMeta item1Meta = item1.getItemMeta();
		ArrayList<String> item1lore = new ArrayList<String>();
		
		item1lore.add("this is a lore!");
		item1Meta.setDisplayName("this is a display name!");
		item1Meta.setLore(item1lore);
		item1.setItemMeta(item1Meta);
		
		inv.setItem(0, item1);
		
		player.openInventory(inv);
		
	}

}
