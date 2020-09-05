package com.gmail.tomahawkmissile2.multinether;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	public static Main plugin;
	
	public void onEnable() {
		Main.plugin = this;
		
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onEntityPortal(EntityPortalEvent e) {
		Entity p = e.getEntity();
		Location l = p.getLocation();
		Block b = l.getBlock();
		String ss[] = p.getWorld().getName().split("_");
		World overworld = this.getServer().getWorld(ss[0]);
		World nether = this.getServer().getWorld(ss[0]+"_nether");
		World end = this.getServer().getWorld(ss[0]+"_the_end");
		if(p.getWorld().getName().equals("world")||p.getWorld().getName().equals("world_nether")||p.getWorld().getName().equals("world_the_end")) {
			return;
		}
		switch(l.getWorld().getEnvironment()) {
		case NORMAL:
			switch(b.getType()) {
			case NETHER_PORTAL:
				this.teleportSafely(p, new Location(nether, l.getX()/8.0, l.getY(), l.getZ()/8.0));
				break;
			case END_PORTAL:
				p.teleport(end.getSpawnLocation());
				break;
			default:
				break;
			}
			break;
		case NETHER:
			switch(b.getType()) {
			case NETHER_PORTAL:
				this.teleportSafely(p, new Location(overworld, l.getX()*8.0, l.getY(), l.getZ()*8.0));
				break;
			case END_PORTAL:
				p.teleport(end.getSpawnLocation());
				break;
			default:
				break;
			}
			break;
		case THE_END:
			switch(b.getType()) {
			case NETHER_PORTAL:
				this.teleportSafely(p, new Location(nether, l.getX()/8.0, l.getY(), l.getZ()/8.0));
				break;
			case END_PORTAL:
				p.teleport(overworld.getSpawnLocation());
				break;
			default:
				break;
			}
			break;
		}
	}
	@EventHandler
	public void onPlayerPortal(PlayerPortalEvent e) {
		Entity p = e.getPlayer();
		Location l = p.getLocation();
		Block b = l.getBlock();
		String ss[] = p.getWorld().getName().split("_");
		World overworld = this.getServer().getWorld(ss[0]);
		World nether = this.getServer().getWorld(p.getWorld().getName()+"_nether");
		World end = this.getServer().getWorld(p.getWorld().getName()+"_the_end");
		if(p.getWorld().getName().equals("world")||p.getWorld().getName().equals("world_nether")||p.getWorld().getName().equals("world_the_end")) {
			return;
		}
		switch(l.getWorld().getEnvironment()) {
		case NORMAL:
			switch(b.getType()) {
			case NETHER_PORTAL:
				if(nether==null) {
					p.sendMessage(ChatColor.DARK_RED+"Error: nether does not exist for this world! Contact a server administrator!");
					return;
				}
				this.teleportSafely(p, new Location(nether, l.getX()/8.0, l.getY(), l.getZ()/8.0));
				break;
			case END_PORTAL:
				if(end==null) {
					p.sendMessage(ChatColor.DARK_RED+"Error: the end does not exist for this world! Contact a server administrator!");
					return;
				}
				p.teleport(end.getSpawnLocation());
				break;
			default:
				break;
			}
			break;
		case NETHER:
			switch(b.getType()) {
			case NETHER_PORTAL:
				if(overworld==null) {
					p.sendMessage(ChatColor.DARK_RED+"Error: overworld does not exist for this nether! Contact a server administrator!");
					return;
				}
				this.teleportSafely(p, new Location(overworld, l.getX()*8.0, l.getY(), l.getZ()*8.0));
				break;
			case END_PORTAL:
				if(end==null) {
					p.sendMessage(ChatColor.DARK_RED+"Error: the end does not exist for this world! Contact a server administrator!");
					return;
				}
				p.teleport(end.getSpawnLocation());
				break;
			default:
				break;
			}
			break;
		case THE_END:
			switch(b.getType()) {
			case NETHER_PORTAL:
				if(end==null) {
					p.sendMessage(ChatColor.DARK_RED+"Error: nether does not exist for this world! Contact a server administrator!");
					return;
				}
				this.teleportSafely(p, new Location(nether, l.getX()/8.0, l.getY(), l.getZ()/8.0));
				break;
			case END_PORTAL:
				if(nether==null) {
					p.sendMessage(ChatColor.DARK_RED+"Error: overworld does not exist for this world! Contact a server administrator!");
					return;
				}
				p.teleport(overworld.getSpawnLocation());
				break;
			default:
				break;
			}
			break;
		}
	}
	public void constructPortal(Direction d, Location l) {
		switch(d.getType()) {
		case NORTH:
		case SOUTH:
			l.getBlock().getRelative(0, 0, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(1, 0, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(2, 0, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(-1, 0, 0).setType(Material.OBSIDIAN);
			
			l.getBlock().getRelative(2, 1, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(2, 2, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(2, 3, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(-1, 1, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(-1, 2, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(-1, 3, 0).setType(Material.OBSIDIAN);
			
			l.getBlock().getRelative(0, 4, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(1, 4, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(2, 4, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(-1, 4, 0).setType(Material.OBSIDIAN);
			
			l.getBlock().getRelative(0, 2, 0).setType(Material.AIR);
			l.getBlock().getRelative(0, 3, 0).setType(Material.AIR);
			l.getBlock().getRelative(1, 1, 0).setType(Material.AIR);
			l.getBlock().getRelative(1, 2, 0).setType(Material.AIR);
			l.getBlock().getRelative(1, 3, 0).setType(Material.AIR);
			
			l.getBlock().getRelative(0, 1, 0).setType(Material.FIRE);
			break;
		case EAST:
		case WEST:
			l.getBlock().getRelative(0, 0, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 0, 1).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 0, 2).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 0, -1).setType(Material.OBSIDIAN);
			
			l.getBlock().getRelative(0, 1, 2).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 2, 2).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 3, 2).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 1, -1).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 2, -1).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 3, -1).setType(Material.OBSIDIAN);
			
			l.getBlock().getRelative(0, 4, 0).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 4, 1).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 4, 2).setType(Material.OBSIDIAN);
			l.getBlock().getRelative(0, 4, -1).setType(Material.OBSIDIAN);
			
			l.getBlock().getRelative(0, 2, 0).setType(Material.AIR);
			l.getBlock().getRelative(0, 3, 0).setType(Material.AIR);
			l.getBlock().getRelative(0, 1, 1).setType(Material.AIR);
			l.getBlock().getRelative(0, 2, 1).setType(Material.AIR);
			l.getBlock().getRelative(0, 3, 1).setType(Material.AIR);
			
			l.getBlock().getRelative(0, 1, 0).setType(Material.FIRE);
			break;
		}
	}
	public void constructPlatform(World w, Location start, Location end, double y) {
		for(int x=start.getBlockX();x<=end.getBlockX();x++)
			for(int z=start.getBlockZ();z<=end.getBlockZ();z++)
				new Location(w,x,y,z).getBlock().setType(Material.STONE);
	}
	public void teleportSafely(Entity p, Location to) {
		to.getWorld().loadChunk(to.getChunk().getX(),to.getChunk().getZ());
		//Check if nether portal exists in given proximity...
		int radius = 32;
		for(int x = -radius ; x<=radius ; x++) {
			for(int y = 0 ; y<=256 ; y++) {
				for(int z = -radius ; z<=radius ; z++) {
					Block b = to.getBlock().getRelative(x, y-to.getBlockY(), z);
					if(b.getType()==Material.NETHER_PORTAL) {
						p.teleport(new Location(to.getWorld(), b.getX()+1, b.getY()+1, b.getZ()+1));
						return;
					}
				}
			}
		}
		//If not, generate one and teleport player...
		for(int i=256;i>0;i--) {
			Location l = new Location(to.getWorld(),to.getX(),i,to.getZ());
			switch(l.getBlock().getType()) {
			case AIR:
			case WATER:
			case LAVA:
			case BEDROCK:
				break;
			default:
				if(l.getBlock().getRelative(0,1,0).getType()==Material.AIR && l.getBlock().getRelative(0,2,0).getType()==Material.AIR) {
					p.teleport(l);
					this.constructPortal(new Direction(p.getLocation().getYaw()), l);
					return;
				}
			}
		}
		//If all else fails, make it work...
		for(int x=-1 ; x<=2 ; x++) {
			for(int y=0 ; y<=5 ; y++) {
				for(int z=-2 ; z<=2 ; z++) {
					to.getBlock().getRelative(x,y,z).setType(Material.AIR);
				}
			}
		}
		to.setY(64.0);
		this.constructPlatform(to.getWorld(), new Location(to.getWorld(),to.getX()-3,64,to.getZ()-3), new Location(to.getWorld(),to.getX()+3,64,to.getZ()+3), 64);
		this.constructPortal(new Direction(p.getLocation().getYaw()), to);
		p.teleport(to);
	}
}
