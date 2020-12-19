package pt.feromonas.skywars;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	String prefix = "[FenomSkyWars]";
	private ArenaManager arenaM;
	private SignManager signM;
	
	@Override
	public void onEnable() {
		System.out.print(prefix+" Foi ativado");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		arenaM=new ArenaManager(100);
		signM=new SignManager();
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
				int index=-1;
				for(int i=0;i<signM.signs.length;i++) {
					if(signM.signs[i]==sign) {
						index=i;
					}
				}
				if(index==-1) {
					ev.getPlayer().sendMessage("Mapa não encontrado, tente denovo!");
					return;
				}
            }
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
